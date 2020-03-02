package com.example.eventbusdemo.eventbus.mode.background;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.eventbusdemo.eventbus.mode.mainordered.MainOrDeredEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EventBusBackgroundModeSherFragmentDialog extends DialogFragment {
    private static final String TAG = "PublicSherFragmentDialo-X";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("BackgroundMode");//标题，消息发布者
        String[] items = new String[]{"主线程发布", "子线程发布"};//显示的是一个列表
        builder.setItems(items, new DialogInterface.OnClickListener() {//设置点击事件，当点击的时候发送对应的消息，反馈给Ui设置对应的状态给用户
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        // 如果点击的是成功的按钮，把成功的信息通过EventBus通知到主界面
                        EventBus.getDefault().post(new BackgroundEvent(Thread.currentThread().toString()));
                        break;
                    case 1:
                        // 如果点击的是失败的按钮，把失败的信息通过EventBus通知到主界面
                        ExecutorService executors=  Executors.newFixedThreadPool(1);
                        executors.submit(new Runnable() {
                            @Override
                            public void run() {
                                EventBus.getDefault().post(new BackgroundEvent(Thread.currentThread().toString()));
                            }
                        });
                        executors.shutdown();
                        break;
                }
            }
        });

        return builder.create();//创建对话框
    }


}