package com.ontob.greendaodemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.ontob.greendaodemo.Utils.DataUtils;
import com.ontob.greendaodemo.adapter.RecyclerAdapter;
import com.ontob.greendaodemo.goodsmodule.GoodsModule;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private GreenDaoManager greenDaoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        initView();
        greenDaoManager = new GreenDaoManager(this);
    }

    private void initView() {
        recyclerView = findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 零食
     *
     * @param view
     */
    public void onQuerySnacksClick(View view) {
        notifyAdapter(greenDaoManager.querySnaks());
    }

    /**
     * 水果
     *
     * @param view
     */
    public void onQueryFruitsClick(View view) {
        notifyAdapter(greenDaoManager.queryFruts());
    }

    /**
     * 全部
     *
     * @param view
     */
    public void onQueryAllClick(View view) {
     List<GoodsModule> data =    greenDaoManager.queryGoods();
        notifyAdapter(data);
    }

    /**
     * 进货
     *
     * @param view
     */
    public void onAddGoodsClick(View view) {
        greenDaoManager.insertGoods();
    }

    /**
     * 改变展示数据
     */
    private void notifyAdapter(List<GoodsModule> dataSource) {
        adapter.setDataSource(dataSource);
    }

    @Override
    protected void onResume() {//重新查询商品
        super.onResume();
        List<GoodsModule> data =    greenDaoManager.queryGoods();
        notifyAdapter(data);
    }
}
