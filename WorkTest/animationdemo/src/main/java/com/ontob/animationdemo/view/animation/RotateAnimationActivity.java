package com.ontob.animationdemo.view.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ontob.animationdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RotateAnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);
    }

    public void rotateOnClick(View v) {
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        v.startAnimation(rotate);
    }
}
