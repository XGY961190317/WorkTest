package com.example.onetob.diancandemo.viewpagerdemo.module2;


import android.os.Bundle;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;
import com.example.onetob.diancandemo.viewpagerdemo.R;
import com.example.onetob.diancandemo.viewpagerdemo.module2.fragment.CJFragment;
import com.example.onetob.diancandemo.viewpagerdemo.module2.fragment.JSFragment;
import com.example.onetob.diancandemo.viewpagerdemo.module2.fragment.KJFragment;
import com.example.onetob.diancandemo.viewpagerdemo.module2.fragment.SPFragment;
import com.example.onetob.diancandemo.viewpagerdemo.module2.fragment.TYFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class ViewPagerDemo2Activity extends AppCompatActivity {

    private XTabLayout tabLayout;
    private ViewPager2 viewPager2;

    //XTabLayout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagerdemo2);

        setXTableLayout();
        setViewPafer2();
    }

    private void setViewPafer2() {
        //1,获取控件
        viewPager2 = findViewById(R.id.view_pafer2);
        //准备资源
        final List<Fragment> vp_list = new ArrayList<>();
        vp_list.add(new CJFragment());
        vp_list.add(new JSFragment());
        vp_list.add(new KJFragment());
        vp_list.add(new SPFragment());
        vp_list.add(new TYFragment());
        //设置适配器
        FragmentStateAdapter vp2Adapter = new FragmentStateAdapter(getSupportFragmentManager()) {
            //设置长度
            @Override
            public int getItemCount() {
                return vp_list.size();
            }

            //设置子项
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return vp_list.get(position);
            }
        };
        viewPager2.setAdapter(vp2Adapter);
        //设置ViewPager和XTabLayout联动
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();//设置滑到哪一页，对应的tab文本选中
            }
        });
    }

    private void setXTableLayout() {
        //为 XtableLayout 添加内容
        //1,找到XtableLayout
        tabLayout = findViewById(R.id.table);
        //2,动态添加内容(财经，军事，科技，视频，体育)
        //a,实例化导航块
        XTabLayout.Tab t1 = tabLayout.newTab();
        //b,设置名字
        t1.setText("财经");
        //c,添加
        tabLayout.addTab(t1);
        tabLayout.addTab(tabLayout.newTab().setText("军事"));
        tabLayout.addTab(tabLayout.newTab().setText("科技"));
        tabLayout.addTab(tabLayout.newTab().setText("视频"));
        tabLayout.addTab(tabLayout.newTab().setText("体育"));
        //3,设置切换效果
        tabLayout.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                //选中时触发
                //获取当前导航块的位置，及文本
                int postion = tab.getPosition();
                String text = tab.getText().toString();
                viewPager2.setCurrentItem(postion);//设置点击XTabLayout时对应的viewpager页
                Toast.makeText(ViewPagerDemo2Activity.this, "当前导航栏的位置为:" + postion + "所显示的文本为:" + text, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
                //未选中时触发
            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
                //重新选中触发
            }
        });
    }
}
