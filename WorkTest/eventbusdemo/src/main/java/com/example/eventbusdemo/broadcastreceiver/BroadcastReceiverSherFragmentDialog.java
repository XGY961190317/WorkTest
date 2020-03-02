package com.example.eventbusdemo.broadcastreceiver;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.IntentService;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class BroadcastReceiverSherFragmentDialog extends DialogFragment {
    private static final String TAG = "PublicSherFragmentDialo-X";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("broadsher");//标题，消息发布者
        String[] items = new String[]{"Success", "Faill"};//显示的是一个列表
        builder.setItems(items, new DialogInterface.OnClickListener() {//设置点击事件，当点击的时候发送对应的消息，反馈给Ui设置对应的状态给用户
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        // 如果点击的是成功的按钮，把成功的信息通过广播通知到主界面
                        Intent intentSuccess = new Intent();
                        intentSuccess.setAction(BroadcastReceiverActivity.EVEN_DEMO_ACTION);
                        intentSuccess.putExtra(BroadcastReceiverActivity.MAG_FLAG, true);
                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intentSuccess);//发送广播
                        break;
                    case 1:
                        // 如果点击的是失败的按钮，把失败的信息通过广播通知到主界面
                        Intent intentFail = new Intent();
                        intentFail.setAction(BroadcastReceiverActivity.EVEN_DEMO_ACTION);
                        intentFail.putExtra(BroadcastReceiverActivity.MAG_FLAG, false);
                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intentFail);//发送广播
                        break;
                }
            }
        });

        return builder.create();//创建对话框
    }


}