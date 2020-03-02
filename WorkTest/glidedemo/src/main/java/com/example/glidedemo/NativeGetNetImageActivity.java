package com.example.glidedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NativeGetNetImageActivity extends AppCompatActivity {

    private Handler handler;
    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nativeimg);
        iv = findViewById(R.id.native_iv_loadimg);
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg != null) {
                    switch (msg.what) {
                        case 200://访问网络成功
                            Bitmap imgBitmap = (Bitmap) msg.obj;
                            iv.setImageBitmap(imgBitmap);
                            break;
                        case -1:
                            iv.setImageResource(R.mipmap.loader_error);
                            break;
                        case -2:
                            iv.setImageResource(R.mipmap.loader_error);
                            break;
                    }
                }
            }
        };
    }

    /**
     * 通过点击按钮加载网络图片
     * @param v
     */
    public void startLoadImgByNative(View v) {
        iv.setImageResource(R.mipmap.loading);
        final String imgUrl = "https://www.imooc.com/static/img/index/logo.png";
        new Thread() {
            @Override
            public void run() {
                super.run();
                startLoadNetImg(imgUrl);
            }
        }.start();

    }

    /**
     * 原生代码加载图片的方法
     * @param imgUrl
     */
    private void startLoadNetImg(String imgUrl) {
        try {
            URL url = new URL(imgUrl);//1,获取图片地址
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3 * 1000);
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);//imageView获取可加载的bitmap对象
                Message msg = Message.obtain();
                msg.what = code;
                msg.obj = bitmap;
                handler.sendMessage(msg);
            }
        } catch (MalformedURLException e) {
            Message msg = Message.obtain();
            msg.what = -1;
            msg.obj = e;
            handler.sendMessage(msg);
            e.printStackTrace();


        } catch (IOException e) {
            Message msg = Message.obtain();
            msg.what = -2;
            msg.obj = e;
            handler.sendMessage(msg);
            e.printStackTrace();
        }
    }
}
