package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskApiDemo {

    /**
     * futureTask 演示 异步线程的实例
     *  main线程阻塞实例
     *  get 一般放到程序后面一旦调用就需要等待他完成才能离开  不管你是否计算完成，容易造成程序阻塞
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask=new FutureTask<>(() -> {
            System.out.println("正在处理等待返回结果");
            TimeUnit.MILLISECONDS.sleep(5000);
            return "异步线程处理完成";
        });
        Thread thread=new Thread(futureTask,"t1");
        thread.start();
        System.out.println("我是main 在干别事情 没时间处理你的");
        //如果执行放到main线程上面 那么就会阻塞main线程
        System.out.println(futureTask.get());
    }

}
