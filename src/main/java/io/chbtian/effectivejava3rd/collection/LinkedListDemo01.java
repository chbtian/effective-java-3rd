package io.chbtian.effectivejava3rd.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListDemo01 {

    @Test
    public void test03() {//队列
        LinkedList<Integer> deque = new LinkedList<>();
        deque.offer(2);
        deque.offer(4);
        deque.offer(6);

        System.out.println(deque.poll());
        System.out.println(deque.poll());
        System.out.println(deque.poll());
    }

    @Test
    public void test02() {//栈
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(2);
        stack.push(1);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    @Test
    public void test01() {//链表
        LinkedList<Integer> list = new LinkedList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4));
        list.forEach((x) -> System.out.println(x));
        list.stream().filter((e) -> e > 3 ? true : false).forEach((x) -> System.out.println(x));
    }
}
