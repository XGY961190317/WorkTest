package com.example.cardviewdemo.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cardviewdemo.R;

import java.util.List;

class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<Msg> mListData;
    private LayoutInflater inflater;

    public MyAdapter(Context mContext, List<Msg> mListData) {
        this.mContext = mContext;
        this.mListData = mListData;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.lv_item_layout,parent,false);
            holderView=new HolderView();
            holderView.titleTv = convertView.findViewById(R.id.lv_item_title);
            holderView.contentTv = convertView.findViewById(R.id.lv_item_content);
            holderView.imgIV = convertView.findViewById(R.id.lv_img);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView) convertView.getTag();
        }
        //设置数据
        Msg msg = mListData.get(position);
        holderView.imgIV.setImageResource(msg.getImgResId());
        holderView.titleTv.setText(msg.getTitle());
        holderView.contentTv.setText(msg.getContent());
        return convertView;
    }
    public static class HolderView{
        public ImageView imgIV;
        public TextView titleTv;
        public TextView contentTv;
    }
}
