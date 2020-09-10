package 线程池;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
//        service.execute();
        Future<Integer> submit = service.submit(new Test());
        Class<? extends ExecutorService> aClass = service.getClass();
        System.out.println(aClass);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        service1.setCorePoolSize(1);
        service1.setKeepAliveTime(1,TimeUnit.SECONDS);
        service1.setMaximumPoolSize(10);
        try {
            Integer integer = submit.get();
        System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

}
class Test implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int num=0;
        for (int i = 0; i < 100; i++) {
            num+=i;
            System.out.println(Thread.currentThread().getName()+":-"+i);
        }
        return num;

    }
}