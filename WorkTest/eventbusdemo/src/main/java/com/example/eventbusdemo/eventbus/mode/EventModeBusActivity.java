package com.example.eventbusdemo.eventbus.mode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.eventbus.mode.async.EventBusAsyncModeActivity;
import com.example.eventbusdemo.eventbus.mode.background.EventBusBackgroundModeActivity;
import com.example.eventbusdemo.eventbus.mode.mainevent.EventBusMainModeActivity;
import com.example.eventbusdemo.eventbus.mode.mainordered.EventBusMainOrDeredModeActivity;
import com.example.eventbusdemo.eventbus.mode.psotingevent.EventBusPsotingModeActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EventModeBusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventmode);
    }
    public void onClickEventModeBtn(View v) {
        switch (v.getId()) {
            case R.id.mode_psoting:
                startActivity(new Intent(EventModeBusActivity.this,EventBusPsotingModeActivity.class));
                break;
            case R.id.mode_main:
                startActivity(new Intent(EventModeBusActivity.this,EventBusMainModeActivity.class));
                break;
            case R.id.mode_main_ordered:
                startActivity(new Intent(EventModeBusActivity.this,EventBusMainOrDeredModeActivity.class));
                break;
            case R.id.mode_background:
                startActivity(new Intent(EventModeBusActivity.this,EventBusBackgroundModeActivity.class));
                break;
            case R.id.mode_async:
                startActivity(new Intent(EventModeBusActivity.this,EventBusAsyncModeActivity.class));
                break;
        }
    }
}
