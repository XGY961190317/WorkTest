package com.ontob.transitionanimationdemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TransitionAnimationToActivity extends AppCompatActivity {
    private int resId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_animation);
    }

    public void toOnClick(View v) {
        switch (v.getId()) {
            case R.id.iv1:
                 resId = R.drawable.pic1;
                break;
            case R.id.iv2:
                resId = R.drawable.pic2;
                break;
            case R.id.iv3:
                resId = R.drawable.pic3;
                break;
            case R.id.iv4:
                resId = R.drawable.pic4;
                break;
        }

        Intent intent = new Intent(this, TransitionAnimation2Activity.class);
        //添加转场动画：

        Transition transition = new Explode();//获取动画对象
        transition.excludeTarget(android.R.id.statusBarBackground,true);//排除某个view不进行动画,排除状态栏
        getWindow().setEnterTransition(transition);//设置入场动画
        getWindow().setExitTransition(transition);//设置出场动画
        getWindow().setReenterTransition(transition);//再次进入时的动画
        getWindow().setSharedElementEnterTransition(transition);//设置共享元素的进场效果
        getWindow().setSharedElementExitTransition(transition);//设置共享元素的离场效果
        Pair<View, String> shareElement = Pair.create(v,"img");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,shareElement);//添加options
        intent.putExtra("resId", resId);
        startActivity(intent,options.toBundle());
    }
}
