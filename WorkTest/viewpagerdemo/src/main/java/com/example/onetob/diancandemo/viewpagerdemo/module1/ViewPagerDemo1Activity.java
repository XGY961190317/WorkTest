package com.example.onetob.diancandemo.viewpagerdemo.module1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.onetob.diancandemo.viewpagerdemo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class ViewPagerDemo1Activity extends AppCompatActivity {
    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_viewpagerdemo1);
    //准备集合数据
    final List<Integer> listData = new ArrayList<>();
    listData.add(R.mipmap.bg1);
    listData.add(R.mipmap.bg2);
    listData.add(R.mipmap.bg3);
    listData.add(R.mipmap.bg4);
    //准备适配器RecyclerView.Adapter
    RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
        //创建视图
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ViewPagerDemo1Activity.this).inflate(R.layout.item_layout, parent, false);
            return new ViewPagerDemo1Activity.MyViewHolder(view);
        }

        //绑定控件
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewPagerDemo1Activity.MyViewHolder myViewHolder = (ViewPagerDemo1Activity.MyViewHolder) holder;
            myViewHolder.container.setBackgroundResource(listData.get(position));
        }

        //返回数量
        @Override
        public int getItemCount() {
            return listData.size();
        }
    };

    //找到viewpager2，设置适配器
    ViewPager2 viewPager2 = findViewById(R.id.vp_demo1);
    viewPager2.setAdapter(adapter);
    final LinearLayout ll_dots = findViewById(R.id.ll_dost);
    viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            for (int i=0;i<ll_dots.getChildCount();i++){
                //通过循环现将所有的点设为未被选中转态
                ((ImageView)ll_dots.getChildAt(i)).setBackgroundResource(R.drawable.dots_unselected);
            }
            //然后设置选中的点为选中转态
            ((ImageView)ll_dots.getChildAt(position)).setBackgroundResource(R.drawable.dots_selected);
        }
    });
}

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout container;
        //找到控件
        public MyViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
        }
    }
}
