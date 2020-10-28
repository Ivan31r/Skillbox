package task9_3;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.awt.desktop.SystemSleepEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static final Path staffFile = Paths.get("data\\movementList.csv");
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yy");
    private static BigDecimal commonIncome = BigDecimal.ZERO;
    private static BigDecimal commonExpense = BigDecimal.ZERO;
    private static HashMap<String, BigDecimal> mapOfExpenses;
    private static final byte INDEX_TYPE_OF_ACCOUNT = 0;
    private static final byte INDEX_ACCOUNT_NUMBER = 1;
    private static final byte INDEX_CURRENCY = 2;
    private static final byte INDEX_DATE_OF_ACTION = 3;
    private static final byte INDEX_REFERENCE = 4;
    private static final byte INDEX_DESCRIPTION = 5;
    private static final byte INDEX_INCOME = 6;
    private static final String REGEX = (".+?\\s+(\\d+\\s+)?(.+\\\\)?(.+?)\\s+\\d{2}.\\d{2}.\\d{2}\\s.+");
    private static final String MAIN_PART = "$3";

    private static final byte INDEX_EXPENSE = 7;

    public static void main(String[] args) {
        ArrayList<BankStatement> info = loadInfoFromFile(staffFile);

        commonIncome = getCommonIncome(info);
        commonExpense = getCommonExpense(info);
        mapOfExpenses = getExpenseList(info);

        System.out.println("Общий доход : " + commonIncome);
        System.out.println("Общий расход : " + commonExpense);
        for (String s : mapOfExpenses.keySet()) {
            System.out.println(s + " - " + mapOfExpenses.get(s));
        }
    }

    private static ArrayList<BankStatement> loadInfoFromFile(Path staffFile) {
        ArrayList<BankStatement> info = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(staffFile);
            for (int i = 1; i < lines.size(); i++) {
                String[] fragments = lines.get(i).split(",");

//                if (fragments.length != 8) {
//                    System.out.println("Wrong line will be fix : " + lines.get(i));
//
//                }
                if (fragments.length == 9) {
                    fragments[7] = fragments[7].replace("\"", "") + "." + fragments[8].replace("\"", "");
                }
                info.add(new BankStatement(fragments[INDEX_TYPE_OF_ACCOUNT],
                        fragments[INDEX_ACCOUNT_NUMBER],
                        fragments[INDEX_CURRENCY],
                        LocalDate.parse(fragments[INDEX_DATE_OF_ACTION], dateFormat),
                        fragments[INDEX_REFERENCE],
                        fragments[INDEX_DESCRIPTION],
                        BigDecimal.valueOf(Double.parseDouble(fragments[INDEX_INCOME])),
                        BigDecimal.valueOf(Double.parseDouble(fragments[INDEX_EXPENSE]))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return info;
    }

    public static BigDecimal getCommonIncome(ArrayList<BankStatement> info) {
        BigDecimal income = BigDecimal.valueOf(0.0);
        for (BankStatement bankStatement : info) {
            income = income.add(bankStatement.getIncome());
        }
        return income;
    }

    public static BigDecimal getCommonExpense(ArrayList<BankStatement> info) {
        return info.stream().map(BankStatement::getExpense).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static HashMap<String, BigDecimal> getExpenseList(ArrayList<BankStatement> info) {
        HashMap<String, BigDecimal> results = new HashMap<>();
        String res = "";
        for (BankStatement bankStatement : info) {

            res = bankStatement.getDescriptionOfOperation().replaceAll(REGEX, MAIN_PART);

            if (results.containsKey(res)) {
                results.put(res, results.get(res).add(bankStatement.getExpense()));
            } else {
                results.put(res, bankStatement.getExpense());
            }
        }
        return results;
    }
}



