package com.example.asynctaskdemo.download;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.asynctaskdemo.R;
import com.example.asynctaskdemo.permissions.DialogUtil;
import com.example.asynctaskdemo.permissions.PermissionHelper;
import com.example.asynctaskdemo.permissions.PermissionInterface;

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

public class AsyncTaskDwonLoadActivity extends AppCompatActivity implements PermissionInterface{
    public static final int TIMEOUT = 3000;
    public static final String REQUESTMETHOD = "GET";
    public static final String FILE_NAME = "imooc_20200211";
    public static final String APK_NAME = "imooc.apk";
    public static final int READ_LENGTH = 1024;
    public static final int INIT_PROGRESS = 0;
    private int requestCode = 10000;
    private String mFilePatch;
    public static final String APP_URL = "http://download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssistant_1.apk";
    private TextView disTV;
    private Button downloadBtn;
    private ProgressBar progressBar;
    private PermissionHelper mPermissionHelper;
    private String permissions[] = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctaskdownload_activity);
        mPermissionHelper = new PermissionHelper(this, (PermissionInterface) this);//初始化权限设置实例
        initView();
        setListener();

    }

    private void initView() {
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        downloadBtn = findViewById(R.id.test_download_btn);
        disTV = findViewById(R.id.dis__result);
        disTV.setText(R.string.dis_result);
    }

    private void setListener() {
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 点击开始下载
                //下载之前申请权限
                requestCode =1;
                mPermissionHelper.requestPermissions(permissions);
                //权限通过后下载
            }
        });
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
            TestDownLoadAsyncTask testDownLoadAsyncTask = new TestDownLoadAsyncTask();
            testDownLoadAsyncTask.execute(APP_URL);
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
                        DialogUtil.showSelectDialog(AsyncTaskDwonLoadActivity.this, "sdcard写入权限不可用", "请在-应用设置-权限中，允许APP使用sdcard写入权限进行数据读取操作", "取消", "立即开启", new DialogUtil.DialogClickListener() {
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
                DialogUtil.showSelectDialog(AsyncTaskDwonLoadActivity.this, "sdcard写入权限不可用", "请在-应用设置-权限中，允许APP使用sdcard写入权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
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
                        DialogUtil.showSelectDialog(AsyncTaskDwonLoadActivity.this, "sdcard读取权限不可用", "请在-应用设置-权限中，允许APP使用sdcard读取权限进行数据读取操作", "取消", "立即开启", new DialogUtil.DialogClickListener() {
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
                DialogUtil.showSelectDialog(AsyncTaskDwonLoadActivity.this, "sdcard读取权限不可用", "请在-应用设置-权限中，允许APP使用sdcard读取权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
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
     * 参数1：入参
     * 参数2,：进度
     * 参数3：结果
     */
    public class TestDownLoadAsyncTask extends AsyncTask<String, Integer, Boolean> {



        /**
         * 下载前，在主线程，这里可以做一些UI的准备工作
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //在下载开始前，要设置进度条显示进度为0
            //bnt显示为正在下载
            //disTv显示为下载中
            progressBar.setProgress(0);
            downloadBtn.setText(R.string.download);
            downloadBtn.setEnabled(false);//开始下载的时候，设置按钮不可点击
            disTV.setText(R.string.download_wait);
        }

        /**
         * 下载中，在另一个线程
         *
         * @param strings
         * @return
         */
        @Override
        protected Boolean doInBackground(String... strings) {
            if (strings != null && strings.length > 0) {//判断入参是不是为空，
                //这里执行下载操作
                String appUrl = strings[0];
                try {
                    //实例化URL
                    URL url = new URL(appUrl);
                    //实例化网络连接
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置请求信息
                    connection.setConnectTimeout(TIMEOUT);//响应时间
                    connection.setRequestMethod(REQUESTMETHOD);//请求方式
                    int responseCode = connection.getResponseCode();//获取响应码
                    connection.connect();//开启连接
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        //连接成功，开始获取数据
                        InputStream inputStream = connection.getInputStream();//通过网络连接获取网络读取流
                        int countLength = connection.getContentLength();//获取下载内容总大小
                        //下载前的准备：
                        //下载路径
                        /**
                         * Environment.getExternalStorageDirectory()//获取sdcard路径
                         * File.separator//等同于"/"
                         */
                        //============================================================
                        //准备下载地址
                        /**
                        mFilePatch = Environment.getExternalStorageDirectory()
                                + File.separator + FILE_NAME
                                + File.separator + APK_NAME;
                        //对下载地址进行处理

                        File apkFile = new File(mFilePatch);
                        if (apkFile.exists()) {
                            //判断这个文件是否存在，如果存在，则delete掉
                            boolean result = apkFile.delete();
                            if (!result) {
                                //判断删除的结果
                                //删除成功，则return false，直接覆盖
                                return false;
                            }
                        }else {
                            apkFile.mkdir();
                        }
                         */
         //=================================================================================
                        String downloadFolderName = Environment.getExternalStorageDirectory()
                                + File.separator + FILE_NAME
                                + File.separator ;
                        File file = new File(downloadFolderName);//新建文件夹路径
                        if(!file.exists()){
                            file.mkdir();
                        }
                        mFilePatch =downloadFolderName+APK_NAME;
                        File apkFile = new File(mFilePatch);//新建文件
                        if (apkFile.exists()){
                            apkFile.delete();//如果存在，先删除
                        }
         //=================================================================================
                        //通过一个byte[]数组，去取数据，每次读取数组的长度
                        byte[] bytes = new byte[READ_LENGTH];
                        int len;//每次读取数据的长度
                        int downLoadLength = INIT_PROGRESS;//下载好的数据长度,用于计算下载进度,初始化为0
                        //准备一个文件写入流，写入数据到文件
                        OutputStream outputStream = new FileOutputStream(apkFile);
                        //通过循环开始下载
                        /**
                         * (len=inputStream.read(bytes))!=-1  每次读取bytes，数组长度的数据，并将这个长度赋值给len，用于判断是否还有数据没有读取完
                         * 如果len=-1，则表示数据读取完了，
                         * 如果len!=-1，表示还有数据没有被读取完，继续循环读取
                         */
                        while ((len = inputStream.read(bytes)) != -1) {
                            //将每次读取到的数据，写入到文件中
                            /**
                             * 参数1：每次写入bytes数组长度的数据
                             * 参数2：0 表示从0开始写
                             * 参数3：len 表示每次写入的长度为len(即每次读取的长度)
                             */
                            outputStream.write(bytes, INIT_PROGRESS, len);
                            //读取完成时，要计时将下载的进度保存好
                            downLoadLength += len;
                            //将下载进度传送出去，便于后面更新下载进度
                            //发送进度
                            publishProgress(downLoadLength * 100 / countLength);
                        }
                        //下载完成后关闭流
                        outputStream.close();
                        inputStream.close();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }

            return true;
        }

        //下载后，UI的显示，数据更新
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            //下载完成后，UI显示更新
            //通过下载结果aBoolean判断是否下载完成
            if (aBoolean) {
                //下载完成后，
                downloadBtn.setText(R.string.download_finish);
                //不允许再次点击下载,是btn失去焦点
                downloadBtn.setEnabled(false);
                //设置disTV显示下载结果
                disTV.setText(getString(R.string.app_download_finish) + mFilePatch);
            } else {
                //下载失败
                downloadBtn.setEnabled(true);
                downloadBtn.setText(R.string.download_fiall);
                disTV.setText(R.string.download_fiall_reset);
            }

        }

        //进度条更新
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(values!=null&&values.length>0){
                progressBar.setProgress(values[0]);
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
