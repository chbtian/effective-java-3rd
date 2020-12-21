package io.chbtian.effectivejava3rd.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BIOClient {
    public static void main(String[] args) throws IOException {
        Socket  client  = new Socket();
        client.connect(new InetSocketAddress("127.0.0.1",8899));
        OutputStream outs = client.getOutputStream();
        outs.write(new String("hello "+Math.random()).getBytes());
        outs.close();
        client.close();
    }
}
