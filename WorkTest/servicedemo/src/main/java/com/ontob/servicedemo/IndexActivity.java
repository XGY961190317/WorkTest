package com.ontob.servicedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ontob.servicedemo.demo.TestActivity;
import com.ontob.servicedemo.work.activity.MainActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IndexActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    public void toMainActivity(View v) {
startActivity(new Intent(this,MainActivity.class));
    }

    public void toTestActivity(View v) {
        startActivity(new Intent(this,TestActivity.class));
    }
}
