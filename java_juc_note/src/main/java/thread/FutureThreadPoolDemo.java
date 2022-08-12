package thread;


import java.util.concurrent.*;

/**
 *      FutureTask 利用线程池来进行并行线程运行的实例
 *         多线程  有返回  异步任务
 *         get  容易阻塞
 */

public class FutureThreadPoolDemo {

    public static void main1(String[] args) throws InterruptedException {
        long start=  System.currentTimeMillis();
        TimeUnit.MILLISECONDS.sleep(500);
        TimeUnit.MILLISECONDS.sleep(300);
        TimeUnit.MILLISECONDS.sleep(300);
        long end=  System.currentTimeMillis();
        System.out.println("耗时间为："+(end-start));
    }

    public static void main2(String[] args) throws InterruptedException, ExecutionException {
        long start=  System.currentTimeMillis();
        ExecutorService executorService=   Executors.newFixedThreadPool(3);
        FutureTask<String> futureTask=new FutureTask<>(() ->{
            TimeUnit.MILLISECONDS.sleep(500);
            return "task1 over";
        });
        executorService.submit(futureTask);
        FutureTask<String> futureTask2=new FutureTask<>(() ->{
            TimeUnit.MILLISECONDS.sleep(300);
            return "task2 over";
        });
        executorService.submit(futureTask2);
        System.out.println(futureTask.get());
        System.out.println(futureTask2.get());

        executorService.shutdown();
        TimeUnit.MILLISECONDS.sleep(300);
        long end=  System.currentTimeMillis();
        System.out.println("耗时间为："+(end-start));
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
       new Thread(() -> {
           try {
               System.out.println("hello t1");
               TimeUnit.MILLISECONDS.sleep(300);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }).start();

        new Thread(() -> {
            try {
                System.out.println("hello t2");
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println("hello t3");
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        long end=System.currentTimeMillis();

        System.out.println("耗时间为："+(end-start));
    }

}
