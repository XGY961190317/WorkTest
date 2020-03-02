package com.example.cardviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cardviewdemo.demo.CardDemoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myOnclick(View view) {
        switch (view.getId()) {
            case R.id.cardview_baseinfo:
                startActivity(new Intent(MainActivity.this, CardBaseInfoActivity.class));
                break;
            case R.id.cardview_demo:
                startActivity(new Intent(MainActivity.this, CardDemoActivity.class));
                break;
        }
    }
}
