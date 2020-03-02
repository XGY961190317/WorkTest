package com.example.onetob.diancandemo.ctivitylifecycle;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PassOBJDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_objdata);
        //获取序列化对象
        Intent it = getIntent();
        Student stu = (Student) it.getSerializableExtra("stu");
        TextView tv = findViewById(R.id.tv_test);
        tv.setText("学生姓名："+stu.getName()+" 年龄"+stu.getAge()+" 性别："+stu.getSex());
    }
}

