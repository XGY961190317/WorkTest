package com.ontob.servicedemo.work.baez;

import android.graphics.drawable.Drawable;

public class ApkBaseInfo {//应用信息
    private String mApkName;//apkname
    private Drawable mApkIcon;//图标
    private String mApkPackageName;//包名

    public String getmApkName() {
        return mApkName;
    }

    public void setmApkName(String mApkName) {
        this.mApkName = mApkName;
    }

    public Drawable getmApkIcon() {
        return mApkIcon;
    }

    public void setmApkIcon(Drawable mApkIcon) {
        this.mApkIcon = mApkIcon;
    }

    public String getmApkPackageName() {
        return mApkPackageName;
    }

    public void setmApkPackageName(String mApkPackageName) {
        this.mApkPackageName = mApkPackageName;
    }
}
