package com.example.sqlitedemo.downloaddatadistolv;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.sqlitedemo.MainActivity;
import com.example.sqlitedemo.R;
import com.example.sqlitedemo.downloaddatadistolv.bean.BookEntitiy;
import com.example.sqlitedemo.downloaddatadistolv.datadao.DataDao;
import com.example.sqlitedemo.downloaddatadistolv.bean.Book;
import com.example.sqlitedemo.downloaddatadistolv.net.BookBiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * 访问路径：http://www.imooc.com./api/teacher?type=2&page=1
 */
public class WorkTestActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 10000;
    private String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private ListView disLv;
    List<Book> mDatas = new ArrayList<>();
    private SimpleAdapter mAapter;
    private BookBiz bookBiz = new BookBiz();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worktest);
        setTitle("书籍列表");
        disLv = findViewById(R.id.worktest_lv);
        int permisson = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permisson != PackageManager.PERMISSION_GRANTED) {
            //动态去申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        loadDatas(true);
    }

    private void loadDatas(boolean b) {
        Log.e(MainActivity.TAG, "loadFailed ex=loadDatas ");
        bookBiz.loadDatas(WorkTestActivity.this, new BookBiz.CallBack() {
            @Override
            public void onSuccess(List<Book> bookList) {
                mDatas.clear();
                Log.e(MainActivity.TAG, "loadFailed ex=loadDatas "+bookList.size());
                mDatas.addAll(bookList);
              //  mAapter.notifyDataSetChanged();
                setListView(mDatas);
            }

            @Override
            public void onFaill(Exception ex) {
                Log.e(MainActivity.TAG, "loadFailed ex= " + ex.getMessage());
            }
        },b);
    }

    private void setListView(List<Book> li) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("learner", mDatas.get(i).getLearner());
            map.put("name", mDatas.get(i).getName());
            mapList.add(map);
        }
        String[] from = {"learner", "name"};
        int[] to = {R.id.learner, R.id.name};
        mAapter = new SimpleAdapter(WorkTestActivity.this, mapList, R.layout.work_test_item_layout, from, to);
        disLv.setAdapter(mAapter);
    }
}
