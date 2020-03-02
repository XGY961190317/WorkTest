package com.example.handlerdemo.utils;

import android.util.Log;

import com.example.handlerdemo.BuildConfig;

public class L {
    public static  boolean isDebug=BuildConfig.DEBUG;


    public static void d(String tag,String msg){
        if(isDebug){
            Log.d(tag,msg);
        }
    }
}
