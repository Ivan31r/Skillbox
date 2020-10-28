package UNIT6_3;

public class UsualClient extends Client {   //Физ лицо

    public UsualClient(long deposit) {

        super(deposit);
    }

    @Override
    public void putMoney(long money) {
<<<<<<< HEAD

=======
    super.putMoney(money);
>>>>>>> e981202bfbffc38d7bd66bebc620e48690e2253b
    }

    @Override
    public void takeMoney(long money) {
    if (money>getDeposit()){
        System.out.println("Недостаточно средств");
    }
    else {
      super.takeMoney(money);
        System.out.println("Вы сняли " + money);
    }
    }
}
