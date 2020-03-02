package com.example.handlerdemo.downloadimg;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.handlerdemo.R;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DownLoadimgActivity extends AppCompatActivity {
    public static final int WHAT_CODE = 1000;//message识别码
    private ImageView img;
    private Button btn;
    final String Url = "https://img2.mukewang.com/5adfee7f0001cbb906000338-240-135.jpg";//URL
    final DownHandler downHandler  = new DownHandler(DownLoadimgActivity.this);//实例化handler
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downlaoadimg_activity);
        initView();//初始化控件
        setListener();//设置事件监听


    }

    private void setListener() {
        //点击开始下载
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new GetPicThread(downHandler,Url)).start();//直接将自定义的Runnable类作为参数给子线程，启动下载方法
            }
        });
    }

    private void initView() {
        btn = findViewById(R.id.btn_net_img);
        img = findViewById(R.id.net_img);
    }


    /**
     * 创建一个handler，处理UI更新
     */
    public static class DownHandler extends Handler {
        //弱引用
        final WeakReference<DownLoadimgActivity> mWeakReference;

        DownHandler(DownLoadimgActivity activity) {
            this.mWeakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            DownLoadimgActivity activity = mWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case WHAT_CODE:
                        //获取数据，设置控件，显示
                        activity.img.setImageBitmap((Bitmap) msg.obj);
                        break;
                }
            }
        }
    }

}
