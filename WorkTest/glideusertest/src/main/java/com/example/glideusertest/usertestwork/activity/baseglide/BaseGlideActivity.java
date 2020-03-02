package com.example.glideusertest.usertestwork.activity.baseglide;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.glideusertest.R;
public class BaseGlideActivity extends AppCompatActivity {

    private ImageView baseIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        baseIv = findViewById(R.id.base_iv_loadimg);
    }

    public void LoadImgByBase(View v) {
        String imgUrl = "http://res.lgdsunday.club/big_img.jpg";
        loadImgByBaseGlide(imgUrl);
    }

    private void loadImgByBaseGlide(String imgUrl) {
        //添加加载前的配置：

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.loading)//设置占位图
                .error(R.mipmap.loader_error)//设置加载失败显示的图片
                .circleCrop();//设置图片为圆角显示
        Glide.with(this)//设置上下文
                .load(imgUrl)//设置img地址URL
                .apply(options)//设置定义好的配置
                .into(baseIv);//设置在哪个控件上显示
    }
}
