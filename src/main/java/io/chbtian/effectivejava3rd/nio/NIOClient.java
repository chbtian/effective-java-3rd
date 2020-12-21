package io.chbtian.effectivejava3rd.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class NIOClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1",8899));
        sc.configureBlocking(false);
        ByteBuffer buff = ByteBuffer.allocate(1024);
        buff.put(new String("client"+Math.round(Math.random())).getBytes());
        buff.flip();
        sc.write(buff);
        buff.clear();
        TimeUnit.SECONDS.sleep(3000);
        sc.close();
    }
}
