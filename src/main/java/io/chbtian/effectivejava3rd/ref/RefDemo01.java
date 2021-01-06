package io.chbtian.effectivejava3rd.ref;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class RefDemo01 {
    public static void main(String[] args) throws IOException {
        M m = new M();//强引用
        SoftReference<M> sr = new SoftReference<>(new M());//软引用
        WeakReference<M> wr = new WeakReference<>(new M());//弱引用
        PhantomReference<M> pf = new PhantomReference<>(new M(),new ReferenceQueue<>());//虚引用
        m = null;
        System.gc();
        System.in.read();
    }
}
