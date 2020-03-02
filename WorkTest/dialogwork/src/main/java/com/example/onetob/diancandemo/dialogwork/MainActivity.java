package com.example.onetob.diancandemo.dialogwork;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView testTv;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testTv = findViewById(R.id.text_tv);
        res = getResources();//获取资源
    }

    public void myOnClick(View view) {
        switch (view.getId()) {
            case R.id.date_btn:
                showDateDialog();
                break;
            case R.id.singer_btn:
                showSingerDialog();
                break;
            case R.id.repeat_btn:
                showRepeatDialog();
                break;
        }
    }

    private void showDateDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String dateStr = year + "年" + month + "月" + dayOfMonth + "日";
                Toast.makeText(MainActivity.this, dateStr, Toast.LENGTH_SHORT).show();
            }
        }, Calendar.DAY_OF_YEAR, Calendar.DAY_OF_MONTH, Calendar.DATE);
        datePickerDialog.show();
    }

    private void showSingerDialog() {
        final String singerData[] = res.getStringArray(R.array.single_dialog);
        if (singerData != null) {
            AlertDialog.Builder singerDialog = new AlertDialog.Builder(this)
                    .setTitle("性别选择")
                    .setIcon(R.mipmap.star)
                    .setSingleChoiceItems(singerData, android.R.layout.select_dialog_item, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    String singerStrItem = singerData[which].toString();//获取子项菜单的值
                    Toast.makeText(MainActivity.this, "你选择了：" + singerStrItem, Toast.LENGTH_SHORT).show();
                    testTv.setText(singerStrItem);
                }
            });
            singerDialog.show();

        }
    }

    private void showRepeatDialog() {
        final String multiData[] = res.getStringArray(R.array.multi_dialog);//获取资源
        final boolean isCheckedItem[] = new boolean[multiData.length];//提供一个boolean类型的数组用于存放选中状态
        if(multiData!=null){
            AlertDialog.Builder multiDialog = new AlertDialog.Builder(this)
                    .setTitle("选择个人喜好")
                    .setIcon(R.mipmap.star)
                    .setMultiChoiceItems(multiData, isCheckedItem,  new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            isCheckedItem[which]=isChecked;//将item的状态反馈出去
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            StringBuffer stringBuffer = new StringBuffer();
                            StringBuffer stringBufferToast = new StringBuffer();
                            for (int j = 0; j <multiData.length ; j++) {
                                if (isCheckedItem[j]) {
                                    stringBufferToast.append(multiData[j]+"、");
                                    stringBuffer.append(multiData[j] + "\n");
                                }

                            }
                            String toastStrong = stringBufferToast.toString().substring(0,(stringBufferToast.toString().length()-1));
                            Toast.makeText(MainActivity.this, "你的爱好是：" + toastStrong, Toast.LENGTH_SHORT).show();
                            testTv.setText(stringBuffer.toString());
                        }
                    })
                    ;
            multiDialog.show();
        }
    }
}
