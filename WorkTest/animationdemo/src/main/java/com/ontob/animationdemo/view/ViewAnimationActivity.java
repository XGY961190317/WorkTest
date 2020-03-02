package com.ontob.animationdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ontob.animationdemo.R;
import com.ontob.animationdemo.view.animation.AlphaAnimationActivity;
import com.ontob.animationdemo.view.animation.RotateAnimationActivity;
import com.ontob.animationdemo.view.animation.ScaleAnimationActivity;
import com.ontob.animationdemo.view.animation.SetAnimationActivity;
import com.ontob.animationdemo.view.animation.TranslateAnimationActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewAnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }

    public void ViewOnclick(View v) {
        switch (v.getId()) {
            case R.id.alpha_Animation://透明度动画
                startActivity(new Intent(this,AlphaAnimationActivity.class));
                break;
            case R.id.scale_Animation://缩放动画
                startActivity(new Intent(this,ScaleAnimationActivity.class));
                break;
            case R.id.translate_Animation://位移动画
                startActivity(new Intent(this,TranslateAnimationActivity.class));
                break;
            case R.id.rotate_Animation://旋转动画
                startActivity(new Intent(this,RotateAnimationActivity.class));
                break;
            case R.id.set_Animation://组合动画
                startActivity(new Intent(this,SetAnimationActivity.class));
                break;


        }
    }
}
