package com.example.myapplicationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationdemo.awork.activity.WorkIndexActivity;
import com.example.myapplicationdemo.awork.utils.L;
import com.example.myapplicationdemo.myapp.MyApplication;
import com.squareup.otto.Bus;

/**
 * 使用通过Application实现共享的Bus
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity-X";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L.d(TAG,"onCreate:"+getApplication()+"  :  "+getApplicationContext()+"    :    "+this);
        findViewById(R.id.tonext_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });
        findViewById(R.id.tonext_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this,MyService.class));
            }
        });
        findViewById(R.id.set_application_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication myApplication = (MyApplication) getApplication();
                myApplication.setName("MainActivity1");

            }
        });
        findViewById(R.id.get_application_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication myApplication = (MyApplication) getApplication();
                String name = myApplication.getName();
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication myApplication = (MyApplication) getApplication();
        Bus bus = myApplication.getBus();//获取Bus对象
        L.d(TAG," onResume bus="+bus);
        bus.register(this);//注册Bus
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication myApplication = (MyApplication) getApplication();
        Bus bus = myApplication.getBus();//获取Bus对象
        L.d(TAG,"onPause bus="+bus);
        bus.unregister(this);//取消注册Bus
    }

    public void ManOnclick(View view) {
        switch (view.getId()){

            case R.id.tonext_activity:
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
                break;
            case R.id.tonext_service:
                startActivity(new Intent(MainActivity.this,MyService.class));
                break;
            case R.id.set_application_name:
                MyApplication myApplication = (MyApplication) getApplication();
                myApplication.setName("MainActivity1");
                break;
            case R.id.get_application_name:
                MyApplication myApplication1 = (MyApplication) getApplication();
                String name = myApplication1.getName();
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                break;
            case R.id.workBtn:
                startActivity(new Intent(MainActivity.this,WorkIndexActivity.class));
                break;

        }
    }
}
