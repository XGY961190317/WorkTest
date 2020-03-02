package com.example.eventbusdemo.eventbus.sticky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.eventbusdemo.R;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubscriberStickyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber_sticky);
        findViewById(R.id.subscriber_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubscriberStickyActivity.this,PublisherStickyActivity.class);
                EventBus.getDefault().postSticky(new StickyEvent("发送的信息是：这个信息通过EventBus粘性事件(Sticky)发送的，可以实现先发送，再订阅！"));
                startActivity(intent);
            }
        });
    }
}
