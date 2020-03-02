package com.example.onetob.diancandemo.viewpagerdemo.usermolder1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.onetob.diancandemo.viewpagerdemo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewPagerAdapter extends RecyclerView.Adapter {
    Context mContext;
    List<Integer> mListImg;
    public MyRecyclerViewPagerAdapter(Context context, List<Integer> listImg) {
        mContext = context;
        mListImg = listImg;
    }

    //获取ViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item_layout,parent,false);//获取子布局
        return new UserMyViewHolder(view);//返回viewHolder

    }
//绑定ViewHolder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserMyViewHolder myViewHolder = (UserMyViewHolder) holder;//强转，获取MyViewHolder对象
        myViewHolder.ll_container.setBackgroundResource(mListImg.get(position));//设置背景
    }
//设置viewpager长度
    @Override
    public int getItemCount() {
        return mListImg.size();
    }

    static class UserMyViewHolder extends RecyclerView.ViewHolder{
LinearLayout ll_container;
        public UserMyViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_container = itemView.findViewById(R.id.user_container);//通过itemView获取子项里面的控件
        }
    }
}
