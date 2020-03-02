package com.example.lvyouapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lvyouapp.adapter.MyVp2Adapter;
import com.example.lvyouapp.fragment.FindFragment;
import com.example.lvyouapp.fragment.IndexFragment;
import com.example.lvyouapp.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity_X";
    private ViewPager2 vp2;
    private RadioGroup bgBtn;
    private LinearLayout LlLin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setViewPager();
        setListener();
    }

    private void initView() {
        //1,获取控件
        vp2 = findViewById(R.id.vp_2);
        bgBtn = findViewById(R.id.btn_rg);
        LlLin = findViewById(R.id.ll_lin);

    }

    private void setListener() {
        ((RadioButton) bgBtn.getChildAt(0)).setChecked(true);//第一次进来设置第一个RadioButoon为选中状态
        LlLin.getChildAt(0).setBackgroundColor(getResources().getColor(R.color.blue2));//第一次进来设置第一个指示为选中状态
        bgBtn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int indexOfChild = group.indexOfChild(group.findViewById(checkedId));// 获取每隔item的索引index
                Log.d(TAG,"INDEX=:"+indexOfChild);
                vp2.setCurrentItem(indexOfChild);
            }
        });
        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                ((RadioButton) bgBtn.getChildAt(position)).setChecked(true);//设置滑动到的页的导航栏为选中状态
               int ChildCount =  LlLin.getChildCount();//获取Layout子项的个数
                for (int i = 0; i < ChildCount; i++) {
                    LlLin.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.transparent));
                }
                LlLin.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.blue2));
            }
        });

    }

    private void setViewPager() {
        //2,准备数据
        List<Fragment> listVpData = new ArrayList<>();
        listVpData.add(new IndexFragment());
        listVpData.add(new FindFragment());
        listVpData.add(new MeFragment());
        //3,设置适配器
        MyVp2Adapter adapter = new MyVp2Adapter(getSupportFragmentManager(),listVpData);
        vp2.setAdapter(adapter);
    }
}
