package com.example.storagedemo.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtile {

    public static boolean requestPermission(Context context, String[] permission,int requestCode) {
        List<String> listPermission = new ArrayList<>();
        for (int i = 0; i < permission.length; i++) {
            if (ContextCompat.checkSelfPermission(context,permission[i]) != PackageManager.PERMISSION_GRANTED)
                ;//判断是否已经获取权限如果没有生气，就添加到列表中，统一申请
            listPermission.add(permission[i]);
        }
        //获取没有被允许权限的所有，并转为一个字符数组，统一申请
        String[] unPermission = new String[listPermission.size()];
        if (listPermission.size() > 0) {
            unPermission = listPermission.toArray(new String[listPermission.size()]);
            //统一申请权限
            ActivityCompat.requestPermissions((Activity) context,unPermission,requestCode);
        } else {
            return true;
        }
        return true;
    }

}
