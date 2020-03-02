package com.example.eventbusdemo.eventbus.mode.psotingevent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventbusdemo.IndexActivity;
import com.example.eventbusdemo.R;
import com.example.eventbusdemo.broadcastreceiver.BroadcastReceiverSherFragmentDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EventBusPsotingModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psotingmode_eventbus);
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
        findViewById(R.id.psotingmode_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusPsotingModeSherFragmentDialog fragmentDialog = new EventBusPsotingModeSherFragmentDialog();
                fragmentDialog.show(getSupportFragmentManager(), "PsotingMode");
            }
        });
    }

    //订阅回调函数
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPsotingEvent(PsotingEvent event) {
        final String subscriberThreadinfo = event.threadInfo;//获取发布方线程信息
        final String publisher = Thread.currentThread().toString();//获取订阅方线程信息
        // Log.d(IndexActivity.TAG, "subscriberThreadinfo=" + subscriberThreadinfo);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setPublisherThreadInfo(publisher);//设置订阅方线程信息
                setSubscribersherThreadInfo(subscriberThreadinfo);
                Log.d(IndexActivity.TAG, "subscriberThreadinfo=" + subscriberThreadinfo + "  PublisherThreadInfo= " + Thread.currentThread().toString());
            }
        });
    }

    //设置textView显示PublisherThreadInfo的方法：
    private void setPublisherThreadInfo(String threadInfo) {
        setTextView(R.id.psoting_publicsherThreadTextView, threadInfo);
    }

    //设置textView显示SubscribersherThreadInfo的方法：
    //要运行在UI线程
    private void setSubscribersherThreadInfo(String threadInfo) {
        setTextView(R.id.psoting_subscriberThreadTextView, threadInfo);
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
