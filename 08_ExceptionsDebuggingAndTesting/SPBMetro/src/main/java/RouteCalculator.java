import core.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RouteCalculator {
    private StationIndex stationIndex;

    private static double interStationDuration = 2.5;    //константы времени
    private static double interConnectionDuration = 3.5;

    public RouteCalculator(StationIndex stationIndex) {
        this.stationIndex = stationIndex;           // сложный класс Station Index, еше не раскусил его. Поправка - раскусил )
    }

    public List<Station> getShortestRoute(Station from, Station to) {
        List<Station> route = getRouteOnTheLine(from, to);   // вернет список со станциями, если они находятся на одной линии
        if (route != null) {
            return route;
        }

        route = getRouteWithOneConnection(from, to);        //одна пересадка
        if (route != null) {
            return route;
        }

        route = getRouteWithTwoConnections(from, to);       // две пересадки
        return route;
    }

    public static double calculateDuration(List<Station> route) {
        double duration = 0;
        Station previousStation = null;    //предыдущая станция
        for (int i = 0; i < route.size(); i++) //обычный цикл, проходим по списку станции( те станции, которые будут у нас во время нашего пути)
        {
            Station station = route.get(i);
            if (i > 0) {
                duration += previousStation.getLine().equals(station.getLine()) ?     //идет проверка: линия предыдущей станции такая же , как у текущей станции?
                        interStationDuration : interConnectionDuration;                    // если да, то плюс 2.5 минуты, если нет, то плюс 3.5 минуты(пересадка своего рода )
            }
            previousStation = station;                                          // после прохождения каждой станции, предыдущей станции назначается текущее значение станции.
        }
        return duration;   //время пути
    }

    //=========================================================================

    private List<Station> getRouteOnTheLine(Station from, Station to) {
        if (!from.getLine().equals(to.getLine())) {      //если линия станции from не equals линии станции to (т.е. данные 2 станции находятся на разных линиях)
            return null;  //вернуть нулл
        }
        ArrayList<Station> route = new ArrayList<>();    //эррэй лист со станциями(путь по одной линии)
        List<Station> stations = from.getLine().getStations();   //лист со станциями , которые находятся на линии станции from
        int direction = 0;
        for (Station station : stations)   //проходим по нашим станциям
        {
            if (direction == 0) {
                if (station.equals(from)) {   //станция equals входной станции from.( т.е. нашли станцию на этой линии)
                    direction = 1;
                } else if (station.equals(to)) {
                    direction = -1;
                }
            }

            if (direction != 0) {    //0 будет. если данная станция  является станцией from или to
                route.add(station);
            }

            if ((direction == 1 && station.equals(to)) ||       // конец цикла, если текущая станция equals станции отправления или станции прибытия.
                    (direction == -1 && station.equals(from))) {
                break;
            }
        }
        if (direction == -1) {       //т.к. линия и станции на ней парсятся друг за другом(условно слева на право , если это линия №3), то если у нас идет движение по станции налево, то полученные станции нужно перевернуть, что бы получился последовательный путь.
            Collections.reverse(route);  //переворачиваем станции
        }
        return route;
    }

    private List<Station> getRouteWithOneConnection(Station from, Station to) {
        if (from.getLine().equals(to.getLine())) {   //если станции находятся на одной линии, то это не наш метод
            return null;                            // и делаем ретурн НУЛЛ
        }

        ArrayList<Station> route = new ArrayList<>();   //лист со станциями

        List<Station> fromLineStations = from.getLine().getStations();   //лист со станциями, которые находятся на одной линии с входной станцией(станцией отправления- from)
        List<Station> toLineStations = to.getLine().getStations();      //все то-же самое, только со станцией  to.
        for (Station srcStation : fromLineStations)                      //проходим по станциям, линия отправной станции(from)
        {
            for (Station dstStation : toLineStations)                    //второй проход, но уже по станциям, которые находятся на линии станции to (т.е. станция направления)
            {
                if (isConnected(srcStation, dstStation))        // проверка, можно ли на станции scrStation сделать переход на станцию dstStation. Т.е. мы имеем 2 разные станции на 2х разных ветках.
                {
                    ArrayList<Station> way = new ArrayList<>();
                    way.addAll(getRouteOnTheLine(from, srcStation));           // тут мы добавляем станции в наш путь, от станции from ( т.е. отправная станция) до станции , где происходит пересадка (srcStation).
                    way.addAll(getRouteOnTheLine(dstStation, to));              //тут добавляем станции , на которую перешли со станции srcStation до станции назначения (to).
                    if (route.isEmpty() || route.size() > way.size())            // зачем тут проверка на пустоту(Empty) непонятно, но если дорога\путь (route) > чем длина нашего пути, то route сбрасываем и закидываем все станции последовательно для получения нашего пути.
                    {
                        route.clear();
                        route.addAll(way);
                    }
                }
            }
        }
        if (route.size() == 0)
            route = null;
        return route;
    }

    private boolean isConnected(Station station1, Station station2) {
        Set<Station> connected = stationIndex.getConnectedStations(station1);   //в переменной connected будут хранится станции, которые пересекаются со станцией station1.
        return connected.contains(station2);    //возвращает true если в сэт коннектед присутствует станция station2;
    }

    private List<Station> getRouteViaConnectedLine(Station from, Station to) {
        Set<Station> fromConnected = stationIndex.getConnectedStations(from);  // тут у нас множество станции, которые конектятся с входной станцией(станцией отправления), т.е. те станции, где есть переход на другую линию\станцию
        Set<Station> toConnected = stationIndex.getConnectedStations(to);      //аналогично, но со станцией прибытия
        for (Station srcStation : fromConnected)        //проходим по станциям, которые имеют переход на другие станции
        {
            for (Station dstStation : toConnected)     //аналогично предыдущему
            {
                if (srcStation.getLine().equals(dstStation.getLine())) {     //если линия станции from(srcStation) идентична(equals) линии станции to(она же satStation), то
                    return getRouteOnTheLine(srcStation, dstStation);       //то сделать ретурн метода, где станции находятся на одной линии.
                }
            }
        }
        return null;      // иначе ретурт нул
    }

    private List<Station> getRouteWithTwoConnections(Station from, Station to) {
        if (from.getLine().equals(to.getLine())) {   //привычная проверка, не являются ли станции с одной линии
            return null;
        }

        ArrayList<Station> route = new ArrayList<>();  //эррэй по станциями, по факту наш путь

        List<Station> fromLineStations = from.getLine().getStations();   //лист станции, которые вместе с входной станцией (т.е. станцией отправления from) находятся на одной линии.
        List<Station> toLineStations = to.getLine().getStations();          //такой же лист, станции на линии станции прибытия(to)
        for (Station srcStation : fromLineStations)                      //проходим по станциям, которые находятся на одной линии со станцией отправления(from)
        {
            for (Station dstStation : toLineStations)                   //проходим по станциям, которые находятся на одной линии со станцией прибытия(to)
            {
                List<Station> connectedLineRoute =
                        getRouteViaConnectedLine(srcStation, dstStation);           //Если станции на одной линии, то вернуть список станции, которые будут на нашем пути. а  если станции не имею перехода, то НУЛл ?
                if (connectedLineRoute == null) {
                    continue;
                }
                ArrayList<Station> way = new ArrayList<>();
                way.addAll(getRouteOnTheLine(from, srcStation));    //добавить станции от станции отправления(from) до станции перехода(srcStation), затем от станции, на которую перешли( dstStation) до станции назначения (to) .
                way.addAll(connectedLineRoute);
                way.addAll(getRouteOnTheLine(dstStation, to));              //вот этот блок не очень понятен   =(
                if (route.isEmpty() || route.size() > way.size()) {
                    route.clear();
                    route.addAll(way);
                }
            }
        }

        return route;
    }
}