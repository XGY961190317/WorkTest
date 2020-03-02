package com.example.sqlitedemo.downloaddatadistolv.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {
    public static String doGet(String urlStr){
        HttpURLConnection conn =null;
        InputStream is=null;
        ByteArrayOutputStream bos=null;
        try {
            URL url = new URL(urlStr);
             conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(30*1000);
            if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                 is = conn.getInputStream();
                 bos = new ByteArrayOutputStream();
                int len;
                byte [] buf = new byte[1024];
                while((len=is.read(buf))!=-1){
                    bos.write(buf,0,len);
                }
                bos.flush();
               return bos.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                conn.disconnect();
            }
        }
        return null;
    }
}
