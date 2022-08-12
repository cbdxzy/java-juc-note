package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread3 extends Thread{
    @Override
    public void run() {
        System.out.println( 11);
    }

}
class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}
class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {

        TimeUnit.SECONDS.sleep(2);
        System.out.println("hello callable");
        return "callable";
    }
}

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask=   new FutureTask<>(new MyThread());
        Thread ti =new Thread(futureTask,"t1");
        ti.start();
        System.out.println("hello main");
        System.out.println(futureTask.get());
        MyThread3 myThread3=new MyThread3();
      //  myThread3.run();

    }


}
