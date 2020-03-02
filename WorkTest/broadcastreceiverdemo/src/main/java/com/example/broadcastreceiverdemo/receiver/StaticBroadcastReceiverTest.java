package com.example.broadcastreceiverdemo.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StaticBroadcastReceiverTest extends BroadcastReceiver {
    private static  String TAG = "StaticBroadcastReceiverTest-X";

    @Override
    public void onReceive(Context context, Intent intent) {
        //在这里接受广播的信息
        if(intent!=null){
            //接受到的是什么广播
            String action = intent.getAction();//获取广播的信息
            Log.d(TAG,"onReceiver:"+action);
        }

    }
}
