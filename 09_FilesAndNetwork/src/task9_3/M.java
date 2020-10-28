package task9_3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.*;

public class M {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yy");

    public static void main(String[] args) {
        LocalDate date = LocalDate.parse("20.05.17",dateFormat);
        System.out.println("Before formatting");
        System.out.println(date);
        System.out.println("after format");
        System.out.println(date.format(DateTimeFormatter.ofPattern("dd.MM.yy")));
        System.out.println("\n");
        System.out.println(LocalDate.parse("20.05.17",dateFormat).format(dateFormat));
        System.out.println("Another format");
        System.out.println(dateFormat.format(date));


    }
}
