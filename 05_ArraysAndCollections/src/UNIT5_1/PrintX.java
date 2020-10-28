package UNIT5_1;

import java.util.Scanner;

public class PrintX {
    public static void main(String[] args) {
        String[][] x = new String[][]{{"X", " ", " ", " ", "X"},
                {" ", "X", " ", "X", " "},
                {" ", " ", "X", " ", " "},
                {" ", "X", " ", "X", " "},
                {"X", " ", " ", " ", "X"},};

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j <x[i].length ; j++) {
                System.out.print(x[i][j]);
            }
            System.out.println();
        }

        System.out.println("Могу так же вывести любой другой крестик, размер которого вы сейчас напишете :");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        String [][] strings = new String[number][number];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                if ((i + j == number-1) || (i==j)){
                    strings[i][j]="X";
                }else {
                    strings[i][j]= " ";
                }
            }
        }

        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j <strings[i].length ; j++) {
                System.out.print(strings[i][j] + " ");
            }
            System.out.println();
        }
    }
}
