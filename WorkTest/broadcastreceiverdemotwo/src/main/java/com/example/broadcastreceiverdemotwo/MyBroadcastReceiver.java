package com.example.broadcastreceiverdemotwo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private TextView mTextView;
    public MyBroadcastReceiver() {
    }
    public MyBroadcastReceiver(TextView tv) {
        mTextView = tv;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent!=null){
        if(TextUtils.equals(intent.getAction(),"com.example.broadcastreceiverdemo.MY_ACTION")){
            mTextView.setText("这是接收到的广播传来的信息:"+intent.getStringExtra("user_broadcast_msg"));
        }
        }
    }
}
