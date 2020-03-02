package com.ontob.transitionanimationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TransitionAnimation2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition2);
        Intent intent = getIntent();
        int resId = intent.getExtras().getInt("resId");
        ImageView img = findViewById(R.id.iv);
        img.setImageResource(resId);
        Transition transition = new Explode();
        transition.excludeTarget(android.R.id.statusBarBackground,true);//排除某个view不进行动画，这里排除状态栏
        getWindow().setEnterTransition(transition);//设置进场动画
        getWindow().setExitTransition(transition);//设置离场动画
    }
}
