package com.example.asynctaskdemo.downloadhelper;


import android.os.AsyncTask;
import android.os.Environment;

import com.example.asynctaskdemo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 用于实现AsyncTask下载网络数据封装的小工具
 * <p>
 * <p>
 * 1,download()方法，实现下载操作 参数:url,localPatch listener
 * 2,listener:start success fail progress
 * 3,用AsyncTask 封装的
 */
public class DownLoadAsyncTaskHelper {
    public static final int TIMEOUT = 3000;
    public static final String REQUESTMETHOD = "GET";
    public static final int READ_LENGTH = 1024;
    public static final int INIT_PROGRESS = 0;
    private static String mFilePatch;

    public static void download(String url, String localPatch,String apkName, OnDownloadListener listener) {
        TestDownLoadAsyncTask downLoadAsyncTask = new TestDownLoadAsyncTask(url, localPatch,apkName, listener);
        downLoadAsyncTask.execute();
    }

    public interface OnDownloadListener {
        void start();

        void success(int code, File file);//参数，下载成功的code，文件

        void fail(int code, File file, String msg);//参数：下载失败的code，文件，信息

        void progress(int progress);//参数：进度
     abstract class SimpleDownloadListener implements OnDownloadListener{
         //内部定义一个抽象类，实现方法，在外部调用这个实现了接口的抽象类，就可以根据实际情况重新这个方法
         @Override
         public void start() {

         }
     }
    }

    public static class TestDownLoadAsyncTask extends AsyncTask<String, Integer, Boolean> {
        private String mUrl;
        private String mLocalPatch;
        private OnDownloadListener mListener;
        private String apkName;
//        final WeakReference<s> mW;
       public TestDownLoadAsyncTask(String mUrl, String mLocalPatch,String apkName, OnDownloadListener mListener) {
//            mW = new WeakReference<>(s);
            this.mUrl = mUrl;
            this.mLocalPatch = mLocalPatch;
            this.mListener = mListener;
        }

        /**
         * 下载前，在主线程，这里可以做一些UI的准备工作
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //在下载开始前，要设置进度条显示进度为0
            //bnt显示为正在下载
            //disTv显示为下载中
            if (mListener != null) {
                mListener.start();
            }

        }

        /**
         * 下载中，在另一个线程
         *
         * @param strings
         * @return
         */
        @Override
        protected Boolean doInBackground(String... strings) {

            //这里执行下载操作
            // String appUrl = strings[0];
            try {
                //实例化URL
                URL url = new URL(mUrl);
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
                    /** String downloadFolderName = Environment.getExternalStorageDirectory()
                     + File.separator + mLocalPatch
                     + File.separator ;
                     File file = new File(downloadFolderName);//新建文件夹路径
                     if(!file.exists()){
                     file.mkdir();
                     }
                     String  mFilePatch =downloadFolderName+APK_NAME;*/
                    //============================================================
                    File file = new File(mLocalPatch);//新建文件夹路径
                    if(!file.exists()){
                        file.mkdir();
                    }
                    mFilePatch = mLocalPatch+apkName;

                    File apkFile = new File(mFilePatch);//新建文件
                    if (apkFile.exists()) {
                        boolean r = apkFile.delete();//如果存在，先删除
                        if(!r){
                            return false;
                        }
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
                if (mListener != null) {
                    mListener.success(0, new File(mLocalPatch));
                }

            } else {
                //下载失败
                if (mListener != null) {
                    mListener.fail(-1, new File(mFilePatch), "下载失败");
                }
            }

        }

        //进度条更新
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (values != null && values.length > 0) {
                //todo设置进度
                if (mListener != null) {
                    mListener.progress(values[0]);
                }
            }
        }
    }
}
