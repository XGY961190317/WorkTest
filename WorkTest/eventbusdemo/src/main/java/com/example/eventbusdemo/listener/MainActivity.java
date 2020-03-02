package com.example.eventbusdemo.listener;

import android.os.Bundle;

import com.example.eventbusdemo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity-X";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击加好按钮，显示fragmentDialog
                ListenerSherFragmentDialog fragmentDialog = new ListenerSherFragmentDialog();
                fragmentDialog.show(getSupportFragmentManager(), "Publicsher");
                //设置fragmentdialog对话款的监听事件，如果返回来的是success,则设置成功的图片，fail则设置失败的图片
                fragmentDialog.setOnEventListenver(new ListenerSherFragmentDialog.OnEventListenver() {
                    @Override
                    public void onSuccess(int index) {
                        Log.d(TAG,"INDEX:="+index);
                        setImgSrc(R.drawable.ic_success);//设置成功的图片
                    }

                    @Override
                    public void onFail(int index) {
                        Log.d(TAG,"INDEX:="+index);
                        setImgSrc(R.drawable.ic_fail);//设置失败的图片
                    }
                });
            }
        });

    }

    /**
     * 当收到信息后，设置img图片显示
     *
     * @param resId 图片资源的id
     */
    private void setImgSrc(int resId) {
        ImageView img = findViewById(R.id.listener_images);
        img.setImageResource(resId);//成功时显示 success   //当失败的时候显示fail
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
