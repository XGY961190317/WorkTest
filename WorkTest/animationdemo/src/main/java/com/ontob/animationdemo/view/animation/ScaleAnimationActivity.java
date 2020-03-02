package com.ontob.animationdemo.view.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ontob.animationdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScaleAnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);
    }
    public void ScaleOnClick(View v){
        Animation scale = AnimationUtils.loadAnimation(this,R.anim.scale);
        v.startAnimation(scale);
    }
}
