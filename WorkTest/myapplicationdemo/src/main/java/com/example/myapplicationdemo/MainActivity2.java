package com.example.myapplicationdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationdemo.awork.utils.L;
import com.example.myapplicationdemo.myapp.MyApplication;
import com.squareup.otto.Bus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
/**
 * 使用通过静态单例实现共享的Bus
 */
public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        L.d(MainActivity.TAG, "onCreate:" + getApplication() + "  :  " + getApplicationContext() + "    :    " + this);
        findViewById(R.id.get_application_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication myApplication = (MyApplication) getApplication();
                String name = myApplication.getName();
                Toast.makeText(MainActivity2.this, name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bus bus = BusProvider.getBUS();
        L.d(MainActivity.TAG," onResume bus="+bus);
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Bus bus = BusProvider.getBUS();
        L.d(MainActivity.TAG," onPause bus="+bus);
        bus.unregister(this);
    }
}
