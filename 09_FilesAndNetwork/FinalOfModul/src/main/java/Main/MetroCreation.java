package Main;

import core.Connection;
import core.Line;
import core.Station;

import java.util.*;

public class MetroCreation {
    private Map<String,List<String>> stations = new LinkedHashMap<>();
    private List<Line> lines = new ArrayList<>();
//    private Map<String, ArrayList<Object>> connections = new LinkedHashMap<String, ArrayList<Object>>();
    private List<List<Connection>> newConnections = new LinkedList<>();


//    public void setConnections(Map<String, ArrayList<Object>> connections) {
//        this.connections = connections;
//    }

    public void setStations(Map<String,List<String>> station){
        this.stations=station;
    }
    public void setLines(List <Line> lines){
        this.lines=lines;
    }
    public void setNewConnections(List<List<Connection>> newConnections){
        this.newConnections=newConnections;
    }
}
