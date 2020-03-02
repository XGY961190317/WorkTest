package com.ontob.transitionanimationdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JieLuAnimationActivity extends AppCompatActivity {
    private boolean isCheckBox;
    private CheckBox mCheckBox;
    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jieluanimation);
        initView();
    }

    private void initView() {
        mCheckBox = findViewById(R.id.checkbox);
        mTextView = findViewById(R.id.textView);
        mButton = findViewById(R.id.button);

    }

    public void jieluOnClick(View view) {
        isCheckBox = mCheckBox.isChecked();
        switch (view.getId()) {
            case R.id.button:
                handleChangeVisibility(isCheckBox);
                break;
        }
    }

    private void handleChangeVisibility(boolean isCheckBox) {
        if (isCheckBox) {
            if (mTextView.isShown()) {
                revealExit();//不可见
            } else {
                revealEnter();//可见，以揭露的方式呈现
            }
        } else {
            if (mTextView.isShown()) {
                mTextView.setVisibility(View.INVISIBLE);
            } else {
                mTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    private void revealExit() {
        int mWidth = mTextView.getWidth();
        int mHeight = mTextView.getHeight();
        int cx = mWidth;
        int cy = mHeight;
        int r = (int) Math.hypot(mWidth, mHeight);
        Animator animator = ViewAnimationUtils.createCircularReveal(mTextView,cx,cy,r,0);
        animator.setDuration(1000);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //当动画播放完成时，视图不可见
                mTextView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void revealEnter() {
        int mWidth = mTextView.getWidth();
        int mHeight = mTextView.getHeight();
        int cx = mWidth /2;
        int cy = mHeight /2;
        int r = mWidth /2;

        int r1 = (int) Math.hypot(mWidth, mHeight);
        //Animator animator = ViewAnimationUtils.createCircularReveal(mTextView,cx,cy,0,r);//从中心，向外动画
        Animator animator = ViewAnimationUtils.createCircularReveal(mTextView, mWidth, mHeight,0,r1);//从右下角，向左上角动画
        animator.setDuration(1000);
        mTextView.setVisibility(View.VISIBLE);
        animator.start();
    }
}
