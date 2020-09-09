package thread;

/**
 * 线程安全问题
 * 同步代码块：当使用runnable时可以使用this作为监视器，
 * 当使用继承Thread时尽量避免使用this作为锁
 * 同步方法：默认的锁是this，当使用的继承Thread时会出现线程
 * 安全问题，锁不唯一了（this) 把当前方法改成静态的即可（默认锁
 * 是当前类的.class对象）  确保锁的唯一即可
 * 同步方法和同步代码块包含的不能太多和太少，太多会造成一个线程在执行直至执行完，
 * 太少会导致线程安全问题。
 *
 *
 */
public class Test03 {

    public static void main(String[] args) {
        MyThread t2=new MyThread();
        MyThread t3=new MyThread();
        MyThread t1=new MyThread();
        t2.start();
        t3.start();
        t1.start();
    }

}
class MyThread extends Thread{

    private static int number=100;
    Object object=new Object();
    @Override
    public void run() {


          while (true){
              synchronized (object) {  //多个线程公用同一把锁
                  //在 实现runnable时使用this 作为监听器，而使用继承Thread 尽量避免使用this（防止不是使用的同一个锁）
                  if (number > 0) {
                      System.out.println(Thread.currentThread().getName() + ":" + "当前的票号为：" + number);
                      number--;
                  } else {
                      return;
                  }
              }
          }

    }
}
