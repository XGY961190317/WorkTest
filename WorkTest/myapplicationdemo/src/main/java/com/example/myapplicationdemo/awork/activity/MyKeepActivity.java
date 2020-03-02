package com.example.myapplicationdemo.awork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplicationdemo.R;
import com.example.myapplicationdemo.awork.bean.Article;
import com.example.myapplicationdemo.awork.db.Dao;
import com.example.myapplicationdemo.awork.utils.L;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 收藏夹显示界面
 */
public class MyKeepActivity extends AppCompatActivity {
    Dao dao = new Dao();
    private ListView keepLv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mykeep);
        Intent intent = getIntent();
        ArrayList<Integer> list = intent.getIntegerArrayListExtra("keepList");//获取到上一个界面床底过来的值
        if (list != null&&list.size()>0) {
            L.d(WorkIndexActivity.TAG, list.toString());//查看数据是否传递成功
            keepLv = findViewById(R.id.keep_lv);
            setListView(list);
        } else  {
            Toast.makeText(this, "收藏夹还空空如也哦,需要到首页添加喜欢的内容后查看啦！", Toast.LENGTH_SHORT).show();
        }
    }

    private void setListView(ArrayList<Integer> list) {
        final List<Article> resultList = new ArrayList<>();
        List<String> resultTitleList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Article article = dao.loadFromDb(this, list.get(i));//通过id从数据库查询对应的数据
            resultTitleList.add(article.getTitle());
            resultList.add(article);
        }
        /**
         * 参数1：上下文
         * 参数2：layout，
         * 参数3：主要设置的textView的id
         * 参数4，text集合
         */
        if (resultTitleList != null) {
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.keep_lv, R.id.keeplv_title, resultTitleList);//使用数组适配器
            keepLv.setAdapter(adapter);
        }
        /**
         * 监听点击item的监听事件，处理item显示
         */
        keepLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyKeepActivity.this, KeepItemActivity.class);
                Article article = resultList.get(position);
                intent.putExtra("articleContent", article);//在跳转的时候，将这个item的对象信息带过去，传递对象需要先序列化
                startActivity(intent);
            }
        });
    }
}
