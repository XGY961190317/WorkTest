package com.example.onetob.diancandemo.fragmentdemo.demo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onetob.diancandemo.fragmentdemo.R;

import androidx.fragment.app.Fragment;


public class ListFragment extends Fragment {
    //3,在自定义fragment中声明一个接口的引用，在onAttach()方法里面，为第三步的引用赋值
    private MyListener ml;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ml = (MyListener) getActivity();
        ml.sendMsg("消息");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    //fragment向activity传值
    //1,定义一个接口，在该接口中声明一个用于传递数据的方法

    interface MyListener{
       public void sendMsg(String msg);
    }


}
