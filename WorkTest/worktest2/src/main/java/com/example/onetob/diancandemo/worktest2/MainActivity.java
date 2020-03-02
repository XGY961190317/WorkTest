package com.example.onetob.diancandemo.worktest2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView display_tv;
    AlertDialog.Builder builder;
    Resources res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display_tv = findViewById(R.id.display_tv);
        res = getResources();//获取资源
        builder = new AlertDialog.Builder(this);

    }

    public void myOnclick(View view) {
        switch (view.getId()) {
            case R.id.data_dialog: //日期对话框
                showDataDialog();
                break;
            case R.id.single_dialog://单选对话框
                showSingleDialog();
                break;
            case R.id.much_dialog://多选对话框
                showMuchDialog();
                break;
        }
    }

    public void showDataDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                display_tv.setText(i+"年\n"+(i1+1)+"月\n"+i2+"日");
                Toast.makeText(MainActivity.this, i+"年\n"+(i1+1)+"月\n"+i2+"日", Toast.LENGTH_SHORT).show();
            }


        }, Calendar.DAY_OF_YEAR, Calendar.DAY_OF_MONTH, Calendar.DATE);
        datePickerDialog.show();
    }

    public void showSingleDialog() {
        //定义数组
        String[] single_dialog;

        single_dialog = res.getStringArray(R.array.single_dialog);//通过数组名获得数组
        if (single_dialog != null && builder != null) {
            setSingleDialog(single_dialog, builder);
        }
    }

    public void setSingleDialog(final String[] single_dialog, AlertDialog.Builder builder) {

        builder.setTitle("性别选择")
                .setIcon(R.mipmap.star)
                .setSingleChoiceItems(single_dialog, android.R.layout.simple_expandable_list_item_1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        String str_item = single_dialog[i].toString();//获得当前控件的值
                        Toast.makeText(MainActivity.this, "你选择了：" + str_item, Toast.LENGTH_SHORT).show();
                        display_tv.setText(single_dialog[i].toString());
                    }
                }).create().show();
    }

    public void showMuchDialog() {
        final String[]  much_arry=res.getStringArray(R.array.multi_dialog);
        final boolean []isCheckedItme =new boolean[much_arry.length];
        if (much_arry!=null&&builder != null) {
            builder.setTitle("选择个人爱好")
                    .setIcon(R.mipmap.star)
                    .setMultiChoiceItems(much_arry, isCheckedItme, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            isCheckedItme[i]=b;
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StringBuffer stringBuffer = new StringBuffer();
                            for (int j = 0; j <much_arry.length ; j++) {
                                stringBuffer.append(much_arry[j]+"\n");
                            }
                            display_tv.setText(stringBuffer.toString());
                        }
                    }).show();
        }
    }
}
