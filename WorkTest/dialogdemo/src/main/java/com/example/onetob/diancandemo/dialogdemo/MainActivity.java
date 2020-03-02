package com.example.onetob.diancandemo.dialogdemo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onetob.diancandemo.dialogdemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                //方式1：
                //1，实例化 builder
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //2,利用buider创建一个对话框
                builder.setTitle("提示对话框");
                builder.setMessage("您确定退出程序吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//确定就关闭当前activity窗口
                    }
                });
                builder.setNegativeButton("取消", null);
                //显示对话框
                builder.show();
//                AlertDialog dialog = builder.create();
//                dialog.show();
                break;
            case R.id.btn_two:
                MyDialog myDialog = new MyDialog(this, R.style.myDialogStyle);
                myDialog.show();
                break;
            case R.id.dis_popWind:
                showPopupWindow(view);
                break;
            case R.id.array_dialog:
                showArrayDialog();
                break;
        }
    }

    private void showArrayDialog() {
        final String[] items = {"Java", "Mysql", "Android", "HTML", "C", "JavaScript"};
        //参数1:环境，this
        //参数2：布局资源的索引   ，指的是每一项数据所呈现的样式 android：r.layout.xxxx
        //参数3：数据源
//        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,items);
        //参数3：制定文本需要放在对应id文本的位置


        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.array_item_layout,R.id.item_tv,items);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请选择")
                //参数1：适配器对象，地数据显示样式的规则制定器
                //参数2：监听器
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,items[which],Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();

    }

    /**
     * 设置PopupWindow
     */
    private void showPopupWindow(View view) {
        //准备弹窗所需要的的视图对象
        View layoutView = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);//将布局转换成视图，打气筒
        //1，实例化PopupWindow对象
        //1,参数1，用在弹窗中的view,
        //2,参数2,3 弹窗的宽高
        //3，参数4  （focusable）,能否获取焦点

        final PopupWindow popupWindow = new PopupWindow(layoutView, 490, 90, true);
        //2,设置
        //设置背景，动画
        //设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置背景为透明色背景，建议用一个设置透明度方法进行精准的透明度设置
        //设置能响应外部的点击事件
        popupWindow.setOutsideTouchable(true);// 设置PopupWindow内容区域外的区域是否响应点击事件（true：响应；false：不响应）
        //设置弹窗能响应点击事件
        popupWindow.setTouchable(true);
        //设置动画
        //1.创建动画资源
        // 2.创建一个style应用刚刚的动画资源
        // 3.对当前弹窗的动画风格设置为第二步的资源索引
        popupWindow.setAnimationStyle(R.style.translate_anim);

        //3,显示PopupWindow
        //参数1，(anchor) 锚,显示在哪个位置，在那个按钮下面
        //参数2,3相对于锚在x,y方向上的偏移量
        popupWindow.showAsDropDown(view, 100, 50);

        //为弹窗中的文本设置点击事件
        TextView tvChoose = layoutView.findViewById(R.id.choose);
        TextView tvChooseAll = layoutView.findViewById(R.id.choose_all);
        TextView tvCopy = layoutView.findViewById(R.id.copy);
        tvChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "您点击了选择", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        tvChooseAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "您点击了全选", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        tvCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "您点击了复制", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
    }

    //方式2：直接在dialog设置，先创建对话框
    public void showNormalDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("提示对话框");
        dialog.setMessage("您确定退出程序吗？");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}
