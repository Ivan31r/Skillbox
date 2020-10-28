package UNIT6_1;

import java.time.LocalDate;

public class DepositPaymentAccount extends PaymentAccount {
   private LocalDate putMoneyDate;


    public DepositPaymentAccount(long deposit) {
        super(deposit);
    }


    @Override
    public void putMoney(long money) {
        super.putMoney(money);
        putMoneyDate=LocalDate.now();
    }

    @Override
    public void takeMoney(long money) {
        LocalDate today = LocalDate.now();
        int countDays =0;

        while (today.isAfter(putMoneyDate)){
        countDays++;
        putMoneyDate=putMoneyDate.plusDays(1);
        }

        if (countDays>31){
            super.takeMoney(money);
        }else {
            System.out.println("После последнего внесения денежных средств прошо менььше месяца");
        }

    }
}
