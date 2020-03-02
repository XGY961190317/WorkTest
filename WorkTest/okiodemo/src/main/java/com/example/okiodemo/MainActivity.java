package com.example.okiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.okiodemo.okhttp.OkHttpTestActivity;
import com.example.okiodemo.okio.OkioTestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtn(View view) {
        switch (view.getId()){
            case R.id.okio_btn:
                startActivity(new Intent(MainActivity.this,OkioTestActivity.class));
                break;
            case R.id.okhttp_btn:
                startActivity(new Intent(MainActivity.this,OkHttpTestActivity.class));
                break;
        }
    }
}

