package UNIT6_1;

public class PaymentAccount {
    private  long deposit;


    public PaymentAccount(long deposit){
    this.deposit=deposit;
    }

    public void putMoney(long money){
        deposit+=money;
    }
    public void takeMoney(long money){
        if (money>deposit){
            System.out.println("На депозите недостаточно средств");
        }else {
            System.out.println("Вы сняли " + money);
            deposit -= money;
        }
    }
    public long getDeposit() {
        return deposit;
    }
}
