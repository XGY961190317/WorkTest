package com.example.sqlitedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.entity.UserEntity;

import java.util.List;

public class DisLvAdapter extends BaseAdapter {
    Context mContext;
    List<UserEntity> mListData;
    LayoutInflater inflater;

    public DisLvAdapter(Context context, List<UserEntity> listData) {
        mContext = context;
        mListData = listData;
        inflater = LayoutInflater.from(context);
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
        ViewHolder holder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.lv_dis_item,parent,false);
            holder = new ViewHolder();
            holder.idTv = convertView.findViewById(R.id.dis_id_tv);
            holder.ageTv = convertView.findViewById(R.id.dis_age_tv);
            holder.nameTv = convertView.findViewById(R.id.dis_name_tv);
            holder.genderTv = convertView.findViewById(R.id.dis_sex_tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //设置控件
        UserEntity user = mListData.get(position);
        holder.idTv.setText(user.get_id()+"");
        holder.nameTv.setText(user.getName());
        holder.ageTv.setText(user.getAge()+"");
        holder.genderTv.setText(user.getSex());
        return convertView;
    }
    public static class ViewHolder{
        public TextView idTv;
        public TextView nameTv;
        public TextView ageTv;
        public TextView genderTv;

    }
}
