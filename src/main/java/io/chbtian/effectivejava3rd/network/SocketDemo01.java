package io.chbtian.effectivejava3rd.network;

import org.junit.Test;

import java.io.*;
import java.net.*;
import java.util.Enumeration;

public class SocketDemo01 {

    @Test
    public void test01() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while(networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println(networkInterface.getDisplayName()+" : " +networkInterface.getName());
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while(inetAddresses.hasMoreElements()){
                System.out.println(inetAddresses.nextElement());
            }
        }
    }
    @Test
    public void client(){
        Socket socket = null;
        BufferedWriter writer = null;
        try {
            socket = new Socket("localhost",7788);
            System.out.println(socket.hashCode());
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write("hello!");
            writer.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void server() throws IOException {
        ServerSocket server = new ServerSocket(7788);
        Socket socket = server.accept();
        System.out.println(socket.hashCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String text = reader.readLine();
        System.out.println(text);
    }
}
