package thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * CompletableFuture 利用线程池调用  然后根据并行线程处理结果后在进行逻辑判断处理
 */
public class CompletableFutureBuildDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//            ExecutorService executorService=     Executors.newFixedThreadPool(3);
//
//        CompletableFuture completableFuture=CompletableFuture.runAsync(() ->{
//            System.out.println("hello "+Thread.currentThread().getName());
//        },executorService);
//        System.out.println("main="+completableFuture.get());
        //获得一个线程池
        ExecutorService executorService=     Executors.newFixedThreadPool(3);

        CompletableFuture completableFuture=CompletableFuture.supplyAsync(() ->{
            System.out.println(ThreadLocalRandom.current().nextInt(10));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello "+Thread.currentThread().getName());
            return "hello supply";
        },executorService)
                .whenComplete((e,y)->{
            System.out.println("自动得到结果后在获取，处理业务逻辑");
        }).exceptionally((e) ->{
                    e.printStackTrace();
                 System.out.println();
                    return "good";
                });
        System.out.println("我先处理其他的");
        System.out.println("main="+completableFuture.get());

    }




}
