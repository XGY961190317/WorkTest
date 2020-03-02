package com.example.eventbusdemo.eventbus.sticky;

import android.os.Bundle;
import android.widget.TextView;

import com.example.eventbusdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PublisherStickyActivity extends AppCompatActivity {

    private TextView disTv;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher_sticky);
        disTv = findViewById(R.id.dis_subscriber_msg);
    }

    @Subscribe(sticky = true)
    public void onSyickyEvent(StickyEvent event) {
        try {
            Thread.sleep(3000);
            disTv.setText(event.subscriber_msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
