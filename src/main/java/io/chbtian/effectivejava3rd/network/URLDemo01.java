package io.chbtian.effectivejava3rd.network;

import org.junit.Test;
import org.springframework.beans.propertyeditors.URLEditor;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.*;
import java.net.*;

public class URLDemo01 {
    @Test
    public void test04() throws IOException, URISyntaxException {
        URI uri = new URI("http://localhost:8080/test");
        URL url = uri.toURL();
        //URLConnection是应用层的连接，http，ftp，email，file，建立在底层TCP/UDP上，也就是socket上
        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoOutput(true);//一定要先打开输出模式
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
        writer.write("name=jack");
        writer.flush();//注意
        urlConnection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            System.out.println(inputLine);
        }
        writer.close();
        reader.close();

    }
    @Test
    public void test03() throws IOException {
        URL url = new URL("http://localhost:8080/test?name=tom");
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            System.out.println(inputLine);
        }

        reader.close();


    }

    @Test
    public void test02() throws IOException {
        URL url = new URL("https://docs.oracle.com/javase/8/docs/api/index.html");
        InputStream ins = url.openStream();
        //   InputStream content = (InputStream) url.getContent();
        byte[] b = new byte[10 * 1024];
        int len = ins.read(b);
        System.out.println(new String(b, 0, len));
        //  HttpURLConnection n;
        ins.close();

    }


    @Test
    public void test01() throws IOException {
        URL url01 = new URL("https://www.baidu.com");
        URL url02 = new URL(url01, "/index.html?name=tom");
        System.out.println(url02.getProtocol());
        System.out.println(url02.getAuthority());
        System.out.println(url02.getHost());
        System.out.println(url02.getDefaultPort());
        System.out.println(url02.getPath());
        System.out.println(url02.getFile());
        System.out.println(url02.getQuery());
        System.out.println(url02.getRef());
        System.out.println(url02.getUserInfo());
        System.out.println(url02.getContent());
    }
}
