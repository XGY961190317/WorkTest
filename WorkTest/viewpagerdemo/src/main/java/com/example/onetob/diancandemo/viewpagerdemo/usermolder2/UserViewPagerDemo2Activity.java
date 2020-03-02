package com.example.onetob.diancandemo.viewpagerdemo.usermolder2;

import android.os.Bundle;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;
import com.example.onetob.diancandemo.viewpagerdemo.R;
import com.example.onetob.diancandemo.viewpagerdemo.usermolder2.adapter.MyUserViewPagerDemo2Adapter;
import com.example.onetob.diancandemo.viewpagerdemo.usermolder2.fragment.UserCJFragment;
import com.example.onetob.diancandemo.viewpagerdemo.usermolder2.fragment.UserJSFragment;
import com.example.onetob.diancandemo.viewpagerdemo.usermolder2.fragment.UserKJFragment;
import com.example.onetob.diancandemo.viewpagerdemo.usermolder2.fragment.UserSPFragment;
import com.example.onetob.diancandemo.viewpagerdemo.usermolder2.fragment.UserTYFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class UserViewPagerDemo2Activity extends AppCompatActivity {

    private XTabLayout tabLayout;
    private ViewPager2 userVp2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdame2_layout);
        setXTabLayout();
        setViewPagers();
        setListener();
    }


    private void setXTabLayout() {
        //(财经，军事，科技，视频，体育)
        //1,获取控件
        tabLayout = findViewById(R.id.xTab);
//        XTabLayout.Tab tab =  tabLayout.newTab();//获取实例化导航模块
//        tab.setText("财经");
        //c,添加
        tabLayout.addTab(tabLayout.newTab().setText("财经"));
        tabLayout.addTab(tabLayout.newTab().setText("军事"));
        tabLayout.addTab(tabLayout.newTab().setText("科技"));
        tabLayout.addTab(tabLayout.newTab().setText("视频"));
        tabLayout.addTab(tabLayout.newTab().setText("体育"));
    }

    private void setViewPagers() {
        //1,获取控件
        userVp2 = findViewById(R.id.user_viewpager);
        //2,准备资源
        List<Fragment>user_vplist = new ArrayList<>();
        user_vplist.add(new UserCJFragment());
        user_vplist.add(new UserJSFragment());
        user_vplist.add(new UserKJFragment());
        user_vplist.add(new UserSPFragment());
        user_vplist.add(new UserTYFragment());
        //3,设置适配器
        MyUserViewPagerDemo2Adapter adapter = new MyUserViewPagerDemo2Adapter(getSupportFragmentManager(),user_vplist);
        userVp2.setAdapter(adapter);
    }

    private void setListener() {
        //设置tablayout  item选中的时间
        tabLayout.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                //选中时调用
                int index = tab.getPosition();//获取当前选中文本的索引
               String text =  tabLayout.getTabAt(index).getText().toString();
                userVp2.setCurrentItem(tab.getPosition());//将viewPager设置到当前选中的tablayout  item的页
               Toast.makeText(UserViewPagerDemo2Activity.this,"选中的是:"+index+"  内容是:"+text,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
                //没选中时调用

            }

            @Override

            public void onTabReselected(XTabLayout.Tab tab) {
                //重选时调用
            }
        });
        //设置viewpger变化事件
        userVp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();//设置当前position对应的tabLayout的item为选中状态
            }
        });
    }
}
