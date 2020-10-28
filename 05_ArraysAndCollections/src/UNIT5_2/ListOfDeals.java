package UNIT5_2;

import org.w3c.dom.ls.LSOutput;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class ListOfDeals {
    public static void main(String[] args) {
        List<String> listOfDeals = new ArrayList<>();
        listOfDeals.add("первое");
        listOfDeals.add("второе");
        listOfDeals.add("третье");

        final String LIST = "LIST";
        final String REGEX_ADD= "add\\s+.+";     // вот тут было изначально "add\\s+.+". Но если вводить к примеру add 1 position,то проходило как по данной реглярке, так и по REGEX_ADD_POSITION. Как можно иначе обойти этот момент?
        final String REGEX_ADD_POSITION = "add\\s+\\d+.+";
        final String REGEX_EDIT_POSITION = "edit\\s+\\d+\\s+.+";
        final String REGEX_DELETE = "delete\\s+\\d+";

        while (true) {
            System.out.println("Что сделать со списком?\nLIST\tADD\t\tEDIT\tDELETE");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase(LIST)) {
                PrintList(listOfDeals);
            }
            if (answer.toLowerCase().matches(REGEX_ADD_POSITION)){
                String []var = answer.split("\\s+");
                StringBuilder text = new StringBuilder();
                for (int i=2;i<var.length;i++){
                    text.append(var[i]).append(" ");
                }
                if (Integer.parseInt(var[1])>listOfDeals.size()){
                 listOfDeals.add(text.toString());
                }
                if (Integer.parseInt(var[1])<listOfDeals.size()){
                    listOfDeals.add(Integer.parseInt(var[1]),text.toString());
                }
            }else if (answer.toLowerCase().matches(REGEX_ADD)){
                String[]var = answer.split("\\s+");
                StringBuilder text = new StringBuilder();
                for (int i=1;i<var.length;i++){
                    text.append(var[i]).append(" ");
                }
                listOfDeals.add(text.toString());
            }
            if (answer.matches(REGEX_EDIT_POSITION)){
                String[]var = answer.split("\\s+");
                StringBuilder text = new StringBuilder();
                for (int i=2;i<var.length;i++){
                    text.append(var[i]).append(" ");
                }
                if (Integer.parseInt(var[1])>=listOfDeals.size()){
                    System.out.println("Вышли за длину списка");
                }
                if (Integer.parseInt(var[1])<listOfDeals.size()){
                    listOfDeals.remove(Integer.parseInt(var[1]));
                    listOfDeals.add(Integer.parseInt(var[1]),text.toString());
                }
            }
            if (answer.matches(REGEX_DELETE)){
                String[]var = answer.split("\\s+");
                if (Integer.parseInt(var[1])>=listOfDeals.size()){
                    System.out.println("Вышли за список");
                }
                if (Integer.parseInt(var[1])<listOfDeals.size()){
                    listOfDeals.remove(Integer.parseInt(var[1]));
                }
            }
        }
    }
    public static void PrintList(List<String> list){
        if (list.size()!=0){
            for (int i = 0;i<list.size();i++){
                System.out.println(i + " - " + list.get(i));
            }
        }
    }
}
