package com.example.myapplicationdemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.myapplicationdemo.awork.utils.L;

import java.util.concurrent.TimeUnit;

public class MyService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param
     */
    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        L.d(MainActivity.TAG, "onHandleIntent:" + getApplication() + "  :  " + getApplicationContext() + "   this :    " + this);
//        try {
//            //TimeUnit.SECONDS.sleep(2);
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.d(MainActivity.TAG, "onHandleIntent:" + getApplication() + "  :  " + getApplicationContext() + "   this :    " + this);
    }
}
