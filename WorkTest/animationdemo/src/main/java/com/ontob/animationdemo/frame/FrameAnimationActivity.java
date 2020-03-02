package com.ontob.animationdemo.frame;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;

import com.ontob.animationdemo.R;

import androidx.appcompat.app.AppCompatActivity;

public class FrameAnimationActivity extends AppCompatActivity {

    private AnimationDrawable mAnimationDrawable;
    private AnimationDrawable mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = findViewById(R.id.view);
        //获取到view的背景
        mAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.loading);  //从资源获取帧动画
        mAnimationDrawable = (AnimationDrawable) v.getBackground();
        mAnimationDrawable.setOneShot(true);//只显示一遍动画
    }

    public void btnOnclick(View view) {
        switch (view.getId()) {
            case R.id.start:
                mAnimationDrawable.start();
                break;

            case R.id.stop:
                mAnimationDrawable.stop();
                break;
        }
    }
}
