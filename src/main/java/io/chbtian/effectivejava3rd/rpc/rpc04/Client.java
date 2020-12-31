package io.chbtian.effectivejava3rd.rpc.rpc04;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("客户端处理");
        int id = 123;
//        User user = new Stub().getUserById(id);

        new Stub().getStub().getUserByIdAndName(123,"jack");
      //  stub.getUserById(id);
    }
}
