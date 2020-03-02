package com.ontob.animationdemo.view.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.ontob.animationdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TranslateAnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
    }
    public void translateOnClick(View v){
        switch (v.getId()){
            case R.id.viewTranslateAnimation1:
            case R.id.viewTranslateAnimation2:
                TextView textView1 = findViewById(R.id.viewTranslateAnimation1);
                TextView textView2 = findViewById(R.id.viewTranslateAnimation2);
                Animation translate = AnimationUtils.loadAnimation(this,R.anim.translate);
                Animation translate1 = AnimationUtils.loadAnimation(this,R.anim.translate);
                translate.setInterpolator(new LinearInterpolator());//线性插值器
                translate1.setInterpolator(new AccelerateInterpolator());//加速度插值器
                textView1.startAnimation(translate);
                textView2.startAnimation(translate1);

                break;
        }

    }
}
