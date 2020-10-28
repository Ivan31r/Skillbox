package UNIT6_3;

public class IndividualEntrepreneur extends Client {   //ИП
    static final double TAKE_FREE = 0.01;
    static final double TAKE_05_FREE = 0.005;


    public IndividualEntrepreneur(long deposit) {
        super(deposit);
    }

    @Override
    public void putMoney(long money) {
        if (money < 1000) {
           super.putMoney(money + (long) (money * TAKE_FREE));
        } else {
          super.putMoney(money + (long) (money * TAKE_05_FREE));
        }
    }
}
