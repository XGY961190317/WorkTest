package com.example.myapplicationdemo.myapp;

import android.app.Application;
import android.content.res.Configuration;

import com.example.myapplicationdemo.MainActivity;
import com.example.myapplicationdemo.awork.utils.L;
import com.squareup.otto.Bus;

import androidx.annotation.NonNull;

public class MyApplication extends Application {
//全局共享测试：

    /**
     * 1，在application总设置属性，
     * 2，在activity1中查看值，和在activity2中查看的值对比，看是否一致
     * 3，在activity1中设置值，在activity2中查看，看是否是刚刚设置的值
     */
    /**
     * Application对象vs静态实例：实现共享
     *
     */
    private Bus bus;

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.d(MainActivity.TAG,"onCreate:"+this);
        bus = new Bus();
    }

    /**
     * 系统配置发生变更的时候调用，例如横竖屏，语言变更
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        L.d(MainActivity.TAG,"onConfigurationChanged():called with:"+"newConfig = {"+newConfig+"}");
    }

    /**
     * 系统内存吃紧的时候调用
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        L.d(MainActivity.TAG,"onLowMemory():");
    }
}
