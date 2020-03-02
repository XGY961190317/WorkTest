package com.ontob.socketdemo.udp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ontob.socketdemo.R;
import com.ontob.socketdemo.udp.biz.UdpClientBiz;

public class UdpActivity extends AppCompatActivity {

    private EditText etMsg;
    private Button btnSend;
    private TextView tvContent;
    private UdpClientBiz mUdpClientBiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mUdpClientBiz = new UdpClientBiz();

    }

    private void initView() {
        etMsg = findViewById(R.id.et_msg);
        btnSend = findViewById(R.id.btn_send);
        tvContent = findViewById(R.id.tv_content);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = etMsg.getText().toString();
                if(msg==null){
                    return;
                }
                appenMsgToContent("client:"+msg);
                etMsg.setText("");
                    mUdpClientBiz.sendMsg(msg, new UdpClientBiz.OnMsgReturnedListener() {
                        @Override
                        public void onMsgReturned(String returnMsg) {
                            appenMsgToContent("server:"+returnMsg);
                        }

                        @Override
                        public void onError(Exception ex) {
                            ex.printStackTrace();
                        }
                    });

            }
        });
    }
    private void appenMsgToContent(String msg){
        tvContent.setText(msg+"\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUdpClientBiz.onDestroy();
    }
}
