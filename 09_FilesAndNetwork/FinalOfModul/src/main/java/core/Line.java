package core;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Line implements Comparable<Line>{
    private String number;
    private String name;
//    private List<Station> stations;

    public Line(String name,String number){
        this.name=name;
        this.number=number;
//        stations=new LinkedList<>();
    }

    @Override
    public int compareTo(Line o) {
        return 0;
    }

    public String getNumber()
    {
        return number;
    }

    public String getName(){
        return name;
    }
//    public void addStation(Station station){
//        stations.add(station);
//    }
    public String toString(){
        return getName() + " - " + getNumber();
    }

}
