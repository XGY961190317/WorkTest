package com.example.netgetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.netgetdemo.utils.GetDataentity;
import com.example.netgetdemo.utils.NetTootls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.netgetdemo.utils.Tootls.decode;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity-x";
    private Button btnGet;
    private Button btnJson;
    private TextView tvDis;
    private String mResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnGet = findViewById(R.id.btn_get);
        btnJson = findViewById(R.id.btn_json);
        tvDis = findViewById(R.id.dis_data);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //请求网络
                //在子线程里面操作
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String urlString = "http://www.imooc.com/api/teacher?type=2&page=1";
                        mResult = NetTootls.requestDataByGet(urlString);//请求网络
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mResult = decode(mResult);
                             GetDataentity getDataentity =  NetTootls.getStringToJson(mResult);//解析数据
                                tvDis.setText(getDataentity.toString());
                            }
                        });
                    }
                }).start();
            }
        });
    }






}
