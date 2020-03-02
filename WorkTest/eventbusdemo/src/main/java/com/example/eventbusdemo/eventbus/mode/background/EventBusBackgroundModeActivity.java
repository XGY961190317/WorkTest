package com.example.eventbusdemo.eventbus.mode.background;

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

public class EventBusBackgroundModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgroundmode_eventbus);
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

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackground(final BackgroundEvent event){
        final String publisherTHreadInfo = Thread.currentThread().toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setPublisherThreadInfo(publisherTHreadInfo);
                setSubscribersherThreadInfo(event.threadInfo);
            }
        });
    }
    private void setListenver() {
        findViewById(R.id.backgroundmode_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusBackgroundModeSherFragmentDialog fragmentDialog = new EventBusBackgroundModeSherFragmentDialog();
                fragmentDialog.show(getSupportFragmentManager(), "BackgroundMode");
            }
        });
    }

    //设置textView显示PublisherThreadInfo的方法：
    private void setPublisherThreadInfo(String threadInfo) {
        setTextView(R.id.background_publicsherThreadTextView, threadInfo);
    }

    //设置textView显示SubscribersherThreadInfo的方法：
    //要运行在UI线程
    private void setSubscribersherThreadInfo(String threadInfo) {
        setTextView(R.id.background_subscriberThreadTextView, threadInfo);
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
