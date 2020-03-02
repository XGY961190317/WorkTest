package com.example.glidedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.glidedemo.generatedapi.GlideApp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import utils.GlideOptions;

public class GlideGetNetImageActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glideimg);
        iv = findViewById(R.id.glide_iv_loadimg);
    }

    public void startLoadImgByGlide(View v) {
        //String url = "http://res.lgdsunday.club/big_img.jpg";
        String url = "https://www.imooc.com/static/img/index/logo.png";
        loadNetImgByGlide(url);
    }
    public void startLoadImgByGlideApp(View v) {
        String url = "http://res.lgdsunday.club/big_img.jpg";
        //String url = "https://www.imooc.com/static/img/index/logo.png";

        loadNetImgByGlideApp(url);
    }

    /**
     * Glide加载网络图片的方法
     * 1,
     */
    public void loadNetImgByGlide(String imgUrl) {
        //Glide 加载图片时的配置
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.loading)//设置加载中的提示等待图片
                .error(R.mipmap.loader_error)//设置加载失败的图片
                .circleCrop();//设置显示为圆角图片
        Glide.with(this)//指定context
                .load(imgUrl)//指定网络图片路径
                .apply(GlideOptions.circleCropOption())//设置配置信息,直接通过自定义的配置options类的具体配置方法
                .into(iv);//指定加载到的位置
    }
    public void loadNetImgByGlideApp(String imgUrl) {
      GlideApp.with(this)
              .load(imgUrl)
              .injectOptions()
              .into(iv);
    }
}
