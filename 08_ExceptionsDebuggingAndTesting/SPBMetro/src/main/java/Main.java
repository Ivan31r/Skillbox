import core.Line;
import core.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String dataFile = "src/main/resources/map.json";
    private static Scanner scanner;

    private static StationIndex stationIndex;

    private static Logger LOGGER = LogManager.getLogger(Main.class);
    private static final Marker INPUT_HISTORY_MARKER = MarkerManager.getMarker("INPUT_HISTORY");
    private static final Marker INVALID_STATIONS_MARKER = MarkerManager.getMarker("INVALID_STATIONS");
    private static final Marker EXCEPTION_HISTORY_MARKER = MarkerManager.getMarker("EXCEPTIONS_HISTORY");

    public static void main(String[] args) {
        RouteCalculator calculator = getRouteCalculator();

        System.out.println("Программа расчёта маршрутов метрополитена Санкт-Петербурга\n");
        scanner = new Scanner(System.in);
        for (; ; ) {
            try {
                Station from = takeStation("Введите станцию отправления:");
                Station to = takeStation("Введите станцию назначения:");

                List<Station> route = calculator.getShortestRoute(from, to);
                System.out.println("Маршрут:");
                printRoute(route);

                System.out.println("Длительность: " +
                        RouteCalculator.calculateDuration(route) + " минут");
                throw new Exception();
            }catch (Exception e){
                LOGGER.error(EXCEPTION_HISTORY_MARKER,"Error",e);
//                System.out.println(e.getMessage());

                //test
            }
        }
    }

    private static RouteCalculator getRouteCalculator()     //коннструктор для Route Calculator ?
    {                                                    //для RouteCalculator нужен объект StationIndex.
        createStationIndex();                         //момент инициализации stationIndex   ( StationIndex stationIndex  = new StationIndex(); )
        return new RouteCalculator(stationIndex);       //возвращаем собственно новый RouteCalculator
    }

    private static void printRoute(List<Station> route)    //печатаем путь
    {
        Station previousStation = null;
        for (Station station : route)          //проходим по листу станции( по нашему пути)
        {
            if (previousStation != null)     //заходим в цикл, если предыдущая станция не Нулл(а не Нулл она будет только со второго прохода)
            {
                Line prevLine = previousStation.getLine();   //смотрим предыдущую линию(по станции)
                Line nextLine = station.getLine();      //смотрим следующую линию( но по факту, это текущая линия)
                if (!prevLine.equals(nextLine))          //сравниваем 2 линии и если они не equals , то печатаем переход на другую станцию
                {
                    System.out.println("\tПереход на станцию " +
                            station.getName() + " (" + nextLine.getName() + " линия)");
                }
            }
            System.out.println("\t" + station.getName());   //печатаем текущую станцию
            previousStation = station;                       //теперь это предыдущая станция
        }
    }

    private static Station takeStation(String message) {
        for (; ; ) {
            System.out.println(message);
            String line = scanner.nextLine().trim();
            Station station = stationIndex.getStation(line);
            if (station != null) {
                LOGGER.info(INPUT_HISTORY_MARKER,"Пользователь ввел станцию: {}",station);
                return station;
            }
            LOGGER.warn(INVALID_STATIONS_MARKER,"Станция {} не найдена", line);
            System.out.println("Станция не найдена :(");
        }
    }

    private static void createStationIndex() {
        stationIndex = new StationIndex();   //инициализация stationIndex.
        try {
            JSONParser parser = new JSONParser();    //получили парсер, для работы с json файлом
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());  //получили "глобальный" объект ( т.к. наш json файл по факту состоит из объекта, в котором 3 объекта с разными значениями).
            //пропарсили мы не json файл, а его стринговое представление (почитаю документацию, но пока не понимаю зачем тут тогда стринг)

            JSONArray linesArray = (JSONArray) jsonData.get("lines"); //парсим массив по ключу "lines", у которого в роли значения выступает массив, а элементами массива являются объекты.
            parseLines(linesArray); //парсим линии

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");   //получаем объект по ключу stations. (это объект в глобальном объекте jsonData)
            parseStations(stationsObject);  //парсим станции

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");   //парсим переходы
            parseConnections(connectionsArray);  //
        } catch (Exception ex) {
            LOGGER.error(EXCEPTION_HISTORY_MARKER,"Error",ex);
            ex.printStackTrace();
        }
    }

    private static void parseConnections(JSONArray connectionsArray)       //на вход получаем массив, по структуре файла элементами данного массива являются массивы, в которых находятся уже объекты.
    {
        connectionsArray.forEach(connectionObject ->                        //проходим по массиму, берем каждый объект( но у нас это массив, который на строчке снизу приводится к массиву)
        {
            JSONArray connection = (JSONArray) connectionObject;              //получаем подмассив главного массива
            List<Station> connectionStations = new ArrayList<>();               //лист станции перехода
            connection.forEach(item ->                                       //проходим по каждому элементу массива connection
            {
                JSONObject itemObject = (JSONObject) item;                   //каждый внутренний элемент массива приводим к json Объекту
                int lineNumber = ((Long) itemObject.get("line")).intValue();  //получаем номер линии, из данного объекта берем значение по ключу line и приводим его к целочисленному значению
                String stationName = (String) itemObject.get("station");    //имя станции = из itemObject берем значение по ключу station.

                Station station = stationIndex.getStation(stationName, lineNumber);        //проверяем , есть ли такая станция ( ведь станции уже заранее пропарсили и они в теории уже все должны быть)
                if (station == null) {                                                     //будет нул, если данная станция не будет найдена
                    throw new IllegalArgumentException("core.Station " +                       //и
                            stationName + " on line " + lineNumber + " not found");             //бросим исключение, которое нам об этом сообщит
                }
                connectionStations.add(station);              //  добавляем в лист с переходами данную станцию
            });
            stationIndex.addConnection(connectionStations);  //добавляем в индекс наш лист с переходами
        });
    }

    private static void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject ->                  //получаем множество ключей  , проходимся по каждому ключу
        {
            int lineNumber = Integer.parseInt((String) lineNumberObject);    //парсим данную линию( по факту это ключ json объекта,в котором значением являются станции)
            Line line = stationIndex.getLine(lineNumber);              //получаем объект Line по ключу( наши линии хранятся в хэшмапе, где ключ это номер линии, а значение имя линии)
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);     //получаем json массив, ключем является ранее полученный номер. В качестве значений у нас станции,а именно массив станции определенной линии
            stationsArray.forEach(stationObject ->                              //прозодим по этому массиву
            {                                                                   //и
                Station station = new Station((String) stationObject, line);    //создаем станцию, где в конструкторе в роли имени будет сам объект,  а линия полученна выше
                stationIndex.addStation(station);           //в индекс добавляем станцию
                line.addStation(station);                   // и в линию добавляем станцию
            });
        });
    }

    private static void parseLines(JSONArray linesArray) {    //получаем на вход массив , элементы данного массива являются объекты
        linesArray.forEach(lineObject -> {       //для каждой линии
            JSONObject lineJsonObject = (JSONObject) lineObject;      //каждую линию приводим к объекту
            Line line = new Line(                       //создается линия( в ее конструкторе инт-номер линии и стринг-имя линии)
                    ((Long) lineJsonObject.get("number")).intValue(),            //у объекта берем значение по параметру number и приводим к числу(к инту)
                    (String) lineJsonObject.get("name")                      //берем у объекта значение по параметру name, в нашем случае это имя
            );
            stationIndex.addLine(line);            //добавляем данную линию в stationIndex
        });
    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();   //получили SB
        try {
            List<String> lines = Files.readAllLines(Paths.get(dataFile));   //прочитали все строки входного файла
            lines.forEach(line -> builder.append(line));                     //получили одну большую строку
        } catch (Exception ex) {
            LOGGER.error(EXCEPTION_HISTORY_MARKER,"Error",ex);
            ex.printStackTrace();
        }
        return builder.toString();
    }
}