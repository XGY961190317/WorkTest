package com.example.asynctaskdemo.userdownload;

import android.os.AsyncTask;
import android.util.Log;

import com.example.asynctaskdemo.MainActivity;
import com.example.asynctaskdemo.utils.L;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DwonLoadHelper {

    private static OnUserDownLoadListener onUserDownLoadListener;
    private static UserAsyncDownLoad downLoad;
    private static List<UserAsyncDownLoad>list = new ArrayList();
    public static void download(String downloadUrl, String filePatch, OnUserDownLoadListener listener) {
        if(downLoad==null){
            downLoad = new UserAsyncDownLoad(downloadUrl, filePatch, listener);
            downLoad.execute();
            list.add(downLoad);
        }else {
            downLoad  =list.get(0);
        }

        onUserDownLoadListener = listener;
        L.d(MainActivity.TAG, "doInBackgroundoooooooooooooooooo="+downLoad);

        onUserDownLoadListener.stopDwonLoad(downLoad);
    }

    public interface OnUserDownLoadListener {
        void onTest();

        void onStart();

        void onSuccess(File file);//返回成功的文件

        void onFail(File file, String msg);

        void onSetProgress(int progress);

        void stopDwonLoad(UserAsyncDownLoad downLoad);

        abstract class UserSimpleDownLoadListener implements OnUserDownLoadListener {
            @Override
            public void onTest() {

            }
        }
    }

    public static class UserAsyncDownLoad extends AsyncTask<String, Integer, Boolean> {
        public static final String REQUEST_METHOD = "GET";
        private String mDownloadUrl;
        private String mFilePatch;
        private OnUserDownLoadListener mListener;
        private File filePatch;

        public UserAsyncDownLoad(String mDownloadUrl, String mFilePatch, OnUserDownLoadListener mListener) {
            this.mDownloadUrl = mDownloadUrl;
            this.mFilePatch = mFilePatch;
            this.mListener = mListener;

            L.d(MainActivity.TAG, "doInBackgroundUuuuuuuuuuuuuuuuuuuuuuuuuu");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mListener != null) {
                mListener.onStart();
            }
        }



        @Override
        protected Boolean doInBackground(String... strings) {
            // 获取文件长度
            return httpDwonload();
        }

        private Boolean httpDwonload() {
            try {
                L.d(MainActivity.TAG, "doInBackground111111");
                // 从SharedPreferences取出DownloadInfo里的下载进度值
                URL url = new URL(mDownloadUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(REQUEST_METHOD);
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //准备文件存放的地址
                    filePatch = new File(mFilePatch);
                    if (!filePatch.exists()) {
                        filePatch.createNewFile();//没有就建一个mkdir()创建文件夹，mkdirs()创建文件
                    } else {
                        boolean isFile = filePatch.delete();//如果有，就删除
                        if (isFile) {
                            filePatch.createNewFile();
                        } else {
                            return false;
                        }
                    }

                    InputStream inputStream = connection.getInputStream();
                    OutputStream outputStream = new FileOutputStream(filePatch);//准备写入本地的流
                    //准备读取工具
                    byte bytes[] = new byte[1024];//每次读取这个数组这么多的数据
                    int fileLength = connection.getContentLength();//获取网络文件总大小
                    int downloadLength = 0;//已下载大小
                    int readLength;//用于存放每次读取的长度
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int len;
                    while ((len = inputStream.read(bytes)) != -1) {
                        baos.write(bytes, 0, len);
                    }
                    baos.close();
                    inputStream.close();
                    byte[] byteArray = baos.toByteArray();
                    Log.d(MainActivity.TAG,new String(byteArray));
                    //开始读取
                 //   while ((readLength = inputStream.read(bytes)) != -1) {
//                        if (isCancelled()) {  //通过isCancelled()判断cancel(true)是否成功。
//                            //Log.d(TAG,"Cancel");
//                            inputStream.close();
//                            break;
//                        }
//                        //每读取一次，写入一次到本地
//                        //参数：1，读取的数组，2：从0开始写 3：readLength的长度
//                        outputStream.write(bytes, 0, readLength);
//                        downloadLength += readLength;
//                        L.d(MainActivity.TAG, "读取长度=" + readLength + "  下载大小=" + downloadLength + "   下载进度=" + downloadLength * 100 / fileLength + "%");
//                        publishProgress(downloadLength * 100 / fileLength);
                  //  }
                    //完成操作，关闭流
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

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (mListener != null) {
                if (aBoolean) {
                    mListener.onSuccess(new File(String.valueOf(filePatch)));// TODO: 2020/2/11下载成功的文件
                } else {
                    mListener.onFail(new File(String.valueOf(filePatch)), "下载失败");// TODO: 2020/2/11下载失败的文件
                }
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (values != null && values.length > 0) {
                if (mListener != null) {
                    mListener.onSetProgress(values[0]);
                }
            }

        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
            L.d(MainActivity.TAG, "结果=" + aBoolean);
        }
    }
}
