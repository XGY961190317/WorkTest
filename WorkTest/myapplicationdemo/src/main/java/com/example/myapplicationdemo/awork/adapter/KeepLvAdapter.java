package com.example.myapplicationdemo.awork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.myapplicationdemo.awork.bean.Article;

import java.util.List;

public class KeepLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<Article> mResultList;
    private LayoutInflater inflater;

    public KeepLvAdapter(Context context, List<Article> resultList) {
        mContext = context;
        mResultList = resultList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mResultList.size();
    }

    @Override
    public Object getItem(int position) {
        return mResultList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
