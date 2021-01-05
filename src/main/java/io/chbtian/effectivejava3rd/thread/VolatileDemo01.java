package io.chbtian.effectivejava3rd.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * volatile
 *
 */
public class VolatileDemo01 {
    private volatile boolean a;  //volatile 保证了可见性和有序性，不能保证原子性

    public VolatileDemo01() {
        this.a = true;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public boolean getA() {
        return a;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo01 var = new VolatileDemo01();
        CountDownLatch cdl = new CountDownLatch(2);

        new Thread(() -> {
            while (var.getA()) {
                //System.out.println(Thread.currentThread().getName());  //这条语句会触发底层硬件的缓存一致性协议，促使线程读取最新数据，所以要注释才能看到效果
            }
            cdl.countDown();
            //System.out.println("end");
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            var.setA(false);
            System.out.println(Thread.currentThread().getName());
            cdl.countDown();

        }, "t2").start();


        cdl.await();
        System.out.println("main stop");
    }
}
