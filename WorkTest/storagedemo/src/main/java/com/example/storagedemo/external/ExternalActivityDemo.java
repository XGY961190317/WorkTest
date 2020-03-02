package com.example.storagedemo.external;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.storagedemo.MainActivity;
import com.example.storagedemo.R;
import com.example.storagedemo.permission.PermissionUtile;
import com.example.storagedemo.permissions.DialogUtil;
import com.example.storagedemo.permissions.PermissionHelper;
import com.example.storagedemo.permissions.PermissionInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ExternalActivityDemo extends AppCompatActivity{

    private int REQUESTCODE = 1000;
    private EditText editText;
    private TextView disTv;

    String [] permissions = new String []{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);
        inieView();
        for (int i = 0; i < permissions.length; i++) {
            if(ContextCompat.checkSelfPermission(ExternalActivityDemo.this,permissions[i])!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(ExternalActivityDemo.this,new String[]{permissions[i]},REQUESTCODE);
            };
        }
    }

    private void inieView() {
        editText = findViewById(R.id.input_text);
        disTv = findViewById(R.id.dis_text);
    }

    /**
     * 点击按键时获取权限
     * 准备文件sd卡路径   内存卡存在则为 mounted
     * 先判断sd卡是不是存在
     * String savePatch = null;
     * if ("mounted".equals(Environment.getExternalStorageState())) {}
     * 获取根目录的绝对路径   .getAbsolutePath()
     * @param v
     */
    public void operate(View v) {

        String patch = Environment.getExternalStorageDirectory().getAbsolutePath()+"/getAbsolutePath.txt";


        switch (v.getId()) {
            case R.id.ex_save_btn:
                try {
                    Log.d(MainActivity.TAG,"文件路径："+patch);
                    File file = new File(patch);
                if(!file.exists()){
                        file.createNewFile();
                }
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(editText.getText().toString().getBytes());
                outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ex_read_btn:
               File file = new File(patch);
                Log.d(MainActivity.TAG,"文件路径：read"+patch);
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    byte[] bytes = new byte[1024];
                    int len = inputStream.read(bytes);
                    String strText = new String(bytes,0,len);
                    Log.d(MainActivity.TAG,"strText="+strText);
                    disTv.setText(strText);
                    inputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUESTCODE){

        }
    }
}
