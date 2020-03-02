package com.example.onetob.diancandemo.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context mContext;
    List<Msg> mListData;
    LayoutInflater inflater;

    public MyAdapter(Context context, List<Msg> listData) {
        mContext = context;
        mListData = listData;
        inflater = LayoutInflater.from(mContext);
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
//获取视图 （设置ListView每一项的显示效果） 每隔视图出现的时候都会执行
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //完成对view的设置
        //将布局资源转为view
        //参数1，所要操作引用的布局资源
        //RecycleBin加载，
        ViewHolder hodleView = null;
        if (convertView == null) {
            //优化1：利用进入RecycleBin中的V结尾，减少读View的赋值
            convertView = inflater.inflate(R.layout.base_item_layout, null);
            hodleView = new ViewHolder();
            hodleView.itemProfile = convertView.findViewById(R.id.item_profile);
            hodleView.itemNickname = convertView.findViewById(R.id.item_nickname);
            hodleView.itemContent = convertView.findViewById(R.id.item_content);
            hodleView.itemLike = convertView.findViewById(R.id.item_like);
            hodleView.itemComment = convertView.findViewById(R.id.item_comment);
            hodleView.itemRepost = convertView.findViewById(R.id.item_repost);
            convertView.setTag(hodleView);
        } else {
            hodleView = (ViewHolder) convertView.getTag();
        }

        //获取数据对象
        final Msg msg = mListData.get(position);
        //设置控件
        hodleView.itemProfile.setImageResource(msg.getProfile());
        hodleView.itemNickname.setText(msg.getNickname());
        hodleView.itemContent.setText(msg.getContent());

        if(msg.isLike()){
            hodleView.itemLike.setImageResource(R.mipmap.liked);
        }else {
            hodleView.itemLike.setImageResource(R.mipmap.liked);
        }
        hodleView.itemComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"你点击个评论",Toast.LENGTH_SHORT).show();
            }
        });
        hodleView.itemRepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"你点击个转发",Toast.LENGTH_SHORT).show();
            }
        });
        hodleView.itemLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过点击事件调用接口回调方法
                myOnItemLikeListener.setOnItemLike(position,true);
            }
        });

        return convertView;
    }
//自定义一个类，命名为ViewHolder
    //将需要保存的视图声明为公开的属性
    //什么时候保存，当convertView为空时完成对ViewHolder的实例化工作，并为各个控件赋值，
    //什么时候用，什么时候都在用，（性能的提升是在convertView不为空时体现）
    //怎么用，当convertView==null，时完成了ViewHolder的初始化及控件初始化工作后，调用使用convertView.setTag()方法将viewHoolder保存起来
    //当convertView不为空时，viewHolder=convertView.getTag()获取
    static class ViewHolder {
        public ImageView itemProfile;
        public TextView itemNickname;
        public TextView itemContent;
        public ImageView itemLike;
        public ImageView itemComment;
        public ImageView itemRepost;

    }
    //接口回调，及时更新adapter的数据
    private MyOnItemLikeListener myOnItemLikeListener;//声明一个接口对象，在需要的地方调用接口方法
    //定义一个接口
    interface MyOnItemLikeListener{
        void setOnItemLike(int position,boolean b);
    }
    //设置方法
    public void setOnIsLike(MyOnItemLikeListener myOnItemLikeListener1) {
        myOnItemLikeListener = myOnItemLikeListener1;
    }
}
