package com.example.cardviewdemo.demo;

import android.os.Bundle;
import android.widget.ListView;

import com.example.cardviewdemo.R;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CardDemoActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carddemo_layout);
        ListView listView = findViewById(R.id.cardView_lv);
        //准备数据
        List<Msg> listData = MsgLab.generateMockList();
        //设置适配器
        MyAdapter adapter = new MyAdapter(CardDemoActivity.this,listData);
        listView.setAdapter(adapter);
    }
}
