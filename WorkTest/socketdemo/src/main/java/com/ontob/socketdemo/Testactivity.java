package com.ontob.socketdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ontob.socketdemo.https.Http2Activity;
import com.ontob.socketdemo.https.HttpActivity;
import com.ontob.socketdemo.tcp.TcpActivity;
import com.ontob.socketdemo.udp.UdpActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Testactivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void onClickBtn(View v) {
        switch (v.getId()){
           case R.id.udp_test:
            startActivity(new Intent(this,UdpActivity.class));
            break;
            case R.id.tcp_test:
                startActivity(new Intent(this,TcpActivity.class));
                break;
            case R.id.http_test:
                startActivity(new Intent(this,HttpActivity.class));
                break;
            case R.id.http2_test:
                startActivity(new Intent(this,Http2Activity.class));
                break;
        }
    }
}
