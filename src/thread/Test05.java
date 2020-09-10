package thread;

/**
 *死锁问题
 */
public class Test05 {
    public static void main(String[] args) {
        StringBuffer s1=new StringBuffer();
        StringBuffer s2=new StringBuffer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s1){
                    s1.append("a");
                    s2.append("1");
                    //在第一个线程拿到s1后休眠，让第二个线程去拿s2的锁，
                    //第二个线程要去拿s1的锁，但第一个要去拿s2的锁；
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s2){
                        s1.append("b");
                        s2.append("2");
                    }
                    System.out.println(s1);
                    System.out.println(s2);
                }

            }
        }).start();
        new Thread(){
            @Override
            public void run() {
                synchronized (s2){
                    s1.append("c");
                    s2.append("3");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s1){
                        s1.append("d");
                        s2.append("4");

                    }
                    System.out.println(s1);
                    System.out.println(s2);
                }
            }
        }.start();






    }
}
