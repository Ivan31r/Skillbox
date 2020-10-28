package Main;

import com.google.gson.GsonBuilder;
import core.Connection;
import core.Line;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TestMain {

    public static MetroCreation metroCreation = new MetroCreation();
    public static List<Line> allLines = new LinkedList<>();
    public static Map<String, List<String>> stations = new LinkedHashMap<>();
    public static List<String> linesNumber = new LinkedList<>();
    public static String moscowMetroMap = "https://www.moscowmap.ru/metro.html#lines";
    public static String lineSelector = "span[data-line~=[0-9]]";
    public static String lineNumberAttributeKey = "data-line";
    public static String stationOnLineSelect = "[class=js-metro-stations t-metrostation-list-table]";
    public static String DIGITAL_REPLACE_REGEX = "[0-9]+\\.\\s+";
    public static String MoscowMetroMapPath = "src\\data\\MoscowMetroMap.json";
    public static String CSS_ATTRIBUTE_STATION_TITLE = "span[title~=переход на станцию]";
//    public static Map<String, ArrayList<Object>> allConnections = new LinkedHashMap<String, ArrayList<Object>>();
    public static List<List<Connection>> newConnections = new LinkedList<>();


    public static void main(String[] args) throws IOException {


        Document document = Jsoup.connect(moscowMetroMap).maxBodySize(0).get();
        Elements lines = document.select(lineSelector);
        addLineToList(lines);
        Elements stationOnLine = document.select(stationOnLineSelect);
        addStationToMap(stationOnLine);
//        addConnectionToMap(stationOnLine);
        addConnections(stationOnLine);

        metroCreation.setLines(allLines);
        metroCreation.setStations(stations);
//        metroCreation.setConnections(allConnections);
        metroCreation.setNewConnections(newConnections);


//        linesNumber.forEach(System.out::println);




        createJsonFile(MoscowMetroMapPath,metroCreation);

    }

    public static void addLineToList(Elements lines) {
        for (Element element : lines) {
            allLines.add(new Line(element.text(), element.attr(lineNumberAttributeKey)));
            linesNumber.add(element.attr(lineNumberAttributeKey));
        }
    }

    public static void addStationToMap(Elements stationOnLine) {

        int index = 0;
        for (Element element : stationOnLine) {
            List<String> varList = new LinkedList<>();
            for (Element element1 : element.getElementsByTag("a")) {
                varList.add(element1.text().replaceAll(DIGITAL_REPLACE_REGEX, ""));
            }
            String key = linesNumber.get(index);
            stations.put(key, varList);
            index++;

        }
    }

//    public static void addConnectionToMap(Elements stationOnLine) {
//        for (Element element1 : stationOnLine) {
//            for (Element element2 : element1.getElementsByTag("a")) {
//                if (element2.select("span[title~=переход на станцию]").hasAttr("title")) {
//                    Object[] titles = element2.select("span[title~=переход на станцию]").eachAttr("title").toArray();
//                    allConnections.put(element2.text().replaceAll(DIGITAL_REPLACE_REGEX, ""), new ArrayList<Object>(Arrays.asList(titles)));
//                }
//            }
//        }
//    }

    public static void createJsonFile(String MoscowMetroMapPath,MetroCreation metroCreation) {
        String json = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(metroCreation);

        try {
            FileWriter fileWriter = new FileWriter(MoscowMetroMapPath);
            fileWriter.write(json);
            fileWriter.flush();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    public static void addConnections(Elements stationOnLine){
        int index = 0;
        for (Element element1 : stationOnLine){
            List <Connection> varStations =null;
            for (Element element2 : element1.getElementsByTag("a")){
                if (element2.select(CSS_ATTRIBUTE_STATION_TITLE).hasAttr("title")) {
                    Connection connection = new Connection(element2.text().replaceAll(DIGITAL_REPLACE_REGEX,""),linesNumber.get(index));
                    varStations = new LinkedList<>();
                    Object[] titles =  element2.select(CSS_ATTRIBUTE_STATION_TITLE).eachAttr("title").toArray();
                    varStations.add(connection);
                    for (Object o :titles){
                        String station = (String)o;
                        int start = "переход на станцию «".length();
                        int end = station.lastIndexOf("»");
                        String subStation = station.substring(start,end);
                        String lineNumber = element2.select(CSS_ATTRIBUTE_STATION_TITLE).attr("class").replaceAll("\\D+","");

                        varStations.add(new Connection(subStation,lineNumber));

                    }

                }
            }
            newConnections.add(varStations);
            index++;
        }
    }
}
