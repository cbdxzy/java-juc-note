package thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

class netMall{
    public String getNetMallName() {
        return netMallName;
    }

    private String netMallName;

    public netMall(String netMallName){
        this.netMallName=netMallName;
    }

    public double calcPrice(String bookName)  {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 +bookName.charAt(0);
    }

}
public class CompletableFutureMallDemo {
   static List<netMall> list= Arrays.asList(
            new netMall("JD"),
            new netMall("DD"),
            new netMall("TAOBAO"),
           new netMall("pdd"),
            new netMall("Timm"));


   public static List<String> getBookMonet(List<netMall> list,String productName){
       return list.stream().map(e -> productName+"  "+ e.getNetMallName() +e.calcPrice(productName)).collect(Collectors.toList());

//      return list.stream().
//              map(
//               // e ->String.format(productName+"% in %s price is %.2f",e.getNetMallName(),e.calcPrice(productName))
//               e ->String.format(productName+"% in %s price is %.2f",e.getNetMallName(),e.calcPrice(productName))
//                 ).collect(Collectors.toList());

   }
    public static List<String> getBookMonetFuture(List<netMall> list,String productName) {
       //改造实例
       List <CompletableFuture> stringList=new ArrayList<>();
       for(netMall li:list){
          CompletableFuture<String> ss= CompletableFuture.supplyAsync(() -> productName+"  "+ li.getNetMallName() +li.calcPrice(productName));
           stringList.add(ss);
       }
        List <String> stringLists=new ArrayList<>();
       for(CompletableFuture co:stringList){
           stringLists.add(co.join().toString());
       }
        return stringLists;
//
//       return list.stream().map(
//                e -> CompletableFuture.supplyAsync(() -> productName+"  "+ e.getNetMallName() +e.calcPrice(productName))
//                ).collect(Collectors.toList()).stream()
//               .map(s -> s.join())
//               .collect(Collectors.toList());
    }
        public static void main(String[] args) {
       long startTime =System.currentTimeMillis();
        List<String> list1=getBookMonet(list,"mysql");
        list1.stream().forEach(System.out::println);
        long endTime =System.currentTimeMillis();
        System.out.println("Time ="+(endTime-startTime));
            System.out.println("Time ===================");
            long startTime1 =System.currentTimeMillis();
            List<String> list2=getBookMonetFuture(list,"mysql");
            list2.stream().forEach(System.out::println);

            long endTime1 =System.currentTimeMillis();
            System.out.println("Time ="+(endTime1-startTime1));


    }


}
