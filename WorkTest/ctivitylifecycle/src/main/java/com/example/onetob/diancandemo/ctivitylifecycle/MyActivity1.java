package com.example.onetob.diancandemo.ctivitylifecycle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MyActivity1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my1);
        Log.d(MainActivity.TAG, "第二个界面(MyActivity1)的onCreate(创建)方法");
    }

    //启动方法
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MainActivity.TAG, "第二个界面(MyActivity1)的onStart（启动）方法");
    }

    //恢复方法
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MainActivity.TAG, "第二个界面(MyActivity1)的onResume（恢复）方法");
    }

    //暂停方法（activity部分不可见）
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MainActivity.TAG, "第二个界面(MyActivity1)的onPause（暂停）方法");
    }

    //停止方法（activity完全不可见）
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MainActivity.TAG, "第二个界面(MyActivity1)的onStop（停止）方法");
    }

    //重启方法activity被onstop要重新恢复到运行状态是调用
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MainActivity.TAG, "第二个界面(MyActivity1)的onRestart（重启）方法");
    }

    //销毁方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.TAG, "第二个界面(MyActivity1)的onDestroy（销毁）方法");
    }

}
