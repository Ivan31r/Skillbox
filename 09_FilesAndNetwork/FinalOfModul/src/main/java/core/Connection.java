package core;

public class Connection implements Comparable<Connection> {
    private String station;
    private String line;

    public Connection(String station,String line){
        this.station=station;
        this.line=line;
    }


    @Override
    public int compareTo(Connection o) {
        return 0;
    }
}
