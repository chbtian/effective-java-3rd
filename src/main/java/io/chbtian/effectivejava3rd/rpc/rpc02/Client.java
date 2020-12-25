package io.chbtian.effectivejava3rd.rpc.rpc02;

import io.chbtian.effectivejava3rd.rpc.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("客户端处理");
        int id = 123;
        User user = new Stub().getUserById(id);
    }
}
