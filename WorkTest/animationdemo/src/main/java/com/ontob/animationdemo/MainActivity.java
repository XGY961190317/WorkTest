package com.ontob.animationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import com.ontob.animationdemo.frame.FrameAnimationActivity;
import com.ontob.animationdemo.property.PropertyTestActivity;
import com.ontob.animationdemo.view.ViewAnimationActivity;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable mAnimationDrawable;
    private AnimationDrawable mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnOnclick(View view) {
        switch (view.getId()) {
            case R.id.frame_Animation:
                startActivity(new Intent(this,FrameAnimationActivity.class));
                break;

            case R.id.view_Animation:
                startActivity(new Intent(this,ViewAnimationActivity.class));
                break;
            case R.id.property_Animation:
                startActivity(new Intent(this,PropertyTestActivity.class));
                break;
        }
    }
}
