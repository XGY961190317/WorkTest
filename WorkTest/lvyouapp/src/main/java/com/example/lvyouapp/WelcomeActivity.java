package com.example.lvyouapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {


    public static final int CODE = 1001;//code
    public static final int TOTAI_TIME = 1000*10;//倒计时总时间
    public static final int INTERVAL_TIME = 1000;//间隔时间
    private TextView timeTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //程序一进来，先发一个msg到handler，让其设置倒计时时间
       MyHandler handler = new MyHandler(this);
        Message message = Message.obtain();
        message.what=CODE;
       message.arg1 = TOTAI_TIME;//倒计时的总时间
        handler.sendMessage(message);
       timeTv = findViewById(R.id.time);
        timeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        });
    }


    //自定义一个handler用于做倒计时操作 弱引用
    public static class MyHandler extends Handler {
        //弱引用：
        public final WeakReference<WelcomeActivity> mWeakReference;

        public MyHandler(WelcomeActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            WelcomeActivity activity = mWeakReference.get();
            //接受第一次发过来的消息
            if (msg.what == CODE) {
                if (activity != null) {

                int time = msg.arg1;//剩下的计时时间，第一次接受的是发过来的倒计时的总时间 TOTAI_TIME
                    activity.timeTv.setText(time/INTERVAL_TIME+activity.getString(R.string.left_over_time));//设置控件
                //收到后再自己给自己发一个消息
                    Message message = Message.obtain();
                    message.what=CODE;
                    message.arg1=time-INTERVAL_TIME;//每次发送的时间为剩下的时间(总剩下的时间-间隔时间)
                    if(time>0){//time 剩下的计时时间发送才有意义，不然不需要计时
                        sendMessageDelayed(message,INTERVAL_TIME);//每次间隔一秒发送
                    }else {
                        //倒计时完成，进行下一步操作
                        activity.startActivity(new Intent(activity,MainActivity.class));
                        activity.finish();
                    }
                }
            }
        }
    }
}
