package com.ontob.transitionanimationdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void myOnClick(View view) {
        switch (view.getId()) {
            case R.id.jielu_Animation:
startActivity(new Intent(this,JieLuAnimationActivity.class));
                break;
            case R.id.transition_Animation:
                startActivity(new Intent(this,TransitionAnimationActivity.class));
                break;
            case R.id.transition_to_activity:
                startActivity(new Intent(this,TransitionAnimationToActivity.class));
                break;
        }
    }


}
