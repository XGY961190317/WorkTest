package com.ontob.animationdemo.property.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;

import com.ontob.animationdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ObjectAnimatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectanimator);
    }

    public void objectAnimatorOnClick(View v) {
        switch (v.getId()) {

            case R.id.property_alpha://透明
                //从资源文件获取动画
                Animator alphaAnomator = AnimatorInflater.loadAnimator(this, R.animator.alpha);//从资源文件获取动画
                alphaAnomator.setTarget(v);//把动画设置给被点击的控件
                alphaAnomator.start();
                break;
            case R.id.property_scale://缩放
                ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 3.0f).start();//缩放动画
                break;
            case R.id.property_rotate://旋转
                ObjectAnimator.ofFloat(v, "rotation", 0, 720).start();
                break;
            case R.id.property_translate://位移
                ObjectAnimator.ofFloat(v,"x",0,500).start();
                break;
            case R.id.property_set://动画集合
               Animator alpha =   ObjectAnimator.ofFloat(v,"alpha",1.0f,0.1f,1.0f);//不透明---透明---不透明
               alpha.setDuration(1000);
               Animator scale = ObjectAnimator.ofFloat(v,"scaleX",0,3);//缩放，不延时
               scale.setDuration(1000);
               Animator rotate = ObjectAnimator.ofFloat(v,"rotation",0,360*4);
               rotate.setDuration(1000);
               rotate.setStartDelay(1000);//延时1秒
                Animator translate = ObjectAnimator.ofFloat(v,"x",0,1000);
                translate.setStartDelay(1000);
                translate.setDuration(1000);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(alpha,scale,rotate,translate);//添加到AnimatorSet中
                animatorSet.start();
                break;
        }
    }
}
