package io.chbtian.effectivejava3rd.bio.inputstream;

import java.io.*;

public class Demo2 {
    public static void main(String[] args) {
        byte[] buffer = {1, 2, 3, 4, 5, 6};
        readBytes(buffer);
    }

    private static void readBytes(byte[] buffer) {
        try (InputStream in = new ByteArrayInputStream(buffer);) {
            byte[] b = new byte[2];
            int c;
            while ((c = in.read(b)) != -1) {
                for (byte b1 : b) {
                    System.out.print(b1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


