package com.example.myapplicationdemo.awork.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class VpAdapter extends PagerAdapter {
    private Context mContext;
    private List<View> mViewList;

    public VpAdapter(Context context, List<View> viewList) {
        mContext = context;
        mViewList = viewList;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int max = Integer.MAX_VALUE;
        container.addView(mViewList.get(position));
        return mViewList.get(position);

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // container.removeView(mDatas.get(position));
        container.removeView(mViewList.get(position));//销毁的item
    }
}
