package io.chbtian.effectivejava3rd.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

public class ChannelDemo1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // fileChannelOpenTest();

        //fileChannelGetTest();
       // newByteChannelTest();
      //  mapTest();

        //truncateTest();
        //lockTest();

        /**
         *  --实现多路复用和非阻塞IO
         *  SelectableChannel
         *      --SocketChannel
         *      --ServerSocketChannel
         *      --DatagramChannel
         *   Selector
         *   SelectionKey
         */
    }

    private static void lockTest() throws IOException, InterruptedException {
        FileChannel fc1 = FileChannel.open(Paths.get("a.txt"),StandardOpenOption.READ,StandardOpenOption.WRITE);
        //竞争同一文件的两个线程可能在不同的Java虚拟机上，
        // 或者一个是Java线程，另一个是操作系统中的其他的某个本地线程。
        // 文件加锁对其他的操作系统进程是可见的，因为Java的文件加锁直接映射到了本地操作系统的加锁工具。
        FileLock flock = fc1.tryLock(0,fc1.size(),true);
        TimeUnit.SECONDS.sleep(60);
        flock.release();

    }

    private static void truncateTest() throws IOException {
        FileChannel fcr = FileChannel.open(Paths.get("a.txt"), StandardOpenOption.READ,StandardOpenOption.WRITE);
        System.out.println(fcr.size());
        fcr.truncate(10);
        System.out.println(fcr.size());
        if (fcr.isOpen()) {
            ByteBuffer buf = ByteBuffer.allocate(2);
            while (fcr.read(buf) != -1) {
                buf.flip();//切记：切换读模式不要忘记
                while(buf.hasRemaining()){
                    System.out.print((char)buf.get());
                }
                buf.clear();
            }

        }
        fcr.close();
    }

    private static void mapTest() throws IOException {
        FileChannel fcr = FileChannel.open(Paths.get("a.txt"),StandardOpenOption.READ);
        FileChannel fcw = FileChannel.open(Paths.get("a221.txt"),StandardOpenOption.CREATE,StandardOpenOption.WRITE);
        //直接将文件映射到物理内存，提升性能，大文件操作时可使用
        //由于依赖底层OS，不易控制、开销较大，所以只有操作需要长时间占用内存的大文件时才值得使用
        MappedByteBuffer inmap = fcr.map(FileChannel.MapMode.READ_ONLY, 0, fcr.size());
        fcw.write(inmap);
        fcr.close();
        fcw.close();

    }

    private static void newByteChannelTest() throws IOException {
        FileChannel fcr = (FileChannel) Files.newByteChannel(Paths.get("a.txt"), StandardOpenOption.READ);
        if (fcr.isOpen()) {
            ByteBuffer buf = ByteBuffer.allocate(10);
            while (fcr.read(buf) != -1) {
                buf.flip();//切记：切换读模式不要忘记
                while(buf.hasRemaining()){
                    System.out.print((char)buf.get());
                }
                buf.clear();
            }

        }
        fcr.close();
    }

    private static void fileChannelGetTest() {
        try (FileChannel fcr = new FileInputStream("反间谍法第二集.mp4").getChannel();
             FileChannel fcw = new FileOutputStream("f22.mp4").getChannel();) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            if (fcr.isOpen() && fcw.isOpen()) {
                while (fcr.read(buffer) != -1) {
                    buffer.flip();//切记：切换读模式不要忘记
                    fcw.write(buffer);
                    buffer.clear();
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileChannelOpenTest() {
        FileChannel fcr = null;
        FileChannel fcw = null;
        try {
            fcr = FileChannel.open(Paths.get("a.txt"), StandardOpenOption.READ);
            fcw = FileChannel.open(Paths.get("a2.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            if (fcr.isOpen() && fcw.isOpen()) {
                while (fcr.read(buffer) != -1) {
                    buffer.flip();//切记：切换读模式不要忘记
                    fcw.write(buffer);
                    buffer.clear();
                }

            }
            //fcr.transferTo(0,fcr.size(),fcw);//最简单的复制方式
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fcr != null) {
                try {
                    fcr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fcw != null) {
                try {
                    fcw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
