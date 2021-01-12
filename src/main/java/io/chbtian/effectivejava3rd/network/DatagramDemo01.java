package io.chbtian.effectivejava3rd.network;

import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramDemo01 {
    @Test
    public void client() throws IOException {
        DatagramSocket client = new DatagramSocket();
        byte[] buff = "hello".getBytes();
        DatagramPacket packet = new DatagramPacket(buff,buff.length, InetAddress.getByName("localhost"),7788);
       // packet.setSocketAddress(new InetSocketAddress(7788));
        client.send(packet);
        client.close();

    }

    @Test
    public void server() throws SocketException {
        DatagramSocket server = new DatagramSocket(7788);
        byte[] buff = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buff,buff.length);
       // DatagramChannel channel = server.getChannel();
        //ByteBuffer buff = ByteBuffer.allocate(1024);
        try {
            server.receive(packet);
            System.out.println(new String(packet.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.close();
    }
}
