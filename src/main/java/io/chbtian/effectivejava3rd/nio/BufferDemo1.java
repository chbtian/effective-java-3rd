package io.chbtian.effectivejava3rd.nio;

import oracle.jrockit.jfr.events.Bits;

import java.nio.*;
import java.util.Arrays;

public class BufferDemo1 {
    public static void main(String[] args) {
        //字节编址顺序
        //Big-endian：大端、高位编址，就是数据从高位地址向低位地址存储
        //Little-endian：小端、低位编址，就是数据从低位地址向高位地址存储
        // 地址不同给的方式由几个
        ByteBuffer b1 = ByteBuffer.allocate(4);
        b1.order(ByteOrder.BIG_ENDIAN);
        b1.putInt(2);
        b1.flip();
        System.out.println(b1.getInt());
        b1.order(ByteOrder.LITTLE_ENDIAN);
        b1.flip();
        System.out.println(b1.getInt());
        /*
        * 十进制数 2 占四个字节：2  33554432
        * Big-endian：00000000 00000000 00000000 00000010
        * Little-endian：00000010 00000000 00000000 00000000
        * */


        System.out.println(ByteOrder.nativeOrder());
        //  intBufferTest();
        ByteBuffer bb = ByteBuffer.allocate(10);
        System.out.println(bb.order());

        //   System.out.println("p: "+ bb.position());
        //   System.out.println("l: "+ bb.limit());
        bb.put((byte) 1);//1
        bb.putInt(2);//4
        bb.putFloat(1.5f);//4
        //   System.out.println("p: "+ bb.position());
        //  System.out.println("l: "+ bb.limit());
        bb.flip();
        //  System.out.println("p: "+ bb.position());
        // System.out.println("l: "+ bb.limit());
        System.out.println(bb.get());
        System.out.println(bb.getInt());//顺序很关键
        System.out.println(bb.getFloat());

        System.out.println(bb.isDirect());

        ByteBuffer bb2 = ByteBuffer.allocateDirect(8);
        bb2.putFloat(2.1f);
        bb2.putFloat(1.3f);
        bb2.flip();//转换之前一定要有flip操作
        FloatBuffer fb = bb2.asFloatBuffer();
        System.out.println("p: " + fb.position());
        System.out.println("l: " + fb.limit());
        System.out.println(fb.capacity());

        while(fb.hasRemaining()){
            System.out.println(fb.get());
        }
        System.out.println(bb2.isDirect());
        System.out.println(fb.isDirect());

        System.out.println(bb2.order());
    }

    private static void intBufferTest() {
    /*
      三种创建方式：
      IntBuffer.allocate(3);
      IntBuffer.wrap(new int[4]);
      ByteBuffer.allocate(3).asIntBuffer();
    */

        IntBuffer ib = IntBuffer.allocate(3);
        ib.put(2);
        ib.put(3);
        ib.put(5);
        //IntBuffer.wrap(new int[4]);
        //ByteBuffer.allocate(3).asIntBuffer();
        System.out.println("capacity: " + ib.capacity());
        System.out.println("limit: " + ib.limit());
        System.out.println("position：" + ib.position());
        //System.out.println("mark: "+ ib.mark());
        ib.flip();
        int i = 0;
        while (ib.hasRemaining()) {
            System.out.println(ib.get());
        }

        ib.flip();
        while (i < ib.limit()) {
            System.out.println(ib.get(i++));
        }

        int[] ia = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        IntBuffer ib2 = IntBuffer.wrap(ia);
        System.out.println("capacity: " + ib2.capacity());
        System.out.println("limit: " + ib2.limit());
        System.out.println("position：" + ib2.position());
        System.out.println("----------");
        int[] dst = new int[3];
        ib2.get(dst);
        System.out.println(Arrays.toString(dst));
        System.out.println("capacity: " + ib2.capacity());
        System.out.println("limit: " + ib2.limit());
        System.out.println("position：" + ib2.position());
    }
}
