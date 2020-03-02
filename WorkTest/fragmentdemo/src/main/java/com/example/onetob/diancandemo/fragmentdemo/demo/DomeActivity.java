package com.example.onetob.diancandemo.fragmentdemo.demo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;

import com.example.onetob.diancandemo.fragmentdemo.MainActivity;
import com.example.onetob.diancandemo.fragmentdemo.R;

public class DomeActivity extends AppCompatActivity implements ListFragment.MyListener {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        RadioButton rb_index = findViewById(R.id.btn_index);
        rb_index.setChecked(true);//第一次进来选择index btn
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container_demo, new IndexFragment());
        ft.commit();
    }

    public void myclick(View v) {
        FragmentManager fms = getSupportFragmentManager();
        FragmentTransaction transaction = fms.beginTransaction();
        switch (v.getId()) {
            case R.id.btn_index:
                Log.d(MainActivity.TAG, "indexFragment_btn");
                IndexFragment indexFragment = new IndexFragment();
                transaction.replace(R.id.container_demo, indexFragment);
                break;
            case R.id.btn_channel:
                Log.d(MainActivity.TAG, "channelFragment_btn");
                //activity传值到fragment中去，调用setAguements
                //1,实例化fragment
                ChannelFragment channelFragment = new ChannelFragment();
                //2,实例化bundle对象
                Bundle bundle = new Bundle();
                //3,存入数据到bundle对象中、
                bundle.putInt("code", 123);
                bundle.putString("msg1", "activity发往fragment的数据");
                //4,调用fragment的调用setAguements方法
                channelFragment.setArguments(bundle);
                //5,添加或者替换显示的fragment
                transaction.replace(R.id.container_demo, channelFragment);
                break;
            case R.id.btn_list:
                Log.d(MainActivity.TAG, "listFragmen_btn");
                //fragment向activity传值
                //1,定义一个接口，在该接口中声明一个用于传递数据的方法
                //2,让activity实现该接口，重写该回调方法,获取传入的值，然后做处理
                //3,在自定义fragment中声明一个接口的引用，在onAttach()方法里面，为第三步的引用赋值
                //4,用引用调用传递数据的方法
                ListFragment listFragment = new ListFragment();
                transaction.replace(R.id.container_demo, listFragment);
                break;
            case R.id.btn_me:
                Log.d(MainActivity.TAG, "meFragmen_btn");
                MeFragment meFragment = new MeFragment();
                transaction.replace(R.id.container_demo, meFragment);
                break;
        }
        transaction.commit();
    }

    public String sendMsg() {

        Log.d(MainActivity.TAG, "这是通过一个普通方法传递到fragment的值");
        String str = "123";
        return str;
    }
    //2,让activity实现该接口，重写该回调方法,获取传入的值，然后做处理
    @Override
    public void sendMsg(String msg) {
        Log.d(MainActivity.TAG,"fragment传回的数据:"+msg);
    }
}
