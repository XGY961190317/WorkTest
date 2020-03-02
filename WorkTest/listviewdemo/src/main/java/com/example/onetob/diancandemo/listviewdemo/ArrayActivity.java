package com.example.onetob.diancandemo.listviewdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 1,准备布局
 * 2,准备数据
 * 3,实例化适配器（布局+数据源）
 * 4,为ListView设置适配器
 */
class ArrayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);
        ListView arrayList = findViewById(R.id.lv_array);
        //参数1，上下文
        //参数2，item布局
        //参数3，数据源
        //使用Android自带的资源:android.R.layout.simple_list_item_1 等等
        String arrayData[] = {"aaa","bbb","ccc","ddd","eee","fff","ggg","aaa","bbb","ccc","ddd","eee","fff","ggg","aaa","bbb","ccc","ddd","eee","fff","ggg"};
      //使用系统布局演示
       // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayData);
        //使用带指定textView控件的构造方法
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.array_item_layout,R.id.tv_test,arrayData);
        arrayList.setAdapter(arrayAdapter);
    }
}
