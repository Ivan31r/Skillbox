package core;

public class Station implements Comparable<Station> {
//    private String lineNumber;
    private String name;

    public Station(String name) {
        this.name = name;
//        this.lineNumber = lineNumber;
    }
//    public String getLineNumber(){
//        return lineNumber;
//    }
    public String getName(){
        return name;
    }

    @Override
    public int compareTo(Station station) {
       return 0;
    }
}
