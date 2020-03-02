package com.example.sqlitedemo.downloaddatadistolv.net;

import android.os.AsyncTask;
import android.util.Log;

import com.example.sqlitedemo.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetNetDataHelper {
    public static void download(String mUrl, OnIsDownload mOnIsDownload) {
        DownloadAsyncTask dat = new DownloadAsyncTask(mUrl, mOnIsDownload);
        dat.execute();
    }

    static class DownloadAsyncTask extends AsyncTask<String, Integer, Boolean> {
        private String mUrl;
        private OnIsDownload mOnIsDownload;
        private String result;

        public DownloadAsyncTask(String mUrl, OnIsDownload mOnIsDownload) {
            this.mUrl = mUrl;
            this.mOnIsDownload = mOnIsDownload;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                URL url = new URL(mUrl);//
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(30 * 1000);
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len = inputStream.read(bytes)) != -1) {
                        bos.write(bytes, 0, len);
                    }
                    bos.close();
                    inputStream.close();
                    byte[] byteArray = bos.toByteArray();
                    result = new String(byteArray);
                    Log.d(MainActivity.TAG,result);
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
            if (aBoolean) {
                mOnIsDownload.onSuccess("获取数据成功", result);
            } else {
                mOnIsDownload.onFail("获取数据失败");
            }
        }
    }

    public interface OnIsDownload {
        void onSuccess(String isSuccess, String result);//返回成功的文件

        void onFail(String msg);
    }
}
