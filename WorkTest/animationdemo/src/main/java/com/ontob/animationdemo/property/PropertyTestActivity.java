package com.ontob.animationdemo.property;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ontob.animationdemo.R;
import com.ontob.animationdemo.property.propertyanimation.ObjectAnimatorActivity;
import com.ontob.animationdemo.property.propertyanimation.ValueAnimatorDemoActivity;
import com.ontob.animationdemo.property.propertyanimation.ViewPropertyAnimatorActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PropertyTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
    }

    public void propertyOnclick(View v) {
        switch (v.getId()) {
            case R.id.valuesAnimator:
                startActivity(new Intent(this, ValueAnimatorDemoActivity.class));
                break;
            case R.id.object_Animator:
                startActivity(new Intent(this, ObjectAnimatorActivity.class));
                break;
            case R.id.viewproperty_Animator:
                startActivity(new Intent(this, ViewPropertyAnimatorActivity.class));
                break;
        }
    }
}
