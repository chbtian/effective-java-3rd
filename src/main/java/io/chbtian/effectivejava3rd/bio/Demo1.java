package io.chbtian.effectivejava3rd.bio;

import java.io.*;

public class Demo1 {
    public static void main(String[] args) {
        try (InputStream in = new FileInputStream("a.txt");
             OutputStream out = new FileOutputStream("b.txt")) {
            int c ;
            while((c = in.read())!=-1){
                out.write(c);
                System.out.print((char)c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
