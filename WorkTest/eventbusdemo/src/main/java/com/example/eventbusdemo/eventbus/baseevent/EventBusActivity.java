package com.example.eventbusdemo.eventbus.baseevent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.broadcastreceiver.BroadcastReceiverSherFragmentDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EventBusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
        setListenver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void setListenver() {
        findViewById(R.id.eventbus_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusSherFragmentDialog fragmentDialog = new EventBusSherFragmentDialog();
                fragmentDialog.show(getSupportFragmentManager(), "eventbus");
            }
        });
    }

    @Subscribe
    public void onSuccessEvten(SuccessEvent event) {
        setImgSrc(R.drawable.ic_success);
    }

    @Subscribe
    public void onFailEvent(FailEvent event) {
        setImgSrc(R.drawable.ic_fail);
    }

    /**
     * 当收到信息后，设置img图片显示
     *
     * @param resId 图片资源的id
     */
    private void setImgSrc(int resId) {
        ImageView img = findViewById(R.id.eventbus_disimg);
        img.setImageResource(resId);//成功时显示 success   //当失败的时候显示fail
    }
}
