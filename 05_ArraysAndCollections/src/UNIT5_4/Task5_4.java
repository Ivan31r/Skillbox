package UNIT5_4;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Task5_4 {
   public static final String REGEX_NAME = "[a-zA-Z]+";
   public static final String REGEX_Number = "[0-9]{11}";
   public static Map<String,String> subscribers = new TreeMap<>();
    public static void main(String[] args) {
        final String LIST="list";


        subscribers.put("Tom", "89994440101");
        subscribers.put("Mike", "88884440101");
        subscribers.put("Lui", "88884449999");
        subscribers.put("ivan", "89194347388");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите номер телефона или имя");
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase(LIST)) {
                printMap(subscribers);
                continue;
            }
            if (answer.matches(REGEX_NAME)) {
                if (subscribers.containsKey(answer)) {
                    printMap(subscribers, answer);
                } else {
                        System.out.println("Введите номер телефона");
                        subscribers.put(answer, getUserInput(REGEX_Number));
                }
            } else if (answer.matches(REGEX_Number)) {
                if (subscribers.containsValue(answer)) {
                    printMap(subscribers, answer);
                } else {
                        System.out.println("Введите имя");
                        subscribers.put(getUserInput(REGEX_NAME), answer);
                    }

                }
            }
        }
    public static void printMap(Map<String,String> map,String key){
        for (Map.Entry<String,String> pair : map.entrySet()){
            if (key.equals(pair.getKey())){
                System.out.println(pair.getKey() + " : " + pair.getValue());
            }
            else if (key.equals(pair.getValue())){
                System.out.println(pair.getKey() + " : " + pair.getValue() );
            }
        }
    }
    public static void printMap(Map<String,String> map){
        for (Map.Entry<String,String> pair : map.entrySet()){
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
    }
    public static String getUserInput(String s) {
        String input;
        while (true) {
            input = new Scanner(System.in).nextLine().trim();
            if (input.matches(s)) {
                break;
            }else System.out.println("Проверьте ввод данных");
        }
        return input;

    }
}
