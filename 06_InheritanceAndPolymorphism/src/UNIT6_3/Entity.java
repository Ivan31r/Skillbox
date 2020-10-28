package UNIT6_3;

public class Entity extends Client {   // Юр лицо
    static final double TAKE_FREE = 0.01;
    public Entity(long deposit) {

        super(deposit);
    }

    @Override
    public void takeMoney(long money) {
       super.takeMoney(money+ (long)(money*TAKE_FREE));
    }
}
