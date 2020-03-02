package com.example.broadcastreceiverdemo.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.broadcastreceiverdemo.R;
import com.example.broadcastreceiverdemo.receiver.UserBroadcastReceiverTest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserTestActivity  extends AppCompatActivity {
    public  static  String TAG="UserBroadcastReceiverTe-X";
    public static final String MY_ACTION="com.example.broadcastreceiverdemo.MY_ACTION";
    public static final  String MY_BROADCAST_MSG = "user_broadcast_msg";
    private TextView resultTv;
    private UserBroadcastReceiverTest ubrt;
    private UserBroadcastReceiverTest mBroadcastReceiver;
    private EditText inputEt;
    private Button sendBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();



        //创建BroadcastReceiver
        ubrt = new UserBroadcastReceiverTest(resultTv);
        //创建intentfilter
        IntentFilter intentFilter = new IntentFilter();
        //添加要接受的自定义action
        intentFilter.addAction(MY_ACTION);
        registerReceiver(ubrt,intentFilter);
        //点击按钮发送BroadcastReceiver
        setListener();


    }

    private void setListener() {
        //点击按钮发送BroadcastReceiver
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建广播
                //通过Intent发送，调用sendBroadcast(Intent intent);方法
                //准备输入的广播数据
                Log.d(TAG,"准备输入的广播数据");
                String inputContent =inputEt.getText().toString();
                Intent intent = new Intent(MY_ACTION);//以自动以action来创建intent
                intent.putExtra("user_broadcast_msg",inputContent);
                Log.d(TAG,"准备输入的广播数据："+inputContent);
                sendBroadcast(intent);
            }
        });
    }

    private void initView() {
        inputEt = findViewById(R.id.input_content);
        sendBtn = findViewById(R.id.send_broadcastbtn);
        resultTv = findViewById(R.id.result_text);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(ubrt!=null){
            unregisterReceiver(ubrt);

        }

    }
}
