package lock;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest implements Runnable{
    private static int numbert=100;
    ReentrantLock lock=new ReentrantLock();//参数是个bool值 默认false 为true线程顺序执行 先进先出

    @Override
    public void run() {

        while (true){
            try {
                lock.lock();
                if(numbert>0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"余票为："+numbert);
                    numbert--;
                }else {
                    break;
                }
            } finally {

                lock.unlock();
            }


        }

    }
}

