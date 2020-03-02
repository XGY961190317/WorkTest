package com.example.broadcastreceiverdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

public class MovingBroadcastReceiverTest extends BroadcastReceiver {
    private static String TAG = "MovingBroadcastReceiver-X";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null) {
            String action = intent.getAction();
            Log.d(TAG, "MovingBroadcastReceiverTest.onReceive:" + action);
        }
    }
}
