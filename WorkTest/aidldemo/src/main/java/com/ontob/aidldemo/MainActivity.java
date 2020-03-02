package com.ontob.aidldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.ontob.servicedemo.demo.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //IBinder
    //ServicerConnection:用于绑定客户端和Service
    //进度监控
    private ServiceConnection conn = new ServiceConnection() {
        //当客户端正常连接着服务时，执行服务的绑定操作会被调用
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
           // Log.e("TAG", "慕课");
            IMyAidlInterface imai = IMyAidlInterface.Stub.asInterface(iBinder);
            try {
                imai.showProgress();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        //当客户端和服务的连接丢失了
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public void operate(View v) {
        switch (v.getId()) {
            case R.id.start://远程启动服务
                //通过action隐式启动服务
                Intent it1 = new Intent();
                it1.setPackage("com.ontob.servicedemo");//android 5.0以后必须指定包名
                it1.setAction("com.ontob.servicedemo.MyService");
                startService(it1);
                break;
            case R.id.stop://远程停止服务
                Intent it2 = new Intent();
                it2.setPackage("com.ontob.servicedemo");
                it2.setAction("com.ontob.servicedemo.MyService");
                stopService(it2);
                break;
            case R.id.bind://远程绑定服务
                //绑定服务：最大的 作用是用来实现对Service执行的任务进行进度监控
                //如果服务不存在： onCreate-->onBind-->onUnbind-->onDestory
                // （此时服务没有再后台运行，并且它会随着Activity的摧毁而解绑并销毁）
                //服务已经存在：那么bindService方法只能使onBind方法被调用，而unbindService方法只能使onUnbind被调用
                Intent it3 = new Intent();
                it3.setPackage("com.ontob.servicedemo");
                it3.setAction("com.ontob.servicedemo.MyService");
                bindService(it3, conn, BIND_AUTO_CREATE);

                break;
            case R.id.unbind://远程解绑服务
                //解绑服务
                unbindService(conn);
                break;
        }
    }
}
