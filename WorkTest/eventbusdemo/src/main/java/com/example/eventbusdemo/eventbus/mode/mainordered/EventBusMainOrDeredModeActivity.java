package com.example.eventbusdemo.eventbus.mode.mainordered;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventbusdemo.IndexActivity;
import com.example.eventbusdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EventBusMainOrDeredModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainorderedmode_eventbus);
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
        findViewById(R.id.mainorderedmode_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusMainOrDeredModeSherFragmentDialog fragmentDialog = new EventBusMainOrDeredModeSherFragmentDialog();
                fragmentDialog.show(getSupportFragmentManager(), "MainOrDeredMode");
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onMainOrDeredEvent(MainOrDeredEvent event) {
        Log.d(IndexActivity.TAG,"onMainOrDered:enter接收前 @"+SystemClock.uptimeMillis());//开始执行时
        setPublisherThreadInfo(Thread.currentThread().toString());
        setSubscribersherThreadInfo(event.threadInfo);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(IndexActivity.TAG,"onMainOrDered:enter接收后 @"+SystemClock.uptimeMillis());//完成执行时
    }
    //设置textView显示PublisherThreadInfo的方法：
    private void setPublisherThreadInfo(String threadInfo) {
        setTextView(R.id.mainordered_publicsherThreadTextView, threadInfo);
    }

    //设置textView显示SubscribersherThreadInfo的方法：
    //要运行在UI线程
    private void setSubscribersherThreadInfo(String threadInfo) {
        setTextView(R.id.mainordered_subscriberThreadTextView, threadInfo);
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
