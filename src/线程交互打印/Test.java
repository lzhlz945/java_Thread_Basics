package 线程交互打印;

public class Test implements Runnable
{
   // private Object object=new Object();
    private static int num=1;
    @Override
    public void run() {
        while (true){
        synchronized (this) {
            //notify()唤醒一个呗阻塞的线程; notifyAll()唤醒多个阻塞的线程;

            notify(); //当第二个线程进入后唤醒第一个线程
            if (num <= 100) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + num);
                num++;
                //当第一个线程进入了就让它等待进入阻塞状态，让第二个线程唤醒
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }

        }

    }
}
class Test01{
    public static void main(String[] args) {
        Test test = new Test();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.setName("1号打印：");
        t2.setName("2号打印：");
        t1.start();
        t2.start();
    }
}
