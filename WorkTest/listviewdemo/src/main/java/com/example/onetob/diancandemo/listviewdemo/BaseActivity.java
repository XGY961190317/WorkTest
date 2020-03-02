package com.example.onetob.diancandemo.listviewdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

class BaseActivity extends AppCompatActivity {

    private ListView baseListView;
    private ImageView write;
    //初始化img数据
    int img[] = {R.mipmap.profile1, R.mipmap.profile2, R.mipmap.profile3, R.mipmap.profile4, R.mipmap.profile5, R.mipmap.profile6, R.mipmap.profile7, R.mipmap.profile8};
    private List<Msg> listData;
    private MyAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        baseListView = findViewById(R.id.lv_base);
        write = findViewById(R.id.write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Msg msg = new Msg(R.mipmap.profile9, "用户9", "这是动态新增的说说", false);
                listData.add(msg);
                adapter.notifyDataSetChanged();
                //设置listView自动显示到最新数据
                baseListView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
            }
        });
        //准备数据
        adapter = new MyAdapter(this, initData());
        baseListView.setAdapter(adapter);
        //设置是否点击  点赞按钮
        adapter.setOnIsLike(new MyAdapter.MyOnItemLikeListener() {

            @Override
            public void setOnItemLike(int position, boolean b) {
                listData.get(position).setLike(b);
                adapter.notifyDataSetChanged();
            }
        });
    }

    //获取数据
    private List<Msg> initData() {
        listData = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            Msg msg = new Msg(img[i - 1], "用户" + i, "今天天气好晴朗，处处好风光", false);
            listData.add(msg);
        }
        return listData;
    }

}
