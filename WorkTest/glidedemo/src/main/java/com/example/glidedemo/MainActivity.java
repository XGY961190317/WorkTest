package com.example.glidedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadImgerOnClick(View view) {
        switch (view.getId()){
            case R.id.native_netBtn:
                startActivity(new Intent(MainActivity.this,NativeGetNetImageActivity.class));
                break;
            case R.id.glide_netBtn:
                startActivity(new Intent(MainActivity.this,GlideGetNetImageActivity.class));
                break;
        }
    }
}
