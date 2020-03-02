package com.example.onetob.diancandemo.listviewdemo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myOnclick(View view) {
        switch (view.getId()) {
            case R.id.simple_btn:
                startActivity(new Intent(MainActivity.this, SimpleActivity.class));
                break;
            case R.id.array_btn:
                startActivity(new Intent(MainActivity.this, ArrayActivity.class));
                break;
            case R.id.base_btn:
                startActivity(new Intent(MainActivity.this, BaseActivity.class));
                break;
        }
    }
}
