package io.chbtian.effectivejava3rd.lambda;

import com.sun.xml.internal.ws.util.StreamUtils;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaTest {
    class MyConsumer{
      public void consumer_test(Consumer<String> c,String str){
          c.accept(str);
      }
    }
    public static void main(String[] args) {
        Consumer<String> c;
        //(e)->{System.out.println(e);};
        Supplier<String> s;
        Function<String, String> f;
        Predicate<String> p;

        Arrays.asList(2,3,4,5).forEach(integer -> System.out.println(integer));
        new LambdaTest().new MyConsumer().consumer_test(s1 -> {
            System.out.println(s1);
        },"hello consumer");

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(3);
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(2);
        }).start();
    }
}
