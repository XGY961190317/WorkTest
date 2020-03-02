package com.example.myapplicationdemo.awork.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.myapplicationdemo.awork.utils.HttpUtils;

/**
 * 直接从网络获取图片工具，获取为Bitmap的数据
 */
public class ImageToBitmapTask {
    public static void loadImgBitmap(String imgUrl, ImageBitmapTask.MyCallback callback) {
        ImageBitmapTask bitmapTask = new ImageBitmapTask(imgUrl, callback);
        bitmapTask.execute();
    }

    public static class ImageBitmapTask extends AsyncTask<String, Void, Bitmap> {
        private String mImgUrl;
        private ImageBitmapTask.MyCallback mCallback;
        private Exception ex;

        public ImageBitmapTask(String mImgUrl, MyCallback mCallback) {
            this.mImgUrl = mImgUrl;
            this.mCallback = mCallback;
        }

        /**
         * 下载数据处理方法
         * @param strings  入参
         * @return
         */
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                bitmap = HttpUtils.doGetImageToBitemp(mImgUrl);//通过调用 HttpUtils.doGetImageToBitemp()方法获取数据
            } catch (Exception ex) {
                ex.printStackTrace();
                this.ex = ex;
            }
            return bitmap;
        }

        /**
         * 返回结果处理方法
         * @param bitmap
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (ex != null) {
                mCallback.onFail(ex);
            }
            mCallback.onSuccess(bitmap);
        }

        /**
         * 接口回调，回调出获取状态，及对应的数据
         */
        public interface MyCallback {
            void onSuccess(Bitmap bitmap);

            void onFail(Exception ex);
        }
    }
}
