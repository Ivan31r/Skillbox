package UNIT5_1;

public class Main {
    public static void main(String[] args) {
        String text = "Каждый охотник желает знать, где сидит фазан";    // фазан сидит где знать желает охотник каждый
        String [] words = text.split(",?\\s+");
        for (int i = 0; i < words.length /2 ; i++) {
            String s = words[words.length-i-1];
            words[words.length-i-1]=words[i];
            words[i]=s;

        }
        for (String s : words) {
            System.out.println(s);
        }
    }
}
