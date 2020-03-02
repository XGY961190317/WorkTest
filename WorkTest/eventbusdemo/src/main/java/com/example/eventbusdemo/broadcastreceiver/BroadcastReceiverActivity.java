package com.example.eventbusdemo.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.eventbusdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class BroadcastReceiverActivity extends AppCompatActivity {
    public static final String EVEN_DEMO_ACTION = "event_demo_action";
    public static final String MAG_FLAG = "receive_msg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastreceiver);
        setListenver();

    }

    private void setListenver() {
        findViewById(R.id.broadcast_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BroadcastReceiverSherFragmentDialog fragmentDialog = new BroadcastReceiverSherFragmentDialog();
                fragmentDialog.show(getSupportFragmentManager(), "broadsher");
            }
        });
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {//获取广播接收则实例
        @Override
        public void onReceive(Context context, Intent intent) {
                //在这里接受消息
            if(intent!=null){
                boolean booleanExtra = intent.getBooleanExtra(MAG_FLAG, false);
                    setImgSrc(booleanExtra);
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(EVEN_DEMO_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,intentFilter);//通过广播管理器注册广播接收者
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);//通过广播管理器销毁广播接收者
    }


    /**
     * 设置图片显示的方法
     *
     * @param isSuccess 图片的资源显示成功的图片还是失败的图片
     */
    private void setImgSrc(boolean isSuccess) {
        ImageView img = findViewById(R.id.broadcast_disimg);
        if(isSuccess){
            img.setImageResource(R.drawable.ic_success);
        }else {
            img.setImageResource(R.drawable.ic_fail);
        }


    }
}
