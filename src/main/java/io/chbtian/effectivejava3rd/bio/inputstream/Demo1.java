package io.chbtian.effectivejava3rd.bio.inputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Demo1 {
    public static void main(String[] args) {
        //readOne();
        //readBytes();
       // readBytesOffset();
        boolean flag = true;
        byte bt = 1;
        short s = 3;
        char c = 'i';
        int i = 10;
        long l = 10L;
        float f = 0.9f;
        double d = 0.5;
        System.out.println(Byte.BYTES);
        System.out.println(Short.BYTES);
        System.out.println(Character.BYTES);

    }

    private static void readOne() {
        try(InputStream in = new FileInputStream("src\\main\\java\\io\\chbtian\\effectivejava3rd\\bio\\inputstream\\a.txt");) {
            int c ;
            while((c=in.read())!=-1){
                System.out.print((char) c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readBytes() {
        try(InputStream in = new FileInputStream("src\\main\\java\\io\\chbtian\\effectivejava3rd\\bio\\inputstream\\a.txt");) {
            byte[] b = new byte[2];
            int c ;
            while((c=in.read(b))!=-1){
                System.out.print(new String(b,0,c));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void readBytesOffset() {
        try(InputStream in = new FileInputStream("src\\main\\java\\io\\chbtian\\effectivejava3rd\\bio\\inputstream\\a.txt");) {
            byte[] b = new byte[8];
            int c ;
            while((c=in.read(b,0,2))!=-1){
                System.out.print(new String(b,0,c));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
