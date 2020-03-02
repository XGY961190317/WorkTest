package com.example.onetob.diancandemo.fragmentdemo;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.onetob.diancandemo.fragmentdemo.demo.DomeActivity;
import com.example.onetob.diancandemo.fragmentdemo.test.Fragment2;
import com.example.onetob.diancandemo.fragmentdemo.test.TestActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity_X";
    private Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myOnClick(View view) {
        switch (view.getId()){
            case R.id.btn_test:

                Intent testIntent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(testIntent);

                break;
            case R.id.btn_demo:

                Intent demoIntent = new Intent(MainActivity.this,DomeActivity.class);
                startActivity(demoIntent);
        }
    }
}
