package com.example.handlerdemo.countdown;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.handlerdemo.R;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CountdownActivity extends AppCompatActivity {

    public static final int WHAT_CODE = 1000;//倒计时code
    public static final int COUNTDOWN_TIME = (int) (1000*3600*26+3700*1000);//倒计时最大值
   // public static final int COUNTDOWN_TIMER =  System.currentTimeMillis();
    public static final int INTERVALS = 1000;//倒计时间隔
    public static final int TIME_UNIT = 60;
    private TextView countDownTv;
    private MyHandler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        countDownTv = findViewById(R.id.countdwon_tv);
        setCountDownView(CountdownActivity.this);
    }

    private void setCountDownView(CountdownActivity activity) {
       // L.d(MainActivity.TAG,String.valueOf(COUNTDOWN_TIMER));
        handler = new MyHandler(activity);
//第一次进来，先发一个消息给handler，确定倒计时的总时间
        Message msg = Message.obtain();
        msg.what= WHAT_CODE;
        msg.arg1 = COUNTDOWN_TIME;
        handler.sendMessage(msg);
    }
    //自定义一个handler，使用弱引用
    public static class MyHandler extends Handler {
       final WeakReference<CountdownActivity> mWeakReference;

        public MyHandler(CountdownActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            CountdownActivity activity = mWeakReference.get();
            if(activity!=null){
                switch (msg.what) {
                    case WHAT_CODE:
                        int resultTime =  msg.arg1;//第一次是设置的时间，第二次是总时间COUNTDOWN_TIME-INTERVALS，第三次是COUNTDOWN_TIME-INTERVALS（arg1剩下的总时间）-INTERVALS
                        int textSetTime_Day = msg.arg1/INTERVALS/TIME_UNIT/TIME_UNIT/24%30;//取天
                        int textSetTime_H = msg.arg1/INTERVALS/TIME_UNIT/TIME_UNIT%24;//取时
                        int textSetTime_M =msg.arg1/INTERVALS/TIME_UNIT%TIME_UNIT;//取分
                        int textSetTime_S = msg.arg1/INTERVALS%TIME_UNIT;//取秒
                        activity.countDownTv.setText(textSetTime_Day+"天"+textSetTime_H+"时"+textSetTime_M+"分"+textSetTime_S+"秒");//设置倒计时时间
                        //然后再发消息给自己：
                        //间隔1秒发一次
                        //每次发剩下的时间（每次减掉时间间隔1秒）
                        if(resultTime>0){
                            Message message = Message.obtain();
                            message.what=WHAT_CODE;
                            message.arg1= (resultTime-INTERVALS);
                            activity.handler.sendMessageDelayed(message, INTERVALS);
                        }else {
                            //倒计时完成，进行下一步操作
                           // activity.startActivity(new Intent(activity,MainActivity.class));
                            //activity.finish();
                        }

                        break;
                }
            }

        }
    }
}
