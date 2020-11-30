package io.chbtian.effectivejava3rd.bio.inputstream;

import com.sun.org.apache.xerces.internal.util.XMLInputSourceAdaptor;

import java.io.*;

public class Demo3 {
    public static void main(String[] args) {
//        writeObj();
        readObj();
        OutputStream o;
        Reader r;
        Writer w;

    }

    private static void writeObj() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.db"))) {
            User user = new User("tom", 21);
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void readObj() {
        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream("user.db"))) {
            User user = (User)oos.readObject();
            System.out.println(user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
