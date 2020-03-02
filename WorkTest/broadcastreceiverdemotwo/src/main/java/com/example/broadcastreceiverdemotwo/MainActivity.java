package com.example.broadcastreceiverdemotwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver mbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView resultTv = findViewById(R.id.receiver_tv);
        mbr = new MyBroadcastReceiver(resultTv);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastreceiverdemo.MY_ACTION");
        registerReceiver(mbr,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mbr);
    }
}
