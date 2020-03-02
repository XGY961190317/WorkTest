package com.example.asynctaskdemo.downloadhelper;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.asynctaskdemo.R;
import com.example.asynctaskdemo.download.AsyncTaskDwonLoadActivity;
import com.example.asynctaskdemo.permissions.DialogUtil;
import com.example.asynctaskdemo.permissions.PermissionHelper;
import com.example.asynctaskdemo.permissions.PermissionInterface;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity implements PermissionInterface{
    private int requestCode = 10000;
    private ProgressBar progressBar;
    private Button downloadBtn;
    private TextView disTV;
    public static final String FILE_NAME = "imooc_11";
    public static final String APK_NAME = "imooc.apk";
    private PermissionHelper mPermissionHelper;
    public static final String APP_URL = "http://download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssistant_1.apk";
    private String permissions[] = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctaskdownload_test_activity);
        //初始化权限设置实例
        mPermissionHelper = new PermissionHelper(this, (PermissionInterface) this);
        initView();
       setListener();

    }

    private void setListener() {
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCode =1;
                mPermissionHelper.requestPermissions(permissions);
            }
        });
    }

    private void initView() {
        progressBar = findViewById(R.id.help_progressBar);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        downloadBtn = findViewById(R.id.help_download_btn);
        disTV = findViewById(R.id.help_dis_result);
        disTV.setText(R.string.dis_result);
    }

    @Override
    public int getPermissionsRequestCode() {
        return requestCode;
    }

    @Override
    public void requestPermissionsSuccess() {
        //权限请求用户已经全部允许
        if (requestCode == 10000) {

        } else if (requestCode == 1) {
            //开始下载
            String downloadFolderName = Environment.getExternalStorageDirectory()
                    + File.separator + FILE_NAME
                    + File.separator;
            DownLoadAsyncTaskHelper.download(APP_URL, downloadFolderName,APK_NAME, new DownLoadAsyncTaskHelper.OnDownloadListener.SimpleDownloadListener() {
                @Override
                public void start() {
                    progressBar.setProgress(0);
                    downloadBtn.setText(R.string.download);
                    downloadBtn.setEnabled(false);//开始下载的时候，设置按钮不可点击
                    disTV.setText(R.string.download_wait);
                }

                @Override
                public void success(int code, File file) {
                    downloadBtn.setText(R.string.download_finish);
                    //不允许再次点击下载,是btn失去焦点
                    downloadBtn.setEnabled(false);
                    //设置disTV显示下载结果
                    disTV.setText(getString(R.string.app_download_finish) + file);
                }

                @Override
                public void fail(int code, File file, String msg) {
                    //下载失败
                    downloadBtn.setEnabled(true);
                    downloadBtn.setText(R.string.download_fiall);
                    disTV.setText(R.string.download_fiall_reset);
                }

                @Override
                public void progress(int progress) {
                    progressBar.setProgress(progress);
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void requestPermissionsFail() {
//权限请求不被用户允许。可以提示并退出或者提示权限的用途并重新发起权限申请。
        if (requestCode==10000){

        }else if (requestCode == 0) {
            //如果拒绝授予权限,且勾选了再也不提醒
            if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                DialogUtil.showSelectDialog(this, "说明", "需要使用sdcard写入权限，进行数据读取操作", "取消", "确定", new DialogUtil.DialogClickListener() {
                    @Override
                    public void confirm() {
                        //用于在用户勾选“不再提示”并且拒绝时，再次提示用户
                        DialogUtil.showSelectDialog(TestActivity.this, "sdcard写入权限不可用", "请在-应用设置-权限中，允许APP使用sdcard写入权限进行数据读取操作", "取消", "立即开启", new DialogUtil.DialogClickListener() {
                            @Override
                            public void confirm() {
                                goToAppSetting();
                            }

                            @Override
                            public void cancel() {

                            }
                        }).show();
                    }

                    @Override
                    public void cancel() {

                    }
                }).show();
            } else {
                DialogUtil.showSelectDialog(TestActivity.this, "sdcard写入权限不可用", "请在-应用设置-权限中，允许APP使用sdcard写入权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
                    @Override
                    public void confirm() {
                        goToAppSetting();
                    }

                    @Override
                    public void cancel() {

                    }
                }).show();
            }
            if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                DialogUtil.showSelectDialog(this, "说明", "需要使用sdcard读取权限，进行数据读取操作", "取消", "确定", new DialogUtil.DialogClickListener() {
                    @Override
                    public void confirm() {
                        //用于在用户勾选“不再提示”并且拒绝时，再次提示用户
                        DialogUtil.showSelectDialog(TestActivity.this, "sdcard读取权限不可用", "请在-应用设置-权限中，允许APP使用sdcard读取权限进行数据读取操作", "取消", "立即开启", new DialogUtil.DialogClickListener() {
                            @Override
                            public void confirm() {
                                goToAppSetting();
                            }

                            @Override
                            public void cancel() {

                            }
                        }).show();
                    }

                    @Override
                    public void cancel() {

                    }
                }).show();
            } else {
                DialogUtil.showSelectDialog(TestActivity.this, "sdcard读取权限不可用", "请在-应用设置-权限中，允许APP使用sdcard读取权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
                    @Override
                    public void confirm() {
                        goToAppSetting();
                    }

                    @Override
                    public void cancel() {

                    }
                }).show();
            }

        }
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
