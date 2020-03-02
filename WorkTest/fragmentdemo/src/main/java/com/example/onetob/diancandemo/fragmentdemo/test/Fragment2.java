package com.example.onetob.diancandemo.fragmentdemo.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onetob.diancandemo.fragmentdemo.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Fragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2,null);
        TextView  textView = view.findViewById(R.id.fragment2_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();//获取fragment管理器
                FragmentTransaction ft = fm.beginTransaction();//开启fragment事务
                //通过fragment事务添加其他fragment
                //参数1，承载fragment的容器，
                //参数2，fragment的对象实例
                ft.add(R.id.container,new Fragment3());
                //移除fragment
                //参数，fragment对象
                //ft.remove()
                ft.commit();//提交事务
            }
        });
        return view;
    }
}
