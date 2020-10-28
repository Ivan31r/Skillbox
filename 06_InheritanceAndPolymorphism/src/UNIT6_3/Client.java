package UNIT6_3;

abstract class Client {

    private long deposit;

     public Client(long deposit){
         this.deposit=deposit;
     }

    public  long getDeposit(){
         return deposit;
    }

    public  void putMoney(long money){
     deposit+=money;
    }
    public  void takeMoney(long money){
         deposit-=money;
    }
}
