package Main;

import core.Connection;
import core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
        Elements lines = document.select("span[data-line~=[0-9]]");
        Elements stations = document.select("[class=js-metro-stations t-metrostation-list-table]");
        Elements element = stations.get(0).getElementsByTag("a");

        List<String> linesNumber = new LinkedList<>();
        linesNumber.add("1");
        linesNumber.add("2");
        linesNumber.add("3");
        linesNumber.add("4");
        linesNumber.add("5");
        linesNumber.add("6");
        linesNumber.add("7");
        linesNumber.add("8");
        linesNumber.add("9");
        linesNumber.add("10");
        linesNumber.add("11");
        linesNumber.add("11A");
        linesNumber.add("12");
        linesNumber.add("14");
        linesNumber.add("15");
        linesNumber.add("D1");
        linesNumber.add("D2");
        linesNumber.add("Mystic line");


        List<List<Connection>>  testConnections = new LinkedList<>();


        int index = 0;
        for (Element element1 : stations){
            String s = null;
            List<Connection> varStations = new LinkedList<>();
            for (Element element2 : element1.getElementsByTag("a")){
                if (element2.select("span[title~=переход на станцию]").hasAttr("title")) {
                    System.out.println(element2.text().replaceAll("[0-9]+\\.\\s+","") + " -станция пересадки");
                    s=element2.text().replaceAll("[0-9]+\\.\\s+","");
                    System.out.println(linesNumber.get(index) + " -номер линии");
                    Connection connection = new Connection(element2.text().replaceAll("[0-9]+\\.\\s+",""),linesNumber.get(index));
                    Object[] titles =  element2.select("span[title~=переход на станцию]").eachAttr("title").toArray();
                    for (Object o :titles){
//                        varStations = new TreeSet<>();
                        String station = (String)o;
                        int start = "переход на станцию «".length();
                        int end = station.lastIndexOf("»");
                        System.out.println("-----");
                        System.out.println("Переход на станцию : ");
                        String subStation = station.substring(start,end);
                        System.out.println(station.substring(start,end));
                        String lineNumber = element2.select("span[title~=переход на станцию]").attr("class").replaceAll("\\D+","");
                        System.out.println(element2.select("span[title~=переход на станцию]").attr("class").replaceAll("\\D+",""));
                        varStations.add(new Connection(element2.text().replaceAll("[0-9]+\\.\\s+",""),linesNumber.get(index)));
                        varStations.add(new Connection(element2.text().replaceAll("[0-9]+\\.\\s+",""),linesNumber.get(index)));

                    }
                    System.out.println("- - - - - ");
                }
            }
            testConnections.add(varStations);
            index++;
        }
        System.out.println("\n");
        System.out.println(testConnections.size());

//        System.out.println(element.get(10));
//        System.out.println(element.get(10).text().replaceAll("[0-9]+\\.\\s+",""));
//        System.out.println(element.get(10).select("span[title~=переход на станцию]").attr("title"));
//
//        System.out.println();
//
//        String s = element.get(10).select("span[title~=переход на станцию]").attr("title");
//        int start = "переход на станцию «".length();
//        int end = s.lastIndexOf("»");
//        System.out.println("Line " + s.substring(start,end));
//        System.out.println("s - " + s);
//
//        System.out.println();
//
//        int a = element.get(10).select("span[title~=переход на станцию]").attr("title").lastIndexOf("»");
//        System.out.println(element.get(10).select("span[title~=переход на станцию]").attr("title").substring("переход на станцию".length() + 2));
//        System.out.println(element.get(10).select("span[title~=переход на станцию]").attr("class").replaceAll("\\D+",""));
//        System.out.println("a" + a);


        //  TreeMap<Station, TreeSet<Station>> connections;      что-бы пересадки были такие же как в json файле в спб метро, нужно
        // в TreeMap в ключ(т.е. Station добавлять нашу станцию, с которой есть возможность перехода на другую станцию
        // а в значение(т.е. в TreeSet<Station> ) добавлять Сэт (в нашем случае TreeSet) тех самых станций, на которые
        //мы можем сделать переход. Обращаем внимание, что в ключе и в значении используются ОБЪЕКТЫ Station, а не класса String

    }
}
