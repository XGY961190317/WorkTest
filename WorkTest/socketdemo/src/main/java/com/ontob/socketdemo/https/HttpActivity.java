package com.ontob.socketdemo.https;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ontob.socketdemo.R;
import com.ontob.socketdemo.udp.biz.UdpClientBiz;

import androidx.appcompat.app.AppCompatActivity;

public class HttpActivity extends AppCompatActivity {
    public static final String TAG = "HttpActivity-x";
    private EditText etMsg;
    private Button btnSend;
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        initView();

    }

    private void initView() {
        etMsg = findViewById(R.id.http_et_msg);
        btnSend = findViewById(R.id.http_btn_send);
        tvContent = findViewById(R.id.http_tv_content);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userUrl = etMsg.getText().toString();
                if (userUrl.isEmpty()) {
                    return;
                }
                HttpUtlis.doGet(userUrl, new HttpUtlis.HttpListener() {
                    @Override
                    public void onSuccess(String content) {
                        tvContent.setText(content);
                    }

                    @Override
                    public void onFail(Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
