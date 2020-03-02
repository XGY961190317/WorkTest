package com.example.onetob.diancandemo.fragmentdemo.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.onetob.diancandemo.fragmentdemo.MainActivity;
import com.example.onetob.diancandemo.fragmentdemo.R;


public class TestActivity  extends AppCompatActivity{

    private Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.d(MainActivity.TAG, "activity的onCreate()方法");
        addFragmentView();
        findViewById(R.id.onclick_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1,获取Fragment管理器
                FragmentManager fm = getSupportFragmentManager();
                //2,,获取Fragment对象
                Fragment fragment1 = fm.findFragmentById(R.id.fragment1);
                //3,获取fragment对象的视图
                View view = fragment1.getView();

                //4,在视图中获取要操作的控件
                TextView fragment1_tv = view.findViewById(R.id.fragment1_tv);
                //5,修改控件内容
                fragment1_tv.setText("这是fragment1动态修改后的内容");
            }
        });
        findViewById(R.id.f2_onclick_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //动态加载，直接可以通过fragment2获取对象
                View fv = fragment2.getView();
                TextView f2_tv = fv.findViewById(R.id.fragment2_tv);
                f2_tv.setText("这是fragment2动态修改后的内容");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MainActivity.TAG, "activity的onStart()方法");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MainActivity.TAG, "activity的onRestart()方法");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MainActivity.TAG, "activity的onResume()方法");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MainActivity.TAG, "activity的onPause()方法");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MainActivity.TAG, "activity的onStop()方法");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.TAG, "activity的onDestroy()方法");
    }

    /**
     * 动态添加fragment
     */
    private void addFragmentView() {
        //1,获取管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        //2,,获取Fragment事务(开启事务)
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //3,动态加载Fragment
        //参数1，承载fragment的容器，
        //参数2，fragment的对象实例
        fragment2 = new Fragment2();
        transaction.add(R.id.container, fragment2);
        //4,提交事务
        transaction.commit();
    }
}
