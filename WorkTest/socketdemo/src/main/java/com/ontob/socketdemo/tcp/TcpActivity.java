package com.ontob.socketdemo.tcp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ontob.socketdemo.R;
import com.ontob.socketdemo.tcp.biz.TcpClientBiz;
import com.ontob.socketdemo.udp.biz.UdpClientBiz;

import androidx.appcompat.app.AppCompatActivity;

public class TcpActivity extends AppCompatActivity {

    private EditText etMsg;
    private Button btnSend;
    private TextView tvContent;
    private TcpClientBiz mTcpClientBiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mTcpClientBiz = new TcpClientBiz();
        mTcpClientBiz.setOnMsgComingListener(new TcpClientBiz.OnMsgComingListener() {
            @Override
            public void onMsgReturned(String returnMsg) {
                tvContent.setText(returnMsg);
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }
        });
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
                mTcpClientBiz.sendMsg(msg);

            }
        });
    }
    private void appenMsgToContent(String msg){
        tvContent.setText(msg+"\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTcpClientBiz.onDestroy();
    }
}
