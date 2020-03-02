package com.ontob.animationdemo.property.propertyanimation;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;

import com.ontob.animationdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewPropertyAnimatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpropertyanimator);
    }

    public void viewAnimatorOnClick(View v) {
        switch (v.getId()) {
            case R.id.property_view_alpha://透明
                v.animate().alpha(0.5f).setDuration(3000).start();
                break;
            case R.id.property_view_scale://缩放
                v.animate().scaleX(500).setDuration(1000).start();
                break;
            case R.id.property_view_rotate://旋转
                v.animate().rotation(3 * 360).setDuration(1000).start();
                break;
            case R.id.property_view_translate://位移
                ViewPropertyAnimator viewPropertyAnimator = v.animate();
                viewPropertyAnimator.translationX(500f);
                viewPropertyAnimator.start();
                break;
            case R.id.property_view_set:
                v.animate().alpha(0.5f).setDuration(3000).start();
                v.animate().scaleX(5).setDuration(1000).start();
                v.animate().rotation(720).setDuration(1000).start();
                v.animate().translationX(200).setStartDelay(1000).setDuration(200).start();
                break;
        }
    }
}
