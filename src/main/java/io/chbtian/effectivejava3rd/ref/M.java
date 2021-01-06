package io.chbtian.effectivejava3rd.ref;

public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize "+" 对象被GC时会调用");

    }
}
