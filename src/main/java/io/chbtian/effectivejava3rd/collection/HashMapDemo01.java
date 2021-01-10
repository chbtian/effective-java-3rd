package io.chbtian.effectivejava3rd.collection;

import io.chbtian.effectivejava3rd.thread.Person;
import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;

public class HashMapDemo01 {
    /**
     * 判断重复的条件：
     *  哈希值相同 并且  （指向同一对象或者equals相等）    重复
     */
    @Test
    public void test01() {
        Hashtable<Object,Object> ht;
        Person p1 = new Person("tom");
        Person p2 = p1;
        HashMap<Person, String> map = new HashMap<>();
        map.put(p1, "anhui");
        p2.setName("tom2");
        map.put(p2, "guangdong");
        map.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });



        map.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });

        TreeMap<String,String> tmap;

    }
}
