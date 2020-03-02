package com.example.onetob.diancandemo.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;


import androidx.annotation.NonNull;

public class MyDialog extends Dialog {
    public MyDialog(@NonNull final Context context, int themeResId) {
        super(context,themeResId);
        //设置对话框布局
        setContentView(R.layout.dialog_layout_one);
        Button btnOne = findViewById(R.id.yes_btn);
        Button btnTwo = findViewById(R.id.no_btn);
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);//退出整个系统程序
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //让对话框消失
                dismiss();
            }
        });
    }
}
