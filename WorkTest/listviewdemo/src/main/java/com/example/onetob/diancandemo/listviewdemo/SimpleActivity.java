package com.example.onetob.diancandemo.listviewdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SimpleActivity  extends AppCompatActivity{
    List<Map<String,Object>> listData = new ArrayList<>();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ListView listView = findViewById(R.id.lv_simple);
        //参数1，this
        //参数2，数据源
        initData();
        //参数3，每一项的布局   R.layout.simple_item_layout
        //参数4，map 的键组成的数组
        String nameString [] = {"img","name","mood"};
        //参数5，item布局中控件的id组成的数组
        int idCode[]={R.id.qq_img_iv,R.id.qq_name_tv,R.id.qq_mood_tv};
        SimpleAdapter simpleAdapter =  new SimpleAdapter(SimpleActivity.this,listData,R.layout.simple_item_layout,nameString,idCode);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> map = listData.get(position);
                String name = map.get("name").toString();
                String mood = map.get("mood").toString();
                Toast.makeText(SimpleActivity.this,name,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        //左边： 头像  右边：右上 ，名字  右下，签名
        Map<String,Object> map1 = new HashMap<String, Object>();
        map1.put("img",R.mipmap.caocao);
        map1.put("name","caocao");
        map1.put("mood","宁教我负天下人，休教天下人负我");

        Map<String,Object> map2 = new HashMap<String, Object>();
        map2.put("img",R.mipmap.zhenji);
        map2.put("name","zhenji");
        map2.put("mood","飘摇兮若流风之回雪，仿佛兮若轻云之闭月");

        Map<String,Object> map3 = new HashMap<String, Object>();
        map3.put("img",R.mipmap.simayi);
        map3.put("name","simayi");
        map3.put("mood","无奈天命之子");

        Map<String,Object> map4 = new HashMap<String, Object>();
        map4.put("img",R.mipmap.guojia);
        map4.put("name","guojia");
        map4.put("mood","哦");

        Map<String,Object> map5 = new HashMap<String, Object>();
        map5.put("img",R.mipmap.caocao);
        map5.put("name","caocao");
        map5.put("mood","宁教我负天下人，休教天下人负我");

        Map<String,Object> map6 = new HashMap<String, Object>();
        map6.put("img",R.mipmap.zhenji);
        map6.put("name","zhenji");
        map6.put("mood","飘摇兮若流风之回雪，仿佛兮若轻云之闭月");

        Map<String,Object> map7 = new HashMap<String, Object>();
        map7.put("img",R.mipmap.simayi);
        map7.put("name","simayi");
        map7.put("mood","无奈天命之子");

        Map<String,Object> map8 = new HashMap<String, Object>();
        map8.put("img",R.mipmap.guojia);
        map8.put("name","guojia");
        map8.put("mood","哦");
        listData.add(map1);
        listData.add(map2);
        listData.add(map3);
        listData.add(map4);
        listData.add(map5);
        listData.add(map6);
        listData.add(map7);
        listData.add(map8);

    }
}
