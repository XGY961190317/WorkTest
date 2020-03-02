package com.example.onetob.diancandemo.ctivitylifecycle;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PassDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_data);
        //获取上一个界面的数据，要先获得保存数据的意图
        Intent it = getIntent();
        String string = it.getStringExtra("msg1");
        int int1 = it.getIntExtra("msg2",0);
        Log.d(MainActivity.TAG,"来自第一个activity的数据"+string+"     "+int1);

       TextView tv =  findViewById(R.id.tv_test);
        tv.setText(string+int1);
       tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();//用来传递数据
                it.putExtra("myMsg","这是传递回第一个界面的数据");
                setResult(RESULT_OK,it);
                finish();
            }
        });
    }
}
