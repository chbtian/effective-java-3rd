package io.chbtian.effectivejava3rd.rpc.rpc01;

import io.chbtian.effectivejava3rd.rpc.IUserService;
import io.chbtian.effectivejava3rd.rpc.User;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1",8899));

        while(true){
           final Socket client = server.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        DataInputStream ins =new DataInputStream(client.getInputStream());
                       // byte[] buff = new byte[1024];
                        int id = ins.readInt();
                        IUserService userService = new UserServiceImpl();
                        User user = userService.getUserById(id);

//                        DataOutputStream outs = new DataOutputStream(client.getOutputStream());
//                        outs.writeInt(user.getId());
//                        outs.writeUTF(user.getName());

                        ObjectOutputStream outs = new ObjectOutputStream(client.getOutputStream());
                        outs.writeObject(user);
                        outs.flush();

                        ins.close();
                        outs.close();
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
