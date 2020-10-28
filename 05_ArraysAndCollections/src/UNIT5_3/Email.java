package UNIT5_3;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Email {
    public static void main(String[] args) {
       Set<String> emails = new HashSet<>();
        final String LIST = "LIST";
        final String REGEX_EMAIL = ".+@\\D+\\.(ru|com)";
        final String REGEX_ADD = "add\\s+."+REGEX_EMAIL;

        while (true) {
            System.out.println("Введите ADD или LIST");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase(LIST)) {
                PrintList(emails);
            }


            if (answer.toLowerCase().matches(REGEX_ADD)) {
                String[] array = answer.split("\\s+");
                if (array[1].matches(REGEX_EMAIL)) {
                    emails.add(array[1]);
                }

            }
            if (!answer.equalsIgnoreCase(LIST) && !answer.toLowerCase().matches(REGEX_ADD)){
                System.out.println("Проверьте введенную команду и правильность почты");
            }
        }
    }
    public static void PrintList(Set<String> email){
        if (email.size() != 0){
            for (String s : email){
                System.out.println(s);
            }
            System.out.println();
        }
    }
}
