package io.chbtian.effectivejava3rd.chapter04;

import io.chbtian.effectivejava3rd.chapter04.item15.Test3;

public class Test2 {
  //  Test1 test;  Test1为包级私有，外界无法访问；
      Test3 test3;
      private String name;
      String address;
      protected String number;
      public  String company;

      public Test2(){
          System.out.println("Test2");
      }

      public void test(){
          System.out.println("test");
      }
}
