package com.ontob.servicedemo.work.application;

import android.app.Application;

import com.ontob.servicedemo.work.service.InitApkInfoService;

public class ApkInfoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InitApkInfoService.startService(getApplicationContext());
    }
}
