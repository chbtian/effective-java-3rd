package io.chbtian.effectivejava3rd.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1",8899));
        while(true){
            Socket client = server.accept();//阻塞
            new Thread(()->{
                try {
                    handler(client);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void handler(Socket client) throws IOException {
        InputStream ins = client.getInputStream();
        byte[] buff = new byte[1024];
        int len = ins.read(buff);//阻塞
        System.out.println(new String(buff,0,len));
        ins.close();
        client.close();
    }
}
