package io.chbtian.effectivejava3rd.rpc.rpc01;

import io.chbtian.effectivejava3rd.rpc.User;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket client = new Socket();
        client.connect(new InetSocketAddress("127.0.0.1",8899));
        int id = 123;
        DataOutputStream outs = new DataOutputStream(client.getOutputStream());
        outs.writeInt(id);
        outs.flush();

//        DataInputStream ins = new DataInputStream(client.getInputStream());
//        int id2 = ins.readInt();
//        String name = ins.readUTF();
//        User user = new User(id2,name);
        ObjectInputStream ins = new ObjectInputStream(client.getInputStream());
        User user = (User)ins.readObject();
        System.out.println(user);

        outs.close();
        ins.close();
        client.close();


    }
}
