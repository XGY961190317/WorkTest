package com.example.storagedemo.shared;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.storagedemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SharedActivity extends AppCompatActivity {

    private EditText nameEt;
    private EditText pwdEt;
    private CheckBox checkBox;
    private Button loginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);
        initView();
        getSharedData();
        setListener();
    }

    private void getSharedData() {
        //第一次进来，先去读取shared的数据，有数据，则直接显示到输入框
        //获取Shared
        //参数1：文件名，没有则创建，有则打开
        SharedPreferences sharedPreferences = getSharedPreferences("myshared", MODE_PRIVATE);
        //获取数据
        //参数1：数据的key值，参数2：如果ke不存在，返回的默认值
        String name = sharedPreferences.getString("userName", "");
        String pwe = sharedPreferences.getString("userPwd", "");
        //获得数据以后，设置到输入框
        nameEt.setText(name);
        pwdEt.setText(pwe);
    }

    private void initView() {
        nameEt = findViewById(R.id.user_name);
        pwdEt = findViewById(R.id.user_pwd);
        checkBox = findViewById(R.id.checkbox);
        loginBtn = findViewById(R.id.login_btn);
    }

    private void setListener() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的值，
                String name = nameEt.getText().toString();
                String pwd = pwdEt.getText().toString();
                if (name.equals("123") && pwd.equals("123")) {//验证密码
                    //判断知否保存密码
                    if (checkBox.isChecked()) {//如果选择了保存密码，则保存到Shared
                        //获取SharedPreferences
                        //参数1：文件名，参数2：模式
                        SharedPreferences sharedPreferences = getSharedPreferences("myshared", MODE_PRIVATE);
                        //获取Editor
                        Editor editor = sharedPreferences.edit();
                        //储存数据
                        editor.putString("userName", name);
                        editor.putString("userPwd", pwd);
                        //提交数据
                        editor.commit();
                    }
                    Toast.makeText(SharedActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SharedActivity.this, "账号或密码不正确", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
