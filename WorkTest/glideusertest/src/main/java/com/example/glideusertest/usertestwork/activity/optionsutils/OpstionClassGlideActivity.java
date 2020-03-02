package com.example.glideusertest.usertestwork.activity.optionsutils;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.glideusertest.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OpstionClassGlideActivity extends AppCompatActivity {

    private ImageView opstionIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postion);
        opstionIv = findViewById(R.id.opstion_iv_loadimg);
    }
    public void LoadImgByOpstion(View v){
        String imgUrl = "http://res.lgdsunday.club/big_img.jpg";
        loadImgByBaseOptions(imgUrl);
    }
    private void loadImgByBaseOptions(String imgUrl) {
        Glide.with(this)
                .load(imgUrl)
                .apply(RequestOptionsUtils.circleCrop())//通过自定义全局RequestOptions类的get方法设置配置
                .into(opstionIv);
    }
}
