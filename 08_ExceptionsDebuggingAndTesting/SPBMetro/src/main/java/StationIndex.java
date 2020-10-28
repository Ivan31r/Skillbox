import core.Line;
import core.Station;

import java.util.*;
import java.util.stream.Collectors;

public class StationIndex {
    HashMap<Integer, Line> number2line;                //мэп с линиями, ключом выступает номер самой линии
    TreeSet<Station> stations;                         //сэт со станциями
    TreeMap<Station, TreeSet<Station>> connections;    //мэп станции, значением которого является сэт станции. В данном случае ключ является станция, а значение те станции, которые пересекаются со станцией в ключе.

    public StationIndex()           //привычный констуктор
    {
        number2line = new HashMap<>();
        stations = new TreeSet<>();
        connections = new TreeMap<>();
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public void addLine(Line line) {
        number2line.put(line.getNumber(), line);
    }

    public void addConnection(List<Station> stations)             //
    {
        for (Station station : stations) {
            if (!connections.containsKey(station)) {            //если мэп станции не содержит данную станцию( а следовательно и места перехода)
                connections.put(station, new TreeSet<>());     //то добавляем в данную мапу станцию и создаем новый сэт ( в данном сэте должны быть станции,где можно сделать переход)
            }
            TreeSet<Station> connectedStations = connections.get(station);    //создаем и инициализируем сэт из станций, которые пересекаются с текущей станцией
            connectedStations.addAll(stations.stream()
                    .filter(s -> !s.equals(station)).collect(Collectors.toList()));   //добавляем все станции, которые проодят по фильтру (все станции, кроме текущей станции? )
        }
    }

    public Line getLine(int number) {
        return number2line.get(number);
    }

    public Station getStation(String name)  //геттер станции с проверкой
    {
        for (Station station : stations) {
            if (station.getName().equalsIgnoreCase(name)) {    //получим станцию в заданным именем только если она уже есть в глобальном сэте станции
                return station;
            }
        }
        return null;
    }

    public Station getStation(String name, int lineNumber) {
        Station query = new Station(name, getLine(lineNumber));
        Station station = stations.ceiling(query);   //надо погуглить данный метод ceiling
        return station.equals(query) ? station : null;
    }

    public Set<Station> getConnectedStations(Station station) {
        if (connections.containsKey(station)) {    //если данная станция(station) присутствует в мапе , то вернуть ее значения( т.е. те станции, с которыми наша станция (station) пересекается.
            return connections.get(station);
        }
        return new TreeSet<>();   //собственно сам сэт
    }
}
