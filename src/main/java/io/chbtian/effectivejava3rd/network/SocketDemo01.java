package io.chbtian.effectivejava3rd.network;

import org.junit.Test;

import javax.xml.soap.SOAPConnection;
import java.io.*;
import java.net.*;
import java.util.Enumeration;

public class SocketDemo01 {

    @Test
    public void test02() throws IOException {
        String address = NetworkInterface.getByName("eth3").getInetAddresses().nextElement().getHostAddress();
        System.out.println(NetworkInterface.getByName("eth3").isUp());
        System.out.println(NetworkInterface.getByName("eth3").isPointToPoint());
        System.out.println(address);
        Socket socket = new Socket();
        socket.bind(new InetSocketAddress(address,0));//指定使用哪个网络接口卡对外进行连接
        socket.connect(new InetSocketAddress("10.79.4.111",7788));
        System.out.println(socket);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("hello");
        bufferedWriter.flush();
        System.in.read();//阻塞，保持连接，观察连接信息
        socket.close();
    }

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
        System.out.println(socket);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String text = reader.readLine();
        System.out.println(text);
        server.close();
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(7788);
        Socket socket = server.accept();
        System.out.println(socket);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String text = reader.readLine();
        System.out.println(text);
        server.close();
    }

    @Test
    public void test() throws IOException {
        //网络编程模型
        //服务端
        //1、第一步：开启服务，监听端口
        boolean flag = true;
        ServerSocket server = new ServerSocket(7799);
        InputStream ins = null;
        OutputStream outs = null;
        while(flag){
            //2、第二步：接收客户端请求
            Socket client = server.accept();
            //3、第三步：获取输入输出流
            ins = client.getInputStream();
            outs = client.getOutputStream();
            //4、第四步：准备缓冲区
            byte[] buff = new byte[1024];
            //5、第五步：与客户端交互
            ins.read(buff,0,buff.length);
            outs.write(buff);
            outs.flush();
        }
        //6、最后关闭
        ins.close();
        outs.close();
        server.close();

        //客户端
        //1、第一步：开启客户端
        Socket client = new Socket();
        //2、第二步：指定网络接口卡地址
        client.bind(new InetSocketAddress("address",0));//指定使用哪个网络接口卡对外进行连接
        //3、第三步：连接到服务端
        client.connect(new InetSocketAddress("address",7788));
        //4、第四步：获取输入输出流
        ins = client.getInputStream();
        outs = client.getOutputStream();
        //5、第五步：准备缓冲区
        byte[] buff = new byte[1024];
        //6、第六步：与服务端交互
        ins.read(buff,0,buff.length);
        outs.write(buff);
        outs.flush();
        //6、最后关闭流
        ins.close();
        outs.close();
        client.close();
    }

}
