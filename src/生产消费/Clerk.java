package 生产消费;

public class Clerk {
    private int num=0;


    public synchronized void pro() {  //生产
        while (true){
            if(num < 20){
                notify();
                num++;
                System.out.println(Thread.currentThread().getName()+":生产了"+num+"个产品");
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void con() {  //消费

            if(num > 0){
                notify();
                System.out.println(Thread.currentThread().getName()+":消费了"+num+"个产品");
                num--;
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }
}
class Producter extends Thread{
    private Clerk clerk;

    public Producter(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.pro();
        }
    }
}
class Consummer extends Thread{
    private Clerk clerk;

    public Consummer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.con();
        }
    }
}
class Test{
    public static void main(String[] args) {

        Clerk clerk = new Clerk();

        Producter p1 = new Producter(clerk);
        Consummer c1 = new Consummer(clerk);
        p1.setName("生产者");
        c1.setName("消费者");

        p1.start();
        c1.start();
    }

}