package UNIT5_5;

import java.util.*;

public class CarNumber {
    public static ArrayList<String> numbers = new ArrayList<>();
    public static char[] letters = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};

    public static void main(String[] args) {

        generateCarNumber();
        Collections.sort(numbers);
        Set<String> hashSet = new HashSet<>(numbers);
        Set<String> treeSet = new TreeSet<>(numbers);

        System.out.println("Введите гос. номер ");
        String number = new Scanner(System.in).nextLine().trim().toUpperCase();
        searchInList(number);
        searchByBinarySearch(number);
        searchInHashSet(number,hashSet);
        searchInTreeSet(number,treeSet);





    }
    public static void generateCarNumber(){
       // ArrayList<String> carNumber = new ArrayList<>();
       // char[] letters = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};
        String res ;
        for (int firstLetter =0;firstLetter<letters.length;firstLetter++){
            for (int secondLetter=0;secondLetter<letters.length;secondLetter++){
                for (int thirdLetter=0;thirdLetter<letters.length;thirdLetter++){
                    for (int number =111;number<1000;number+=111){
                        for (int region = 1;region<200;region++){
                            res=letters[firstLetter]+""+number+letters[secondLetter]+letters[thirdLetter]+region;
                            numbers.add(res);
                        }
                    }
                }
            }
        }
    }
    public static String getText(String typeOfSearch, boolean isFind, long end){
        String text = typeOfSearch;
        text += isFind ? " номер найден" : "номер не найден";
        text += ", поиск занял ";
        text += end + "mc";
        return text;
    }
    private static void searchInList(String number) {
        long start = System.nanoTime();
        boolean findInList = numbers.contains(number);
        long end = System.nanoTime() - start;
        System.out.println(getText("Поиск перебором: ", findInList, end));
    }
    private static void searchInHashSet(String number,Set<String> hashSet) {
        long start = System.nanoTime();
        boolean findInHashSet = hashSet.contains(number);
        long end = System.nanoTime() - start;
        System.out.println(getText("Поиск в HashSet: ", findInHashSet, end));
    }
    private static void searchInTreeSet(String number,Set<String> treeSet) {
        long start = System.nanoTime();
        boolean findInTreeSet = treeSet.contains(number);
        long end = System.nanoTime() - start;
        System.out.println(getText("Поиск в TreeSet: ", findInTreeSet, end));
    }
    private static void searchByBinarySearch(String number) {
        long start = System.nanoTime();
        int index = Collections.binarySearch(numbers,number);
        boolean findByBinarySearch = false;
        if (index>=0){
            findByBinarySearch=true;
        }
        long end = System.nanoTime() - start;
        System.out.println(getText("Бинарный поиск:", findByBinarySearch, end));
    }
}

