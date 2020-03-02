package com.example.broadcastreceiverdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.example.broadcastreceiverdemo.R;
import com.example.broadcastreceiverdemo.receiver.MovingBroadcastReceiverTest;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getPackageName());
    }


    public void testOnclick(View view) {
        switch (view.getId()){
            case R.id.moving_broadcast_receiver_test:
                startActivity(new Intent(MainActivity.this,MovingTestActivity.class));
                break;
            case R.id.user_broadcast_receiver_test:
                startActivity(new Intent(MainActivity.this,UserTestActivity.class));
                break;
        }
    }
}
