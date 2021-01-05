package io.chbtian.effectivejava3rd.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
/*
线程本地变量
1、每一个线程内部都维护有一个Map
2、set时，是在当前线程的Map中存入一个KV，K为ThreadLocal对象，V为传入的值；get时，是在当前线程的Map中根据K获取V；
3、所以各个线程是独一份的，各自隔离的，互不影响；
4、ThreadLocal对象，存在两个引用；一个是自己的引用，一个是Map中Key的引用，所以当自己的引用取消时，不会被GC回收，存在内存泄露问题；
    解决这个问题的方式是Map中的Key对ThreadLocal对象的引用设置为弱引用，如果自己的强引用不存在时，GC会直接回收；
5、同时，传入的对象Value也存在两个引用，一个是自己的强引用，一个是Map中V的强引用；自己的引用取消时，依然不会被回收，存在内存泄露问题；
    解决这个问题的方式是，使用完后，要将Map中的KV remove掉。
6、一个ThreadLocal对象里只能放一个对象，如果需要存多个，需要建立多个ThreadLocal对象。
7、各线程对Value并不是真正的生产一个副本，只是一个引用；所以想实现各线程互不干扰，开始set时Value值就应该是不同的对象，或者各线程对Value只有读操作，无修改操作，否则就会有干扰。
 */
public class ThreadLocalDemo {
    private static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) throws IOException {
        Person obj = new Person("default");//因为传进去的是同一个对象，相互之间是有影响的，比如下面的名称修改
        tl.set(obj);
        System.out.println(obj.hashCode()+ ": "+obj);
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.setName("ttt1");
                tl.set(obj);
                Person o = tl.get();
                System.out.println(Thread.currentThread().getName()+ ": "+o.hashCode()+": "+o);
            }
        },"t1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
             //   obj.setName("ttt2");
                tl.set(obj);
                Person o = tl.get();
                System.out.println(Thread.currentThread().getName()+ ": "+o.hashCode()+": "+o);
            }
        },"t2").start();

        System.out.println(obj.hashCode()+ ": "+obj);
        System.in.read();


    }
}
