package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sqlitedemo.downloaddatadistolv.WorkTestActivity;
import com.example.sqlitedemo.higsql.HighSqlTestActivity;

public class MainActivity extends AppCompatActivity {
    public  static final String TAG = "MainActivity-X";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testSql(View view) {
        switch (view.getId()){
            case R.id.baseSqlTest_btn:
                startActivity(new Intent(MainActivity.this,BaseSqlTestActivity.class));
            break;
            case R.id.highSqlTest_btn:
                    startActivity(new Intent(MainActivity.this,HighSqlTestActivity.class));
            break;
            case R.id.DbAPITest_btn:
                startActivity(new Intent(MainActivity.this,DbAPITestActivity.class));
                break;
            case R.id.work_btn:
                startActivity(new Intent(MainActivity.this,WorkTestActivity.class));
                break;
        }
    }
}
