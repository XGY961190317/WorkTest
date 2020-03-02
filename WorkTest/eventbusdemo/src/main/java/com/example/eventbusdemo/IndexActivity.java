package com.example.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.eventbusdemo.broadcastreceiver.BroadcastReceiverActivity;
import com.example.eventbusdemo.eventbus.baseevent.EventBusActivity;
import com.example.eventbusdemo.eventbus.sticky.SubscriberStickyActivity;
import com.example.eventbusdemo.listener.MainActivity;
import com.example.eventbusdemo.eventbus.mode.EventModeBusActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IndexActivity extends AppCompatActivity {
    public static final String TAG = "IndexActivity-x";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    public void onClickBtn(View v) {
        switch (v.getId()) {
            case R.id.listener:
                startActivity(new Intent(IndexActivity.this,MainActivity.class));
                break;
            case R.id.broadcastreceiver:
                startActivity(new Intent(IndexActivity.this,BroadcastReceiverActivity.class));
                break;
            case R.id.eventbus:
                startActivity(new Intent(IndexActivity.this,EventBusActivity.class));
                break;
            case R.id.eventbus_mode:
                startActivity(new Intent(IndexActivity.this,EventModeBusActivity.class));
                break;
            case R.id.eventbus_sticky:
                startActivity(new Intent(IndexActivity.this,SubscriberStickyActivity.class));
                break;
        }
    }
}
