package Transactions;

import java.util.Random;

public class Account {
    private long money;
    private String accNumber;
    private boolean isLocked;

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void putMoney(long money) {
        this.money += money;
    }

    public void takeMoney(long money) {
        this.money -= money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public void doBlock() {
        isLocked = true;
    }

    public void doUnBlock() {
        isLocked = false;
    }

    public boolean getLock() {
        return isLocked;
    }
}
