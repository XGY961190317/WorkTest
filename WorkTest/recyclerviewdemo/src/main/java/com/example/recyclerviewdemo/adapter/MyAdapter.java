package com.example.recyclerviewdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recyclerviewdemo.MainActivity;
import com.example.recyclerviewdemo.R;
import com.example.recyclerviewdemo.bean.ContentEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<ContentEntity> mDatas;
    private LayoutInflater inflater;
    private RecyclerView mRecyclerView;
    private int addDataPosition = -1;

    public MyAdapter(Context context, List<ContentEntity> datas, RecyclerView recyclerView) {
        this.mContext = context;
        this.mDatas = datas;
        this.mRecyclerView = recyclerView;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        ContentEntity entity = mDatas.get(position);
        holder.imageView.setImageResource(entity.getImg());
        holder.textView.setText(entity.getText());
        //通过设置textView的高度来实现不通过itemView的高度
        if (mRecyclerView.getLayoutManager().getClass() == StaggeredGridLayoutManager.class) {//如果是瀑布流manager，则做对应的操作
            Log.d(MainActivity.TAG, "RandomHeight()=" + RandomHeight());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RandomHeight());
            holder.textView.setLayoutParams(params);
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.textView.setLayoutParams(params);
        }
        //设置itemView的点击事件将position回调出去
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickListener(position);
            }
        });
        //  改变ItemView背景颜色
        if (addDataPosition == position) {
            holder.mItemView.setBackgroundColor(Color.RED);
        } else {
            holder.mItemView.setBackgroundColor(Color.parseColor("#A4D3EE"));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private View mItemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_iv);
            textView = itemView.findViewById(R.id.item_tv);
            mItemView = itemView;
        }
    }

    /**
     * 随机高度的方法
     *
     * @return 返回随机的高度，设置给itemView
     */
    private int RandomHeight() {
        int height = (int) (Math.random() * 1000);//1000以内的整数
        return height;

    }

    /**
     * 给itemVIew添加监听事件：通过接口回调的方式
     * 1,定义回调接口
     * 2，定义设置itemOnClick的监听事件
     * 3，在itemView的点击事件中响应这个接口回调方法，将对应的postion回调出去
     */
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClickListener(int postion);
    }

    /**
     * 设置onItemClick监听事件的方法
     *
     * @param onItemClick
     */
    public void setOnItemClickListener(OnItemClickListener onItemClick) {
        mOnItemClickListener = onItemClick;
    }

    /**
     * 添加一条数据
     *
     * @param position
     */
    public void addData(int position) {
        addDataPosition = position;
        ContentEntity entity = new ContentEntity();
        entity.setImg(R.mipmap.e);
        entity.setText("这是一条插入的数据");
        mDatas.add(position, entity);
        notifyItemInserted(position);//通知在索引为1的位置插入一条数据

//        刷新ItemView
        notifyItemRangeChanged(position, mDatas.size() - position);
    }

    /**
     * 删除一条数据
     *
     * @param position
     */
    public void removeData(int position) {
        addDataPosition = -1;
        mDatas.remove(position);
        notifyItemRemoved(position);//通知在索引为1的位置删除一条数据

//        刷新ItemView
        notifyItemRangeChanged(position, mDatas.size() - position);
    }

}
