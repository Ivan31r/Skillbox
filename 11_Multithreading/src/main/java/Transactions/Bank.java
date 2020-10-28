package Transactions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {
    private Map<String, Account> accounts = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger(1);
    private Account fromAccount;
    private Account toAccount;
    private static AtomicLong count = new AtomicLong(1);

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public static AtomicInteger getCounter() {
        return counter;
    }


    public String createAccount(long amount) {
        String accNumber = String.valueOf(count);
        Account account = new Account(amount, accNumber);
        accounts.put(accNumber, account);
        count.incrementAndGet();
        return accNumber;
    }

    private final Random random = new Random();

    public synchronized boolean isFraud(Account fromAccount, Account toAccount, long amount)
            throws InterruptedException {
        Thread.sleep(1);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        fromAccount = accounts.get(fromAccountNum);
        toAccount = accounts.get(toAccountNum);
        if (fromAccountNum.equalsIgnoreCase(toAccountNum)) {
            return;
        }
        int comparable = fromAccountNum.compareToIgnoreCase(toAccountNum);

        synchronized (comparable < 0 ? fromAccount : fromAccount) {
            synchronized (comparable < 0 ? toAccount : fromAccount) {
                if (amount > 50000) {
                    try {
                        if (isFraud(fromAccount, toAccount, amount)) {
                            fromAccount.doBlock();
                            toAccount.doBlock();
                        }
                    } catch (InterruptedException e) {
                        e.getMessage();
                    }
                }
                try {
                    doTransfer(fromAccount, toAccount, amount);
                } catch (Exception e) {
                    e.getMessage();
                }

            }
        }


    }

    public void doTransfer(Account fromAccount, Account toAccount, long amount) throws Exception {

        if (isCorrectAccounts(fromAccount, toAccount, amount)) {
            fromAccount.takeMoney(amount);
            toAccount.putMoney(amount);
            counter.incrementAndGet();
        } else {
            throw new WrongConditionsException("Не прошла проверка условий");

        }


    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getCommonMoneyFromAllAccounts() {
        long commonMoney = 0;
        List<String> keys = new ArrayList<>(accounts.keySet());
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            commonMoney += accounts.get(key).getMoney();
        }

        return commonMoney;
    }

    private boolean isCorrectAccounts(Account fromAccount, Account toAccount, long amount) {
        if (amount <= 0) {
            return false;
        } else if (fromAccount.getMoney() < amount) {
            return false;
        } else {
            return !fromAccount.getLock() && !toAccount.getLock();
        }

    }

}
