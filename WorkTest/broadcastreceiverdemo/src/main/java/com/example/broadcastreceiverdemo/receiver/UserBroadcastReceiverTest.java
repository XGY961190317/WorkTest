package com.example.broadcastreceiverdemo.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.example.broadcastreceiverdemo.activity.UserTestActivity;


public class UserBroadcastReceiverTest extends BroadcastReceiver {
    private TextView mTextView;
    public UserBroadcastReceiverTest() {
    }

    public UserBroadcastReceiverTest(TextView textView) {
        this.mTextView = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
             Log.d(UserTestActivity.TAG,"intent:"+intent.getAction());
    //判断是否接收到广播
            if(intent !=null){
        //判断接收到的是什么广播：
        Log.d(UserTestActivity.TAG,"intent的值:"+intent.getAction());
        if(TextUtils.equals(intent.getAction(),UserTestActivity.MY_ACTION)){
            String content = intent.getStringExtra(UserTestActivity.MY_BROADCAST_MSG);
            if(mTextView!=null){
                mTextView.setText("接收到的action是:\n"+intent.getAction()+"\n  接收到的内容是:"+content);
            }

        }
    }
    }

}
