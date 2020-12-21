package io.chbtian.effectivejava3rd.nio;

import com.sun.beans.editors.ByteEditor;
import com.sun.security.ntlm.Server;
import jdk.management.resource.internal.inst.FileChannelImplRMHooks;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 * --实现多路复用和非阻塞IO
 * SelectableChannel
 * --SocketChannel
 * --ServerSocketChannel
 * --DatagramChannel
 * Selector
 * SelectionKey
 */
public class ChannelDemo2 {


    @Test
    public void mServer() throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", 8899));
        ssc.configureBlocking(false);

        Selector selector = Selector.open();

        ssc.register(selector, SelectionKey.OP_ACCEPT);


        int select = selector.select();
        switch (select){
            case SelectionKey.OP_ACCEPT :;break;
            case SelectionKey.OP_CONNECT:;break;
        }
        while(true) {
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            sc.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    @Test
    public void nbServer() throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("127.0.0.1", 8899));
        ssc.configureBlocking(false);//设置非阻塞模式

        while (true) {
            SocketChannel sc = ssc.accept();  //不阻塞
            if (sc != null) {
                sc.configureBlocking(false);//设置非阻塞模式
                ByteBuffer buf = ByteBuffer.allocate(1024);
                sc.read(buf); //不阻塞，反复检查
                // sc.close();
                buf.flip();
                byte[] b = new byte[buf.limit()];

                buf.get(b);
                System.out.println(new String(b));
                buf.clear();
            }

        }
    }


    @Test
    public void bCliet() throws IOException {
        SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            buf.put(scanner.next().getBytes());
            buf.flip();
            sc.write(buf);
            buf.clear();

            sc.read(buf);


            buf.clear();
        }
    }

    @Test
    public void bServer() throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("127.0.0.1", 8899));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        // SocketChannel sc = ssc.accept(); //阻塞
        while (true) {
            SocketChannel sc = ssc.accept(); //阻塞
            sc.read(buf); //阻塞
            buf.flip();
            byte[] b = new byte[buf.limit()];

            buf.get(b);
            System.out.println(new String(b));
            buf.clear();
            buf.put("收到".getBytes());
            buf.flip();
            sc.write(buf);
            buf.clear();
        }
    }


    @Test
    public void testBIOClient() throws IOException {

        // Socket socket = new Socket("127.0.0.1",8898);
        SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8898));
        /**
         * 注意：
         * SocketChannel只有通过SocketChannel.open()才能创建
         * 只使用Socket.getChannel()方法无法创建，会出现空指针
         *
         * ServerSocketChannel也是一样，只能通过open方法创建
         * 创建后才可以使用getChannel()获取
         *
         * FileChannel可以通过文件流直接getChannel()获取
         */
        // SocketChannel sc = socket.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileChannel fc = FileChannel.open(Paths.get("a.txt"), StandardOpenOption.READ);
        while (fc.read(buffer) != -1) {
            buffer.flip();
            sc.write(buffer);
            buffer.clear();
        }

        sc.close();
        fc.close();

    }

    @Test
    public void testBIOServer() throws IOException {
        // ServerSocket serverSocket = new ServerSocket(8898);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("127.0.0.1", 8898));
        // ServerSocket serverSocket = ssc.socket();
        // System.out.println(serverSocket.getChannel() == ssc);
        //ServerSocketChannel ssc = serverSocket.getChannel();
        FileChannel fc = FileChannel.open(Paths.get("aaa.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel sc = ssc.accept();//这一步是阻塞的
        while (sc.read(buffer) != -1) {
            buffer.flip();
            fc.write(buffer);
            buffer.clear();
        }
        fc.close();
        sc.close();
        ssc.close();
    }

}
