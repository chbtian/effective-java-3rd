package io.chbtian.effectivejava3rd.collection;

import java.util.*;

public class ArrayListDemo01 {
    public static void main(String[] args) {
 //       ArrayList<Map<String,String>> integers = new ArrayList<>(20);
//        integers.ensureCapacity(1000000);//在存放大量数据时，首先执行此扩容操作，有助于性能提升；因为add时如果容量不足会不断的扩容，耗费资源；
//        long begin = System.currentTimeMillis();
//        for(int i=0;i<10000000;i++){
//            integers.add(new HashMap<String,String>());
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - begin);
        ArrayList<Integer> integers = new ArrayList<>(10);
        integers.addAll(Arrays.asList(0,3,2,5,6,9,8,7,1,4));
        Iterator<Integer> iterator = integers.iterator();
        integers.remove(0);//导致ConcurrentModificationException
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
