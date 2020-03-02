package com.example.myapplicationdemo.awork.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {
    public static final String GET_REQUEST = "GET";

    /**
     * 获取网络数据的方法
     *
     * @param imgDataUrl
     * @return
     */
    public static String doGetNetData(String imgDataUrl) {
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            URL url = new URL(imgDataUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(GET_REQUEST);
            conn.setConnectTimeout(1000 * 30);
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                bos = new ByteArrayOutputStream();
                int len;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                bos.flush();
                return bos.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

    /**
     * 获取图片
     *
     * @param imgDataUrl
     * @return
     */
    public static Bitmap doGetImageToBitemp(String imgDataUrl) {
        Bitmap bitmap = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            URL url = new URL(imgDataUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(GET_REQUEST);
            conn.setConnectTimeout(1000 * 30);
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return bitmap;
    }

}
