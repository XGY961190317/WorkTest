package com.example.broadcastreceiverdemo.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.broadcastreceiverdemo.receiver.MovingBroadcastReceiverTest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

class MovingTestActivity  extends AppCompatActivity {

    private MovingBroadcastReceiverTest mbr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例化一个BroadcastReceiver
        mbr = new MovingBroadcastReceiverTest();
        //实例化一个intentfilter
        IntentFilter intentFilter = new IntentFilter();
        //准备action
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);//下载app的广播
        intentFilter.addAction(Intent.ACTION_PACKAGE_INSTALL);//install app的广播
        //安装和卸载APP广播需要指定数据类型
        intentFilter.addDataScheme("package");
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);//开机的广播
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);//低电量的广播
        //注册广播
        //广播和action准备好了之后，要调用registerReceiver(）注册广播
        /**
         * 参数1：广播接受者 BroadcastReceiver
         * 参数2：action的intent
         */
        registerReceiver(mbr,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mbr);//注销广播
    }
}
