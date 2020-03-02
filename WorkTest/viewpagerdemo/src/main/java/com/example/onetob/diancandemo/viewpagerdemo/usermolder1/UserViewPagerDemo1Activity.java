package com.example.onetob.diancandemo.viewpagerdemo.usermolder1;


import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.onetob.diancandemo.viewpagerdemo.MainActivity;
import com.example.onetob.diancandemo.viewpagerdemo.R;
import com.example.onetob.diancandemo.viewpagerdemo.usermolder1.adapter.MyRecyclerViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class UserViewPagerDemo1Activity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdame1_layout);
        setViewPagerDemo();
    }

    private void setViewPagerDemo() {
        //1,获取控件
        ViewPager2 viewPager2 = findViewById(R.id.viewpager2);
        //2,准备数据
        List<Integer>listImgResouer = new ArrayList<>();
        listImgResouer.add(R.mipmap.bg1);
        listImgResouer.add(R.mipmap.bg2);
        listImgResouer.add(R.mipmap.bg3);
        listImgResouer.add(R.mipmap.bg4);

        //3,设置适配器
        MyRecyclerViewPagerAdapter adapter = new MyRecyclerViewPagerAdapter(UserViewPagerDemo1Activity.this,listImgResouer);
        viewPager2.setAdapter(adapter);
        //设置状态栏圆点
        setdots( viewPager2);
    }

    private void setdots(ViewPager2 viewPager2) {
        final LinearLayout ll_dots = findViewById(R.id.user_ll_dots);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0; i <ll_dots.getChildCount() ; i++) {
                    Log.d(MainActivity.TAG,position+"循环将所有的点设为未被选中转态:"+i);
                    //通过循环将所有的点设为未被选中转态
                    ll_dots.getChildAt(i).setBackgroundResource(R.drawable.user_dots_unselected);//设置ll_dots  item为unselected背景
                }
                ll_dots.getChildAt(position).setBackgroundResource(R.drawable.user_dots_selected);//设置ll_dots  选中的item为selected背景
            }
        });

    }

}
