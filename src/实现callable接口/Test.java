package 实现callable接口;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test implements Callable<Integer> {

    /**
     * 有返回值
     * 可以抛出异常
     * 可以泛型作为参赛
     * 返回的是FuntureTask实现类 继承Runnable
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        int num=0;
        for (int i = 0; i < 100; i++) {
            if(i % 2== 0){
                num+=i;
            }
            System.out.println(Thread.currentThread().getName()+":"+num);
        }

        return num;
    }
}
class Test01{
    public static void main(String[] args) {
  //实现callable要用FutureTask接口 还要使用Thread来启动
        //FutureTask最终继承了Runnable 接口 就可以实现Thread 来启动线程
        Test test = new Test();
        FutureTask<Integer> task = new FutureTask<Integer>(test);
        Thread thread = new Thread(task);
        thread.start();
        try {
            Integer num = task.get();
            System.out.println("总和为:"+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}