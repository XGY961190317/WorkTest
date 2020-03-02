package com.example.onetob.diancandemo.viewpagerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.onetob.diancandemo.viewpagerdemo.module1.ViewPagerDemo1Activity;
import com.example.onetob.diancandemo.viewpagerdemo.module2.ViewPagerDemo2Activity;
import com.example.onetob.diancandemo.viewpagerdemo.usermolder1.UserViewPagerDemo1Activity;
import com.example.onetob.diancandemo.viewpagerdemo.usermolder2.UserViewPagerDemo2Activity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity_X";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void myOnClick(View view) {
        switch (view.getId()){
            case R.id.btn_demo1:
startActivity(new Intent(MainActivity.this, ViewPagerDemo1Activity.class));
                break;
            case R.id.btn_demo2:
                startActivity(new Intent(MainActivity.this, ViewPagerDemo2Activity.class));
                break;
            case R.id.btn_demo3:
                startActivity(new Intent(MainActivity.this,UserViewPagerDemo1Activity.class));
                break;
            case R.id.btn_demo4:
                startActivity(new Intent(MainActivity.this,UserViewPagerDemo2Activity.class));
                    break;
        }
    }
}
