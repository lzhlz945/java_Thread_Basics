package 线程安全问题存钱;

public class Acount {
    private Double money;

    public Acount(Double money) {
        this.money = money;
    }
    public synchronized void d(Double a){
        if (a>0) {
            money+=a;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":存入了"+1000+"充值成功"+"余额为："+money);
    }
}
class Customer extends Thread{
   private Acount acount;

    public Customer(Acount acount) {
        this.acount = acount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
           acount.d((double) 1000);
        }

    }
}
class Test{
    public static void main(String[] args) {
        Acount acount=new Acount((double) 0);
        Customer c1=new Customer(acount);
        Customer c2=new Customer(acount);

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}
