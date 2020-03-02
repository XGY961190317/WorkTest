package com.example.handlerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.handlerdemo.countdown.CountdownActivity;
import com.example.handlerdemo.diglett.DiglettActivity;
import com.example.handlerdemo.download.DownloadActivity;
import com.example.handlerdemo.downloadimg.DownLoadimgActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity-X";
    private Button btnDwon;
    private Button btnCountDown;
    private Button btnDiglett;
    private Button btnDownloadimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }


    private void initView() {
        btnDwon = findViewById(R.id.btn_download);
        btnCountDown = findViewById(R.id.btn_countdown);
        btnDiglett = findViewById(R.id.btn_diglett);
        btnDownloadimg = findViewById(R.id.btn_downloadimg);
    }

    private void setListener() {
        btnDwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DownloadActivity.class));
            }
        });
        btnCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CountdownActivity.class));
            }
        });
        btnDiglett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DiglettActivity.class));
            }
        });
        btnDownloadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DownLoadimgActivity.class));
            }
        });
    }
}
