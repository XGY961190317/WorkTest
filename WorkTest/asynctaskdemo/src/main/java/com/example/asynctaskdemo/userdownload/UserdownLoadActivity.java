package com.example.asynctaskdemo.userdownload;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.asynctaskdemo.MainActivity;
import com.example.asynctaskdemo.R;
import com.example.asynctaskdemo.permissions.PermissionHelper;
import com.example.asynctaskdemo.permissions.PermissionInterface;
import com.example.asynctaskdemo.utils.L;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserdownLoadActivity extends AppCompatActivity implements PermissionInterface {
    private static int PERMISSION_REQUEST_CODE = 10000;
    private TextView title;
    private Button startBtn;
    private Button pauseBtn;
    private ProgressBar asyProgress;
    public static final String FILE_NAME = "imooc";
    public static final String APK_NAME = System.currentTimeMillis()+"imooc.apk";
    public static final String APP_URL = "http://www.imooc.com/api/teacher?type=2&page=1";
    PermissionHelper mPermissionHelper;
    private String permissions[] = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    DwonLoadHelper.UserAsyncDownLoad mDownLoad;
    boolean isStop = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdownload_activity);
        mPermissionHelper = new PermissionHelper(this, (PermissionInterface) this);//初始化权限设置实例
        initView();
        setListener();

    }

    private void initView() {
        title = findViewById(R.id.tv_title);
        startBtn = findViewById(R.id.start_download);
        pauseBtn = findViewById(R.id.pause_download);
        asyProgress = findViewById(R.id.async_progressBar);
    }

    private void setListener() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                PERMISSION_REQUEST_CODE=1;
                //获取权限
                mPermissionHelper.requestPermissions(permissions);
                if(isStop){
                    L.d(MainActivity.TAG,"进来设置");
                    mDownLoad.cancel(false);}
                isStop=true;
            }
        });
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 取消下载
                title.setText("已暂停");
               mDownLoad.cancel(true);
               //mDownLoad=null;
               startBtn.setEnabled(true);
                isStop=true;
            }
        });
    }

    @Override
    public int getPermissionsRequestCode() {
        return PERMISSION_REQUEST_CODE;
    }

    @Override
    public void requestPermissionsSuccess() {
        //权限请求用户已经全部允许
        if (PERMISSION_REQUEST_CODE == 10000) {

        } else if (PERMISSION_REQUEST_CODE == 1) {
            L.d(MainActivity.TAG,"权限ok");
            //开始下载
            String downloadFolderName = Environment.getExternalStorageDirectory()
                    + File.separator + FILE_NAME
                    + File.separator+APK_NAME;
            L.d(MainActivity.TAG,"权限ok11111111111111111111");
            DwonLoadHelper.download(APP_URL, downloadFolderName, new DwonLoadHelper.OnUserDownLoadListener.UserSimpleDownLoadListener() {
                @Override
                public void onStart() {
                    title.setText("正在下载");
                    asyProgress.setProgress(0);
                    startBtn.setEnabled(false);
                }

                @Override
                public void onSuccess(File file) {
                    title.setText("下载完成");
                    startBtn.setEnabled(true);
                }

                @Override
                public void onFail(File file, String msg) {
                    title.setText(msg);
                    startBtn.setEnabled(true);
                }

                @Override
                public void onSetProgress(int progress) {
                    asyProgress.setProgress(progress);
                }

                @Override
                public void stopDwonLoad(DwonLoadHelper.UserAsyncDownLoad downLoad) {
                    mDownLoad =downLoad;

                }
            });


        }
    }

    @Override
    public void requestPermissionsFail() {

    }
    /**
     * 打开Setting
     */

    private void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 123);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)){
            return ;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
