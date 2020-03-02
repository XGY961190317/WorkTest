package com.example.onetob.diancandemo.fragmentdemo.test;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onetob.diancandemo.fragmentdemo.MainActivity;
import com.example.onetob.diancandemo.fragmentdemo.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(MainActivity.TAG,"fragment的onAttach()方法   fragment与activity关联");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MainActivity.TAG,"fragment的onCreate()方法   fragment创建好了");
    }

    //fragment创建视图时调用的方法
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d(MainActivity.TAG, "fragment的onCreateView()方法   fragment创建视图");
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(MainActivity.TAG, "fragment的onActivityCreated()方法   fragment——activity创建好了");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(MainActivity.TAG, "fragment的onStart()方法   fragment启动");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(MainActivity.TAG, "fragment的onResume()方法  fragment显示");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(MainActivity.TAG, "fragment的onPause()方法 fragment暂停");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(MainActivity.TAG, "fragment的onStop()方法  fragment停止");

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(MainActivity.TAG, "fragment的onDestroyView()方法  fragment销毁视图销毁");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.TAG, "fragment的onDestroy()方法  fragment销毁");
    }
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(MainActivity.TAG, "fragment的onDetach()方法  与activity接触关联");
    }
}
