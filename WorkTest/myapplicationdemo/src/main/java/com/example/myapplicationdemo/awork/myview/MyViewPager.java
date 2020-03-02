package com.example.myapplicationdemo.awork.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 但是咸鱼的是不可以左右滑动的，怎么禁止ViewPager左右滑动呢？
 * 建一个ViewPager的子类，重写它的 onInterceptTouchEvent 和 onTouchEvent方法：
 */
public class MyViewPager extends ViewPager {
    private boolean canSwipe = true;

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanSwipe(boolean canSwipe) {
        this.canSwipe = canSwipe;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return canSwipe && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return canSwipe && super.onInterceptTouchEvent(ev);
    }
}
