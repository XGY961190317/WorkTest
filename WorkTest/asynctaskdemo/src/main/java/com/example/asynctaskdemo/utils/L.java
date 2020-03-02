package com.example.asynctaskdemo.utils;

import android.util.Log;

import com.example.asynctaskdemo.BuildConfig;

public class L {
    public static final boolean isDebug = BuildConfig.DEBUG;
    public static void d(String tag,String msg){
        if(isDebug){
            Log.d(tag,msg);
        }

    }
}
