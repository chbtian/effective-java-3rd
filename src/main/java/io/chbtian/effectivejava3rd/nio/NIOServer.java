package io.chbtian.effectivejava3rd.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("127.0.0.1", 8899));
        ssc.configureBlocking(false); //设置非阻塞模式

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);//通道注册到选择器（绑定特定的事件）

        while (true) {
            int count = selector.select(1000);//轮训通道IO状态，看是否有IO就绪的通道，返回值大于0代表已就绪的通道个数
            if (count <= 0) { //无准备就绪的IO通道，跳过此次循环，继续下一轮
                System.out.println("无准备继续的IO通道，跳过此次循环，继续下一轮");
                continue;
            }
            //count大于0,代表有就绪的通道，根据不同的就绪事件进行不同的处理
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    System.out.println("有客户端连接");
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    iterator.remove();//最后不要忘记删掉已处理的通道
                } else if (key.isReadable()) {
                    System.out.println("有客户端数据准备就绪");
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer buff = (ByteBuffer) key.attachment();
                    int len = sc.read(buff);
                    System.out.println(new String(buff.array(), 0, len));
                    sc.close();

                    iterator.remove();
                } else {
                    System.out.println("无关注的就绪事件");
                }
            }
        }

    }
}
