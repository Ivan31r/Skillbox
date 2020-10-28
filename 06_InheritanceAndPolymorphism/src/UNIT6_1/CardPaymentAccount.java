package UNIT6_1;

public class CardPaymentAccount extends PaymentAccount {

    private static double TAKE_FREE = 0.01;

    public CardPaymentAccount(long deposit) {
        super(deposit);
    }


    @Override
    public void takeMoney(long money) {
        super.takeMoney(money+(long)(money*TAKE_FREE));
    }

//    public void takeMoney(long money) {
//        if (money+(long)(money*TAKE_FREE)>deposit){
//            System.out.println("На депозите недостаточно средств");
//        }else {
//            System.out.println("Вы сняли " + money);
//            deposit-=money;
//            System.out.println("Комиссия составила " + money*TAKE_FREE);
//            deposit-=money*TAKE_FREE;
//        }
//    }
}
