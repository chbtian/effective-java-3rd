package io.chbtian.effectivejava3rd.chapter04.item15;

import io.chbtian.effectivejava3rd.chapter04.Test2;

public class Test3 extends Test2 {
    Test1 test1; // Test1为包级私有，本包内可以访问。
    // String n = new Test2().number; protected属性不能通过这种方式访问；this和super这两个对象具有继承关系，new Test2()这个自己创建的对象和本类对象不具有继续关系；
    /**
     * 访问父类protected属性或方法的三种正确方式
     */
    String nn = this.number;// 1
    String n = super.number;// 2
    String nnn = number;// 3
    //  String na = this.name;
    // String add = super.address;
    public Test3(){
        System.out.println("Test3");
    }

//    private void test(){
//        System.out.println("test");
//    }
    private static class Test4{ //Test4只被Test3使用
    }
}
