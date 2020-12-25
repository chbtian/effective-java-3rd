package io.chbtian.effectivejava3rd.rpc.rpc03;

import io.chbtian.effectivejava3rd.rpc.IUserService;
import io.chbtian.effectivejava3rd.rpc.User;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
                        String methodName = ins.readUTF();
                        int id = ins.readInt();
                        IUserService userService = new UserServiceImpl();
                        System.out.println(methodName);
                        Class<IUserService> clazz = IUserService.class;
                        Method method = clazz.getDeclaredMethod(methodName, Integer.class);
                        User user = (User) method.invoke(userService,id);


                        ObjectOutputStream outs = new ObjectOutputStream(client.getOutputStream());
                        outs.writeObject(user);
                        outs.flush();

                        ins.close();
                        outs.close();
                        client.close();
                    } catch (IOException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
