package io.chbtian.effectivejava3rd.collection;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class QueueDemo01 {

    @Test
    public void test02(){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(3);
        pq.add(1);
        pq.add(5);
        System.out.println(pq.remove());
    }
    @Test
    public void test01(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.add(3);
        ad.add(4);
        ad.forEach((x)-> System.out.println(x));
        ad.remove();
        ad.forEach((x)-> System.out.println(x));

        ad.offer(6);
        ad.offer(8);

        ad.forEach((x)-> System.out.println(x));

        ad.poll();

        ad.forEach((x)-> System.out.println(x));


        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(5);
        stack.push(9);
        System.out.println(stack.pop());


        PriorityQueue<Integer> pq = new PriorityQueue<>();

    }
}
