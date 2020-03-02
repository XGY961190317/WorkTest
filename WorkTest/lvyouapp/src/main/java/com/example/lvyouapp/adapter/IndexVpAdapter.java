package com.example.lvyouapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lvyouapp.R;
import com.example.lvyouapp.util.DataUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IndexVpAdapter extends RecyclerView.Adapter {
    Context mContext;
    int[] mImg;
    public IndexVpAdapter(Context context,int[] img) {
        mContext = context;
        mImg = img;
    }
    //加载视图
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.index_vp_item_layout,parent,false);
        return new MyViewHolder(view);
    }
//绑定控件
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
     MyViewHolder viewHolder = (MyViewHolder) holder;
        //随着滑动，viewpager显示的页的下标要做取长度的模处理
        viewHolder.llVp.setBackgroundResource(mImg[position%DataUtil.idexVp_img.length]);//设置控件
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;//设置vp的页长，设置为最大的int数，让其有足够的控件滑动
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
       public LinearLayout llVp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            llVp = itemView.findViewById(R.id.ll_vp_item);
        }
    }
}
