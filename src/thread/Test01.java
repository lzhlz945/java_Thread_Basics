package thread;

/**
 *
 */
public class Test01 {
    public static void main(String[] args) {
        MyThead t1 = new MyThead("thread:1");
//        t1.setName("线程一");
      t1.setPriority(Thread.MAX_PRIORITY);  //线程的调度 优先级有三个值 1 5 10
//        t1.run(); //如果用run方法启动线程那么就只会有一个线程使用了 一个线程只能使用一次start（)
        t1.start();

        for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i+"**********"+Thread.currentThread().getPriority());
            if(i  == 20){
                try {
                    t1.join();
                } catch (InterruptedException e) {

                }
            }
        }
    }
}
class MyThead extends Thread{
    public MyThead(String name) {
        super(name);//通过构造器命名线程名称

    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2==0){
                System.out.println(Thread.currentThread().getName()+":"+i+Thread.currentThread().getPriority());
            }
        }
    }
}