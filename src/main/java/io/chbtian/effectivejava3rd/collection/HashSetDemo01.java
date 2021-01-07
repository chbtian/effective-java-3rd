package io.chbtian.effectivejava3rd.collection;

import io.chbtian.effectivejava3rd.thread.Person;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetDemo01 {
    /**
     * set:
     * 元素不可重复是根据对象的equals方法判定是否相等的
     * 添加时做了去重判断，保证不重复；
     * 但是如果添加完后，出现的修改操作，使两个对象equals相等了，此时不会再去重，集合中就会有重复的数据。
     */
    @Test
    public void test01(){
        Set<Person> set = new HashSet<>();
        Person p1 = new Person("tom");
        Person p2 = new Person("tom2");
        set.add(p1);
        set.add(p2);
        Iterator<Person> iterator = set.iterator();
        /**
         * Person{name='tom'}
         * Person{name='tom2'}
         */
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        p2.setName("tom");
        Iterator<Person> iterator2 = set.iterator();
        /**
         * Person{name='tom'}
         * Person{name='tom'}
         */
        while(iterator2.hasNext()){
            System.out.println(iterator2.next());
        }
    }


    @Test
    public void test02(){
        Set<Person> set = new HashSet<>();
        Person p1 = new Person("tom");
        set.add(p1);
        p1.setName("tom2");
        Person p2 = p1;
        //System.out.println(p1 == p2);
        set.add(p2);
        Iterator<Person> iterator = set.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


}
