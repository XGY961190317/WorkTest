package com.example.handlerdemo.diglett;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handlerdemo.R;

import java.lang.ref.WeakReference;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DiglettActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener {

    public static final int WHAT_CODE = 1000;
    private TextView diglettText;
    private ImageView diglettImg;
    private Button diglettBtn;
    //申明一组坐标，作为diglett的显示坐标
    int mPosition[][] = new int[][]{
            {342, 180},{432, 880},
            {521, 256},{429, 780},
            {456, 976},{145, 665},
            {123, 678},{564, 567}
    };
    private int mTotalCount;//老鼠出来的总数
    private int mSuccessCount;//打到的数量
    public static final int MAX_COUNT = 10;//一共有多少只
    DiglettHandler handler = new DiglettHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diglett_activity);
        setTitle("打地鼠");
        initView();

    }

    private void initView() {
        diglettText = findViewById(R.id.diglett_text);
        diglettImg = findViewById(R.id.diglett_img);
        diglettBtn = findViewById(R.id.diglett_btn);
        diglettBtn.setOnClickListener(this);
        diglettImg.setOnTouchListener(this);
    }

    public void start() {

        //初始化信息
        diglettBtn.setText("游戏中");//游戏中，禁止点击
        diglettBtn.setEnabled(false);//设置btn没有交点，不能点击
        diglettText.setVisibility(View.VISIBLE);
        //第一次发信息给handler
        getNext(0);//随机给一个数
    }

    ;

    /**
     * 获取下一只的位置，使用随机数
     *
     * @param daleyTime 延迟发送下一只出现的位置信息
     */
    public void getNext(int daleyTime) {
        //获取下一只diglett的位置
        int postion = new Random().nextInt(mPosition.length);
        Message message = Message.obtain();
        message.what = WHAT_CODE;
        message.arg1 = postion;
        handler.sendMessageDelayed(message, daleyTime);
        mTotalCount++;//出现一个地鼠，记一次数
    }

    @Override
    public void onClick(View v) {
        start();//点击开始游戏
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.setVisibility(View.GONE);//打中后，隐藏老鼠图标
        mSuccessCount++;//打中一个，寄一个成功的数量
        diglettText.setText("游戏开始了，打中了"+mSuccessCount+"只地鼠,一共有"+MAX_COUNT+"只地鼠");
        return false;
    }

    static class DiglettHandler extends Handler {
        //弱引用
        final WeakReference<DiglettActivity> mWeakReference;
        DiglettHandler(DiglettActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            DiglettActivity activity = mWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case WHAT_CODE:
                        if(activity.mTotalCount>activity.MAX_COUNT){//如果地鼠出现的数量大于最大的数量，结束游戏
                            activity.clear();//清除数据
                            Toast.makeText(activity,"地鼠打完了",Toast.LENGTH_SHORT).show();
                            return;//直接return
                        }
                        int position = msg.arg1;
                        activity.diglettImg.setX(activity.mPosition[position][0]);//设置坐标
                        activity.diglettImg.setY(activity.mPosition[position][1]);
                        activity.diglettImg.setVisibility(View.VISIBLE);//设置显示出来
                        activity.getNext(new Random().nextInt(500) + 300);//设置下一个坐标
                        break;
                }
            }
        }
    }

    private void clear() {
        //清除所有的数据，显示
        mTotalCount=0;
        mSuccessCount=0;
        diglettImg.setVisibility(View.GONE);
        diglettBtn.setText("开始游戏");
        diglettBtn.setEnabled(true);
    }
}
