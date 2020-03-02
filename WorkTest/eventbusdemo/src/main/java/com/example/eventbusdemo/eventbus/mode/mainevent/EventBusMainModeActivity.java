package com.example.eventbusdemo.eventbus.mode.mainevent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.broadcastreceiver.BroadcastReceiverSherFragmentDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EventBusMainModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmode_eventbus);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent( MainEvent event) {
         String publisher  = Thread.currentThread().toString();
                setPublisherThreadInfo(publisher);
                setSubscribersherThreadInfo(event.threadInfo);
    }
    private void setListenver() {
        findViewById(R.id.mainmode_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusMainModeSherFragmentDialog fragmentDialog = new EventBusMainModeSherFragmentDialog();
                fragmentDialog.show(getSupportFragmentManager(), "MainMode");
            }
        });
    }

    //设置textView显示PublisherThreadInfo的方法：
    private void setPublisherThreadInfo(String threadInfo) {
        setTextView(R.id.main_publicsherThreadTextView, threadInfo);
    }

    //设置textView显示SubscribersherThreadInfo的方法：
    //要运行在UI线程
    private void setSubscribersherThreadInfo(String threadInfo) {
        setTextView(R.id.main_subscriberThreadTextView, threadInfo);
    }

    /**
     * 当收到信息后，设置TextView显示Thread信息
     *
     * @param textViewId TextView控件的id
     */
    private void setTextView(int textViewId, String threadInfo) {
        TextView textView = findViewById(textViewId);
        textView.setText(threadInfo);//显示线程信息
        textView.setAlpha(.5f);
        textView.animate().alpha(1).start();
    }
}
