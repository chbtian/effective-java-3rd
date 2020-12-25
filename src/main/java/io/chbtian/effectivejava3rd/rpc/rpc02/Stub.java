package io.chbtian.effectivejava3rd.rpc.rpc02;

import io.chbtian.effectivejava3rd.rpc.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Stub {

    public User getUserById(int id) throws IOException, ClassNotFoundException {
        Socket client = new Socket();
        client.connect(new InetSocketAddress("127.0.0.1",8899));
        DataOutputStream outs = new DataOutputStream(client.getOutputStream());
        outs.writeInt(id);
        outs.flush();

        ObjectInputStream ins = new ObjectInputStream(client.getInputStream());
        User user = (User)ins.readObject();
        System.out.println(user);

        outs.close();
        ins.close();
        client.close();
        return  user;
    }
}
