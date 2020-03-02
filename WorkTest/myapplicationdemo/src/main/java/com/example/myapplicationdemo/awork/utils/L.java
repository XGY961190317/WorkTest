package com.example.myapplicationdemo.awork.utils;

import android.util.Log;

import com.example.myapplicationdemo.BuildConfig;

/**
 * 用于处理log
 */
public class L {
   private static final boolean isDebug = BuildConfig.DEBUG;
    public static void d(String tag, String msg){
        if(isDebug){
            Log.d(tag,msg);
        }
    }
}
