package com.example.okiodemo.okhttp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.okiodemo.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpTestActivity extends AppCompatActivity {
    public static final String TAG = "OkHttpTestActivity-X";
    private static final String GETURL = "https://raw.githubusercontent.com/square/okhttp/master/README.md";
    private static final String RESPONSE = "https://raw.githubusercontent.com/square/okhttp/master/README.md";
    private static final String POST_URL = "https://api.github.com/markdown/raw";
    private static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    private TextView content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttptest);
        content = findViewById(R.id.content);

    }

    //创建menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.get_menu:
                get();
                break;
            case R.id.response_menu:
                respons();
                break;
            case R.id.post_menu:
                post();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void respons() {
        Request.Builder builder = new Request.Builder();
        builder.url(RESPONSE);
        Request requset = builder.build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(requset);
        //通过异步请求，连接网络
        call.enqueue(new Callback() {//异步请求
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "call=" + call + "e=" + e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int requestCode = response.code();//响应码，200为反问成功
                Headers header = response.headers();//获取头
                String body = response.body().string();//文件体
                final StringBuilder buf = new StringBuilder();//缓存数据的对象
                String contentType = header.get("Content-Type");//获取header里面的键对应的值
                buf.append("contentType:" + contentType);
                buf.append("code:" + requestCode);
                buf.append("\nheader:" + header);
                buf.append("\nbody:" + body);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        content.setText(buf.toString());
                    }
                });
            }
        });
    }

    public void get() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //响应时事件，通过OKHttp开始发起网络请求
                Request.Builder builder = new Request.Builder();
                builder.url(GETURL);
                Request request = builder.build();
                Call call = client.newCall(request);
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()) {//请求结果码，200为成功
                        final String resultStr = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                content.setText(resultStr);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void post() {
        Request.Builder builder = new Request.Builder();
        builder.url(POST_URL);
        //参数1：文件类型，pos服务器说明类型，使用MediaType.parse()转为mediatype
        //参数2，需要提交的数据
        builder.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, "Hello world github/linguist#1 **cool**, and #1!\""));
        Request request = builder.build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        //异步请求数据
        call.enqueue(new Callback() {//通过实现回调获取访问到的转态，从而做处理
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if(response.isSuccessful()){
                       final String postData =  response.body().string();
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               content.setText(postData);
                           }
                       });
                    }
            }
        });
    }
    //Get 请求：
    //准备好Url:通过Requset对象builder Url,然后将这个对象传给OkHttpClient 的newCall方法
    //    Request.Builder builder = new Request.Builder();//准备builder对象
    //        builder.url(requsetUrl);//将网络请求URL传递给builder
    //    Request request = builder.build();//通过builder.build方法获取Requset对象
    //    Call call = client.newCall(request);//将刚刚获取到的requset对象通过client.newCall()方法，获取call对象
    //        try {
    //        Response response = call.execute();//开始请求,获取到返回的Response包装好的对象，这个对象里面有body，等获取到的内容
    //        response.body();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
}
