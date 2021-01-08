package io.chbtian.effectivejava3rd.collection;

import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class ArrayListDemo01 {

    @Test
    public void iterator() {
        ArrayList<Integer> is = new ArrayList<>(10);
        is.addAll(Arrays.asList(0, 3, 2, 5, 6, 9, 8, 7, 1, 4));
        Iterator<Integer> iterator = is.iterator();
        //is.remove(0);//这里会导致ConcurrentModificationException
        while (iterator.hasNext()) {
            int i = iterator.next();
            if (i > 6) {
                iterator.remove();//这里是可以的
            }
        }

        is.forEach((x) -> {
            System.out.println(x);
        });
    }

    @Test
    public void concurrent() throws IOException {
        //ArrayList<String> list = new ArrayList<>();
        List<String> list = Collections.synchronizedList(new ArrayList<>());//1
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {  //多线程并发修改，出现了数据异常
                // synchronized (this) { //2
                list.add(Thread.currentThread().getName().substring(7));

                //  }
            }).start();
        }
        System.in.read();
        System.out.println(list.size());
        System.out.println(list.contains(null));
        list.forEach((x) -> {
            System.out.println(x);
        });

    }


    @Test
    public void addCapacity() {
        ArrayList<Map<String, String>> integers = new ArrayList<>();
        integers.ensureCapacity(20000000);//在存放大量数据时，首先执行此扩容操作，有助于性能提升；因为add时如果容量不足会不断的扩容，耗费资源；
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {
            integers.add(new HashMap<String, String>());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

    @Test
    public void add() {
        ArrayList<Integer> integers = new ArrayList<>(10);
        integers.addAll(Arrays.asList(0, 3, 2, 5, 6, 9, 8, 7, 1, 4));
        integers.add(null);
        integers.add(null);
        Iterator<Integer> iterator = integers.iterator();
        //integers.remove(0);//导致ConcurrentModificationException
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
