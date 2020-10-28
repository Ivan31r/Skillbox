package Transactions;

import javax.swing.plaf.TableHeaderUI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static boolean isRun = true;


    public static void main(String[] args) {


        Bank bank = new Bank();
        bank.createAccount(4000000);
        bank.createAccount(3000000);
        bank.createAccount(2000000);
        bank.createAccount(1000000);
        bank.createAccount(5000000);



        bank.getAccounts().forEach((s, account) -> System.out.println("Account " + account.getAccNumber() + " has " + account.getMoney()));
        System.out.println("Common balance " + bank.getCommonMoneyFromAllAccounts());

        CreateThread.initAndStartThreads(() -> bank.transfer(getRandomAccount(), getRandomAccount(), 4000));


        bank.getAccounts().forEach((s, account) -> System.out.println("Account " + account.getAccNumber() + " has " + account.getMoney()));
        System.out.println("Common balance " + bank.getCommonMoneyFromAllAccounts());


    }

    public static synchronized String getRandomAccount() {
        String result;
        Random random = new Random();
        int i = random.nextInt(5) + 1;
        result = String.valueOf(i);
        return result;
    }
}
