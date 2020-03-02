package com.example.handlerdemo.downloadimg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.example.handlerdemo.MainActivity;
import com.example.handlerdemo.utils.L;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetPicThread implements Runnable {

    String mUrl;
    DownLoadimgActivity.DownHandler mHandler;
    /**
     * 构造方法，用于传递需要的handler，和url
     * @param downHandler
     * @param url
     */
    public GetPicThread(DownLoadimgActivity.DownHandler downHandler, String url) {
        mUrl = url;
        mHandler = downHandler;
    }
//执行下载方法
    @Override
    public void run() {
        download(mUrl,mHandler);
    }

    /**
     * 下载图片数据的方法
     * @param path   图片网址
     * @param handler   处理消息，发送数据到主线程
     */
    public void download(String path,Handler handler){
        try {
            URL url = new URL(path);//获取URL实例
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();//开启HttpURLConnection
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);//设置请求响应时间
            connection.connect();//开启请求
            int requeCode = connection.getResponseCode();//获取请求码
            if(requeCode==HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);//直接获取Bitmap格式数据
                //将获取到的数据传送到主线程
                Message message = Message.obtain();
                message.what= DownLoadimgActivity.WHAT_CODE;
                message.obj=bitmap;
                L.d(MainActivity.TAG,bitmap.toString());
                handler.sendMessage(message);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
