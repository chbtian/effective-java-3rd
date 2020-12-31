package io.chbtian.effectivejava3rd.rpc.rpc04;

import io.chbtian.effectivejava3rd.rpc.IUserService;
import io.chbtian.effectivejava3rd.rpc.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Stub implements InvocationHandler {

    public IUserService getStub() throws IOException, ClassNotFoundException {
        IUserService userService = (IUserService) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{IUserService.class}, this);
        return  userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        String methodName = method.getName();
//        Object pt = method.getParameterTypes()[0];
//        System.out.println(methodName+" : "+ pt);
        Socket client = new Socket();
        client.connect(new InetSocketAddress("127.0.0.1",8899));
        ObjectOutputStream outs = new ObjectOutputStream(client.getOutputStream());
        String name = method.getName();
        Class<?>[] pts = method.getParameterTypes();

        System.out.println(method.getName());
        for (int i = 0; i < pts.length; i++) {
            System.out.println(pts[i]);
        }

        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        outs.writeUTF(name);
        outs.writeObject(method.getParameterTypes());
        outs.writeObject(args);
        outs.flush();

        ObjectInputStream ins = new ObjectInputStream(client.getInputStream());
        User user = (User)ins.readObject();
        System.out.println(user);

        outs.close();
        ins.close();
        client.close();
        return null;
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        IUserService s = new Stub().getStub();
        s.getUserById(2);

    }
}
