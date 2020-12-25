package io.chbtian.effectivejava3rd.rpc.rpc03;

import io.chbtian.effectivejava3rd.rpc.User;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("客户端处理");
        int id = 123;
//        User user = new Stub().getUserById(id);

        Stub stub = new Stub();
        stub.process("getUserById",id);
    }
}
