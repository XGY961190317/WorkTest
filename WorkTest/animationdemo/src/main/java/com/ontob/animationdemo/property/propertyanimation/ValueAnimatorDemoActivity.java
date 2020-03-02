package com.ontob.animationdemo.property.propertyanimation;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.ontob.animationdemo.R;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

public class ValueAnimatorDemoActivity extends AppCompatActivity {
    public   String TAG = "ValueAnimatorDemoActivi-x";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuesanimator);
    }

    public void valuesOnclick(View v) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(100);//设置动画时间
        //监听更新
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float  animatedFraction =animation.getAnimatedFraction();
                int animatedValue = (int) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate:"+String.format("%.3f %d",animatedFraction,animatedValue));
            }
        });
        valueAnimator.start();
    }
}
