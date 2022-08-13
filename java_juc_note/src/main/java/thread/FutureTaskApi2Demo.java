package thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskApi2Demo {

    /**
     * 根据返回结果进行运算
     * thenApply  try catch  的使用类似
     * handle     try finally 的使用类似
     * thenApply  //计算结果，存在依赖关系
     * thenAccept 接收任务的处理结果，并消费处理 无返回值
     * applyToEither (CompletableFuture, f ->{ return f +"xxx" })  ：速度选用谁快选谁
     * A  B 俩个CompletableFuture 俩个对吗结果合并  可以用A.thenCombine(B,(x,y) ->{
     *     return x+y;
     * })
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture=   CompletableFuture.supplyAsync( () ->{
            System.out.println("第一次运算");
            return 1;
        }).thenApply( e ->{
            System.out.println("第2次运算");
            int p=10/0;
            return e+2;
        }).thenApply(f ->{
            System.out.println("第3次运算");
            return f+3;
        }).whenComplete((v,e) ->{
                    if(e==null){
                        System.out.println("结算结果："+v);
                    }
        }).exceptionally(e ->{
            e.printStackTrace();
            System.out.println(  e.getMessage());
            return null;
        });
        System.out.println(completableFuture.get());
    }

}
