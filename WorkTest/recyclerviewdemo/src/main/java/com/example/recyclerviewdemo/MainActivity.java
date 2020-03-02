package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerviewdemo.adapter.MyAdapter;
import com.example.recyclerviewdemo.bean.ContentEntity;
import com.example.recyclerviewdemo.itemdecoration.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity-x";
    private List<ContentEntity> mDatas = new ArrayList<>();
    private int[] imgs = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycclerview);
        //创建线性布局管理器
        mLinearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayout);//设置LayoutManager
        recyclerView.addItemDecoration(new SpacesItemDecoration(8));//通过自定义辅助类，设置item的间距
        adapter = new MyAdapter(this, mDatas, recyclerView);
        recyclerView.setAdapter(adapter); //设置adapter
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int postion) {
                Toast.makeText(MainActivity.this, "点击的是第" + (postion + 1) + "条数据", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 点击添加数据
     *
     * @param view
     */
    public void onAdData(View view) {
        for (int i = 0; i < 20; i++) {
            ContentEntity entity = new ContentEntity();
            entity.setImg(imgs[i % 5]);
            entity.setText("第" + (i + 1) + "条数据");
            mDatas.add(entity);
        }
        adapter.notifyDataSetChanged();//通知适配器更新
    }

    /**
     * 点击反向排列
     *
     * @param view
     */
    public void onReverseData(View view) {
        mLinearLayout.setReverseLayout(true);//设置反向
        recyclerView.setLayoutManager(mLinearLayout);//设置LayoutManager
    }

    /**
     * 点击横向排列
     *
     * @param view
     */
    public void onHorizontalData(View view) {
        mLinearLayout.setOrientation(RecyclerView.HORIZONTAL);//设置横向排列
        recyclerView.setLayoutManager(mLinearLayout);//设置LayoutManager
    }

    /**
     * 点击网格布局显示
     *
     * @param view
     */
    public void onGridlData(View view) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        if (recyclerView.getLayoutManager().getClass() != gridLayoutManager.getClass()) {
            recyclerView.setLayoutManager(gridLayoutManager);
        }
    }

    /**
     * 点击设置为瀑布流视图
     *
     * @param view
     */
    public void onStaggeredData(View view) {
        //设置好存执显示后，还要在适配器中随机改变itemView的高度
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//两行，垂直显示
        if (recyclerView.getLayoutManager().getClass() != staggeredGridLayoutManager.getClass()) {
            Log.d(TAG, "onStaggeredData staggeredGridLayoutManager");
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
        }
    }

    /**
     * 插入一条数据
     *
     * @param view
     */
    public void onAddAData(View view) {
        adapter.addData(1);
    }

    /**
     * 删除一条数据
     *
     * @param view
     */
    public void onRemoveAData(View view) {
        adapter.removeData(1);
    }
}
