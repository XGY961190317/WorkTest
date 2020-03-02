package com.example.lvyouapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvyouapp.R;
import com.example.lvyouapp.util.Msg;

import java.util.List;

public class FindLvAdapter extends BaseAdapter {
    Context mContext;
    List<Msg> mMsgList;
    LayoutInflater inflater;

    public FindLvAdapter(Context context, List<Msg> msgList) {
        mContext = context;
        mMsgList = msgList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mMsgList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMsgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.find_lv_item_layout, parent, false);
            holder = new MyViewHolder();
            holder.itemProfile = convertView.findViewById(R.id.item_profile);
            holder.itemRepost = convertView.findViewById(R.id.item_repost);
            holder.itemComment = convertView.findViewById(R.id.item_comment);
            holder.itemLike = convertView.findViewById(R.id.item_like);
            holder.itemNickname = convertView.findViewById(R.id.item_nickname);
            holder.itemContent = convertView.findViewById(R.id.item_content);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        //设置数据
        Msg msg = mMsgList.get(position);
        holder.itemProfile.setImageResource(msg.getProfile());
        holder.itemNickname.setText(msg.getNickname());
        //通过点击事件，响应
        holder.itemLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyOnLikeListener.onLike(true,position);
            }
        });
        //设置点赞 通过msg.isLike()设置
        if(msg.isLike()){
            holder.itemLike.setImageResource(R.mipmap.liked);
        }else {
            holder.itemLike.setImageResource(R.mipmap.like_unselecte);
        }
        return convertView;
    }

    class MyViewHolder {
        public ImageView itemProfile;
        public ImageView itemRepost;
        public ImageView itemComment;
        public ImageView itemLike;
        public TextView itemNickname;
        public TextView itemContent;
    }
    //定义接口
    private MyOnLikeListener mMyOnLikeListener;
    //设置回调接口，响应事件
   public interface MyOnLikeListener{//不同包要用  public修饰
        void onLike(boolean isLikeCheck,int itemPosition);
    }
    //设置相应事件，处理点赞
    public void setOnLikeListener(MyOnLikeListener myOnLikeListener){
        mMyOnLikeListener = myOnLikeListener;
    }


}
