package com.example.onetob.diancandemo.ctivitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CanRetrunDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_retrun_data);
        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置结果
                Intent intent = new Intent();
                intent.putExtra("userName","这是来自第二个界面的信息");
                //参数1，结果码在上一个界面中的onActivityResult()方法中通过resultCode获取
                //参数2，意图对象  主要作用，存放数据，在上一个界面中的onActivityResult()方法中通过data获取
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
