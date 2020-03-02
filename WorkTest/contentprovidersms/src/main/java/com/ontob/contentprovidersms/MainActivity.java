package com.ontob.contentprovidersms;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity-x";
    private int requestCode = 1;
    private PermissionUtils permissionUtils;
    public static List<String> sNeedReqPermissions = new ArrayList<>();

    static {
        sNeedReqPermissions.add(Manifest.permission.SEND_SMS);
        sNeedReqPermissions.add(Manifest.permission.RECEIVE_SMS);
        sNeedReqPermissions.add(Manifest.permission.READ_SMS);
        sNeedReqPermissions.add(Manifest.permission.RECEIVE_WAP_PUSH);
        sNeedReqPermissions.add(Manifest.permission.RECEIVE_MMS);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //  addPermission();

        permissionUtils = new PermissionUtils(this);
        permissionUtils.requestPermission(sNeedReqPermissions, requestCode, new PermissionUtils.CallBack() {
            @Override
            public void grantAll() {
            }

            @Override
            public void denied() {

            }
        });


    }

    //@RequiresApi(api = Build.VERSION_CODES.M)
    public void myOnClick(View view) {
        switch (view.getId()) {
            case R.id.sms_btn:
//1,获取ContentResolver对象
                ContentResolver resolver = getContentResolver();
                Cursor c = resolver.query(Uri.parse("content://sms"), null, null, null, null);
                while (c.moveToNext()) {
                    String name = c.getString(c.getColumnIndex("address"));
                    String body = c.getString(c.getColumnIndex("body"));
                    Log.d(TAG, "name:" + name + "\n  body:" + body);
                }
                break;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
