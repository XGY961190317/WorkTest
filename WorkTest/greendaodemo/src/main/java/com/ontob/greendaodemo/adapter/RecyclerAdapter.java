package com.ontob.greendaodemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ontob.greendaodemo.GoodsDetailActivity;
import com.ontob.greendaodemo.R;
import com.ontob.greendaodemo.goodsmodule.GoodsModule;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context mContext;
    LayoutInflater inflater;
    private List<GoodsModule> dataSource;

    public void setDataSource(List<GoodsModule> dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }
    public RecyclerAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.goods_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       GoodsModule model =  dataSource.get(position);
        holder.tvName.setText(model.getName());
        holder.tvInfo.setText(model.getInfo());
        holder.ivIcon.setImageResource(mContext.getResources().getIdentifier(model.getIcon(), "mipmap", mContext.getPackageName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra(GoodsDetailActivity.GOODS_MODEL, (Parcelable) model);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(dataSource==null){
            return 0;
        }
        return dataSource.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivIcon;
        public TextView tvName;
        public TextView tvInfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
            tvInfo = itemView.findViewById(R.id.tv_info);
        }
    }
}
