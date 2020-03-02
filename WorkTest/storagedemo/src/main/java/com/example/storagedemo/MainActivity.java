package com.example.storagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.storagedemo.external.ExternalActivity;
import com.example.storagedemo.external.ExternalActivityDemo;
import com.example.storagedemo.internalstorage.InternalActivity;
import com.example.storagedemo.shared.SharedActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity-X";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.share_btn:
                startActivity(new Intent(MainActivity.this,SharedActivity.class));
                break;

            case R.id.external_btn:
                startActivity(new Intent(MainActivity.this,ExternalActivityDemo.class));
                break;
            case R.id.interal_btn:
                startActivity(new Intent(MainActivity.this,InternalActivity.class));
                break;
        }
    }
}
