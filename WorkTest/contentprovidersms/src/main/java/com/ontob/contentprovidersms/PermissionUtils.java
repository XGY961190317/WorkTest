package com.ontob.contentprovidersms;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class PermissionUtils {
    private Activity mActivity;
    private int mRequestCode;
    private CallBack mCallBack;

    public PermissionUtils(Activity activity) {
        mActivity = activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermission(List<String> needPermissions, int requestCode, CallBack callBack) {
        Log.d(MainActivity.TAG,"requestPermission");
        if (Build.VERSION.SDK_INT < 23) {
            callBack.grantAll();//sdk23以下默认授权，不需要动态授予
            Log.d(MainActivity.TAG,"requestPermission  < 23");
            return;
        }
        if (mActivity == null) {
            Log.d(MainActivity.TAG,"requestPermission mActivity == null");
            throw new IllegalArgumentException("activity is null");

        }
        mRequestCode = requestCode;
        mCallBack = callBack;
        List<String> resultPermissions = new ArrayList<>();//用于保存没有被授予的权限的集合
        for (String permission : needPermissions) {
            if (mActivity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                resultPermissions.add(permission);
            }
        }
        if (resultPermissions.isEmpty()) {
            Log.d(MainActivity.TAG,"resultPermissions == null");
            callBack.grantAll();
        }
        mActivity.requestPermissions(resultPermissions.toArray(new String[]{}), mRequestCode);
    }

    //回调授权结果
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        String d = null;
        if (requestCode == mRequestCode) {
            boolean grantAll = true;
            for (int resultCode : grantResults) {
                if (resultCode != PackageManager.PERMISSION_GRANTED) {
                   d =   permissions[requestCode];
                    grantAll = false;
                   // Toast.makeText(mActivity, permissions[resultCode] + " 未授权", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            if (grantAll) {
                mCallBack.grantAll();
            } else {
                Log.d("TAG","没通过的");
                if(d!=null){

                    mCallBack.denied();
                }

            }
        }
    }

    public interface CallBack {
        void grantAll();

        void denied();
    }
}
