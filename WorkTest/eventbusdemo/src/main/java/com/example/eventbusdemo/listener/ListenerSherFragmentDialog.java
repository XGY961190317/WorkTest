package com.example.eventbusdemo.listener;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ListenerSherFragmentDialog extends DialogFragment {
    private static final String TAG = "PublicSherFragmentDialo-X";
    private OnEventListenver mOnEventListenver;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("publisher");//标题，消息发布者
        String[] items = new String[]{"Success", "Faill"};//显示的是一个列表
        builder.setItems(items, new DialogInterface.OnClickListener() {//设置点击事件，当点击的时候发送对应的消息，反馈给Ui设置对应的状态给用户
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        // 如果点击的是成功的按钮，直接回调接口的onSuccess()方法出去，给Ui做处理
                        if (mOnEventListenver != null) {
                            mOnEventListenver.onSuccess(0);
                        }
                        break;
                    case 1:
                        // 如果点击的是失败的按钮，直接回调接口的onFail()方法出去，给Ui做处理
                        if (mOnEventListenver != null) {
                            mOnEventListenver.onFail(1);
                        }
                        break;
                }
            }
        });

        return builder.create();//创建对话框
    }

    /**
     * 设置监听的方法
     *
     * @param eventListenver
     */
    public void setOnEventListenver(OnEventListenver eventListenver) {
        mOnEventListenver = eventListenver;
    }

    public interface OnEventListenver {
        void onSuccess(int index);

        void onFail(int index);
    }
}