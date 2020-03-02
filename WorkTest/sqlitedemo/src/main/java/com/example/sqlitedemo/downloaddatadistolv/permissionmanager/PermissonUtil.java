package com.example.sqlitedemo.downloaddatadistolv.permissionmanager;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;


import com.example.sqlitedemo.downloaddatadistolv.WorkTestActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class PermissonUtil {
    public static boolean getPermission(Context context, String[] permission, int requestCode) {
        List<String> mPermissionList = new ArrayList<>();
        for (int i = 0; i < permission.length; i++) {
            if (ContextCompat.checkSelfPermission(context, permission[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permission[i]);
            }
            if(mPermissionList.size()>0){
                String[] deniedPermissions =  mPermissionList.toArray(new String[mPermissionList.size()]);
                ActivityCompat.requestPermissions((Activity) context, deniedPermissions, requestCode);
            } else{
                return true;
            }

        }
        return false;
    }
}
