package com.example.expandablelistviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.expandablelistviewdemo.adapter.ExpandableAdapter;
import com.example.expandablelistviewdemo.bean.Chapter;
import com.example.expandablelistviewdemo.bean.ChapterLab;
import com.example.expandablelistviewdemo.net.ChapterBiz;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity-X";

    private ExpandableListView elv;
    private ChapterBiz cb = new ChapterBiz();
    private List<Chapter> mDatas = new ArrayList<>();
    private ExpandableAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //准备数据
        //程序一进来先设置适配器
        mAdapter = new ExpandableAdapter(this,mDatas);
        elv.setAdapter(mAdapter);
        //通过逻辑类的下载数据方法进行数据处理
        loadDatas(true);
    }

    /**
     * 获取数据的处理方法
     * @param b  是否从本地数据库获取数据的标记位
     */
    private void loadDatas(boolean b) {//数据处理
        /**
         * 通过逻辑操作对象，调用获取数方法，并通过ChapterBiz回调结果
         */
        cb.loadDatas(this, new ChapterBiz.CallBack() {
            @Override
            public void onSuccess(List<Chapter> chapterList) {
                //获取数据成功，设置数据
                //在操作结果集合前要记得先清空之前的数据
                mDatas.clear();
                //添加数据到集合
                mDatas.addAll(chapterList);
                //通知数据库，数据更新
                mAdapter.notifyDataSetChanged();
            }

            /**
             * 如果获取数据失败，打印信息
             * @param ex
             */
            @Override
            public void onFailed(Exception ex) {
                ex.printStackTrace();
                Log.e(TAG, "loadFailed ex= " + ex.getMessage());

            }
        },b);
    }

    private void initView() {
        elv = findViewById(R.id.expand_listview);

    }
    private void initEvent() {
        //点击分组时调用
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d(TAG,"setOnGroupClickListener groupPosition="+groupPosition);
                return false;
            }
        });
        //分组收缩时调用
        elv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.d(TAG,"setOnGroupCollapseListener groupPosition="+groupPosition);
            }
        });
        //分组展开时调用
        elv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.d(TAG,"setOnGroupExpandListener groupPosition="+groupPosition);
            }
        });
        //点击子项时调用
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.d(TAG,"setOnChildClickListener groupPosition="+groupPosition+"    childPosition="+childPosition);
                return false;
            }
        });
    }
}
