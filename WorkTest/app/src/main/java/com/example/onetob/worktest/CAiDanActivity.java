package com.example.onetob.worktest;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CAiDanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dan);
        //ctx_btn  演示ContextMenu
        //1,注册，
        registerForContextMenu(findViewById(R.id.ctx_menu));
        //2，创建

        //3，菜单项的操作
        //4,为按钮设置上下文操作模式    在顶部出现   覆盖在上面
        //   1,实现ActionMode CallBack接口
        //   2，在view的长按事件中去启动上下文操作模式   startActionMode(cd);
        findViewById(R.id.ctx_menu).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActionMode(cd);
                return false;
            }
        });

        //popUp_mane 演示PopupMenu
        final Button btnPop = findViewById(R.id.popUp_menu);
        btnPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1，实例化一个PopMenu对象
                PopupMenu menu = new PopupMenu(CAiDanActivity.this, btnPop);//参数1，上下文    参数2，锚，出现在什么位置，谁的下方
                //2，加载菜单资源：利用MenuInflater    利用Menlnflater将Menu加载到PopMenu.getMenu() 所返回的Menu对象中
                menu.getMenuInflater().inflate(R.menu.tanchu_item, menu.getMenu());//将R.menu.tanchu_item对应的菜单资源加载到menu.getMenu()中
                //3，为PopupMenu设置点击监听器
                //4,显示这一个popupMenu   千万不要忘记这一步
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.copy:
                                Toast.makeText(CAiDanActivity.this, "复制", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.paste:
                                Toast.makeText(CAiDanActivity.this, "粘贴", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
                menu.show();
            }
        });
    }

    //,实现ActionMode CallBack接口
    ActionMode.Callback cd = new ActionMode.Callback() {
        //在view的长按事件中去启动上下文操作模式(调用startActionMode(Callback))
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Log.d("TAG", "创建");
            getMenuInflater().inflate(R.menu.ctx_menu, menu);
            return true;
        }

        //在创建方法后调用
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.d("TAG", "准备");
            return false;
        }

        //菜单项被点击
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.d("TAG", "执行");
            switch (item.getItemId()) {
                case R.id.delete:
                    Toast.makeText(CAiDanActivity.this, "删除", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.op1:
                    Toast.makeText(CAiDanActivity.this, "操作1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.op2:
                    Toast.makeText(CAiDanActivity.this, "操作2", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }

        //上下文操作模式结束时被调用
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.d("TAG", "结束");
        }
    };

//    //ContextMenu   //2，创建
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getMenuInflater().inflate(R.menu.ctx_menu, menu);//2，创建
//
//    }
//
//    //3，菜单项的操作
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.delete:
//                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.op1:
//                Toast.makeText(this, "操作1", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.op2:
//                Toast.makeText(this, "操作2", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onContextItemSelected(item);
//    }

    //创建OptionMenu
    //重写onCreateOptionsMenu方法
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单资源
        //通过XML资源来设计Menu
        //getMenulbflater(R.menu.option_menu)
        //方式1：直接加载资源文件中的menu
        // getMenuInflater().inflate(R.menu.option_menu, menu);//将菜单资源加载到菜单中来 return true
        //方式2：通过纯Java代码来实现
        //Menu对象
        /*
         *设置
         * 更多
         *    添加
         *    删除
         */
        menu.add(1, 1, 1, "设置");//参数1：组id    参数2：菜单项的id  参数3：序号 参数4：菜单项上面的文本Text
        SubMenu item_menu = menu.addSubMenu(1, 2, 2, "更多");//参数1：组id    参数2：菜单项的id  参数3：序号 参数4：菜单项上面的文本Text
        //SubMenu
        item_menu.add(2, 3, 1, "添加");
        item_menu.add(2, 4, 2, "删除");
        return true;
    }

    //OptionMenu菜单项的选中方法
    //重写onOptionsItemSelected方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.getItemId();//获取菜单项的id
//        switch (item.getItemId()) {
//            case R.id.save:
//                Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.setting:
//                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.exit:
//                finish();
//                break;
//
//
//
//        }
        //纯Java代码创建的菜单
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
            case 3:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            default:
                super.onOptionsItemSelected(item);

                //纯Java代码创建的菜单

        }
        return true;
    }
}
