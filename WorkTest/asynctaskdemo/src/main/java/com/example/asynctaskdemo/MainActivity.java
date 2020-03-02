package com.example.asynctaskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asynctaskdemo.download.AsyncTaskDwonLoadActivity;
import com.example.asynctaskdemo.downloadhelper.TestActivity;
import com.example.asynctaskdemo.userdownload.UserdownLoadActivity;
import com.example.asynctaskdemo.utils.L;

/**
 * AsyncTask方法的介绍
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity-X";
    private Button downLoadBtn;
    private Button downloadhelpBtn;
    private Button userdownloadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AsyncTask使用：
        new MyAsyncTask().execute("imooc");
        initView();
        setListener();
    }

    private void initView() {
        downLoadBtn = findViewById(R.id.download_btn);
        downloadhelpBtn = findViewById(R.id.downloadhelp_btn);
        userdownloadBtn = findViewById(R.id.downloaduser_btn);
    }

    private void setListener() {
        downLoadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AsyncTaskDwonLoadActivity.class));
            }
        });
        downloadhelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TestActivity.class));
            }
        });
        userdownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,UserdownLoadActivity.class));
            }
        });
    }

    /**
     * String：入参
     * Integer：进度
     * Boolean：结果
     */
    public class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {

        /**
         * 在另一个线程中处理是件
         *
         * @param strings 入参  长度可变
         * @return 结果
         */
        @Override
        protected Boolean doInBackground(String... strings) {
            for (int i = 0; i < 10000; i++) {
                L.d(MainActivity.TAG, "doInBackground: " + strings[0]);
                publishProgress(i);//抛出进度
            }

            return true;
        }

        /**
         * 在异步任务之前，在主线程中
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //可操作性UI
        }

        /**
         * 执行完了以后调用
         *
         * @param aBoolean
         */
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            //在主线程中，可以执行结果处理
        }

        /**
         * 当进度在变化的时候，在这里更新
         *
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //也是在UI线程
            //收到进度，然后处理
        }

        /**
         * 取消
         * 参数：结果
         */
        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
        }

        /**
         *
         */
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
