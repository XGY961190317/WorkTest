package com.example.handlerdemo.download;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.handlerdemo.MainActivity;
import com.example.handlerdemo.R;
import com.example.handlerdemo.permissions.DialogUtil;
import com.example.handlerdemo.permissions.PermissionHelper;
import com.example.handlerdemo.permissions.PermissionInterface;
import com.example.handlerdemo.utils.L;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener, PermissionInterface {

    public static final int FAILL_CODE = 10004;
    private int requestCode = 10000;
    public static final String APP_URL = "https://download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssistant_1.apk";
    private PermissionHelper mPermissionHelper;
    public String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private Handler handler;
    public static final int DOWNLOAD_CODE = 10000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Button startDown = findViewById(R.id.start_dowload);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        startDown.setOnClickListener(this);
        mPermissionHelper = new PermissionHelper(this, this);
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case DOWNLOAD_CODE:
                        progressBar.setProgress(msg.arg1);

                        break;
                    case FAILL_CODE:
                        L.d(MainActivity.TAG, (String) msg.obj);
                        break;
                }
           }
        };
    }

    @Override
    public int getPermissionsRequestCode() {
        return requestCode;
    }

    @Override
    public void requestPermissionsSuccess() {
        //权限请求用户已经全部允许
        if (requestCode == 10000) {

        } else if (requestCode == 0) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + 10086);
            intent.setData(data);
            startActivity(intent);
        } else if (requestCode == 1) {
           //开始下载
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String urlApp = "";
                    downLoadApp(APP_URL);
                }
            }).start();
        }
    }

    private void downLoadApp(String urlApp) {
        try {
            URL url = new URL(urlApp);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000*10);
            int responseCode = connection.getResponseCode();
L.d(MainActivity.TAG,"responseCode="+responseCode);
           if (responseCode==HttpURLConnection.HTTP_OK){
                String downloadFolderName = Environment.getExternalStorageDirectory()+ File.separator+"imooc"+File.separator;
                File file = new File(downloadFolderName);
                if(!file.exists()){
                    file.mkdir();
                }
                String fileName =downloadFolderName+"imooc.apk";
                File apkFile = new File(fileName);
                if (!apkFile.exists()){
                    apkFile.delete();//如果存在，先删除
                }
                InputStream inputStream = connection.getInputStream();
                int cnnectionLength = connection.getContentLength();//获取文件的长度
            L.d(MainActivity.TAG,"cnnectionLength="+cnnectionLength+"      "+connection.getResponseCode());
                int downloadSize =0;
                byte by[] = new byte[1024];
                int length = 0;
                OutputStream outputStream = new FileOutputStream(fileName);
                while((length=inputStream.read(by))!=-1){
                    outputStream.write(by,0,length);
                    downloadSize+=length;

                    //常备主线程
                    Message message = Message.obtain();
                    message.arg1 = downloadSize*100/cnnectionLength;
                    message.what= DOWNLOAD_CODE;
                    handler.sendMessage(message);
                }
                inputStream.close();
                outputStream.close();
           }//

        } catch (MalformedURLException e) {
            setMessage(FAILL_CODE,"下载失败");
            e.printStackTrace();
        } catch (IOException e) {
            setMessage(FAILL_CODE,"下载失败");
            e.printStackTrace();
        }
    }
public void setMessage(int what,Object object){
    Message message = Message.obtain();
    message.what= what;
    message.obj=object;
    handler.sendMessage(message);
}
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void requestPermissionsFail() {
        //权限请求不被用户允许。可以提示并退出或者提示权限的用途并重新发起权限申请。
        if (requestCode==10000){

        }else if (requestCode == 0) {
            //如果拒绝授予权限,且勾选了再也不提醒
            if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                DialogUtil.showSelectDialog(this, "说明", "需要使用电话权限，进行电话测试", "取消", "确定", new DialogUtil.DialogClickListener() {
                    @Override
                    public void confirm() {
                        //用于在用户勾选“不再提示”并且拒绝时，再次提示用户
                        DialogUtil.showSelectDialog(DownloadActivity.this, "电话权限不可用", "请在-应用设置-权限中，允许APP使用电话权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
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
                DialogUtil.showSelectDialog(DownloadActivity.this, "电话权限不可用", "请在-应用设置-权限中，允许APP使用电话权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
                    @Override
                    public void confirm() {
                        goToAppSetting();
                    }

                    @Override
                    public void cancel() {

                    }
                }).show();
            }

        }else if (requestCode==1){
            //如果拒绝授予权限,且勾选了再也不提醒
            if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                DialogUtil.showSelectDialog(this, "说明", "需要使用写入SD卡权限，进行下载测试", "取消", "确定", new DialogUtil.DialogClickListener() {
                    @Override
                    public void confirm() {
                        //用于在用户勾选“不再提示”并且拒绝时，再次提示用户
                        DialogUtil.showSelectDialog(DownloadActivity.this, "写入SD卡权限不可用", "请在-应用设置-权限中，允许APP使用写入SD卡权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
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
                DialogUtil.showSelectDialog(DownloadActivity.this, "写入SD卡权限不可用", "请在-应用设置-权限中，允许APP使用写入SD卡权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
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
                DialogUtil.showSelectDialog(this, "说明", "需要使用读取SD卡权限，进行下载测试", "取消", "确定", new DialogUtil.DialogClickListener() {
                    @Override
                    public void confirm() {
                        //用于在用户勾选“不再提示”并且拒绝时，再次提示用户
                        DialogUtil.showSelectDialog(DownloadActivity.this, "读取SD卡权限不可用", "请在-应用设置-权限中，允许APP使用读取SD卡权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
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
                DialogUtil.showSelectDialog(DownloadActivity.this, "读取SD卡权限不可用", "请在-应用设置-权限中，允许APP使用读取SD卡权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_dowload:
                requestCode=1;
                mPermissionHelper.requestPermissions(permissions);
                break;

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)) {
            //权限请求结果，并已经处理了该回调
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
}
