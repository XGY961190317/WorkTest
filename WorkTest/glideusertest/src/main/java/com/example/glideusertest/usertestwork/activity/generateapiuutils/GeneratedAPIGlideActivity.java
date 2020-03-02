package com.example.glideusertest.usertestwork.activity.generateapiuutils;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.glideusertest.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GeneratedAPIGlideActivity extends AppCompatActivity {

    private ImageView generatedIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated);
        generatedIv = findViewById(R.id.generated_iv_loadimg);
    }
    public void LoadImgByGenerated(View v){
        String imgUrl = "http://res.lgdsunday.club/big_img.jpg";
        loadImgByBaseGenerated(imgUrl);
    }

    private void loadImgByBaseGenerated(String imgUrl) {
    GlideApp.with(this)
            .load(imgUrl)
            .generated()//设置配置
            .into(generatedIv);
    }
}
