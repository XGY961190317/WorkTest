package com.example.onetob.diancandemo.ctivitylifecycle;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity_X";
    final private int REQUESTCODE = 1000;
    final private int PASSREQUESTCODE = 1001;

    //**单个activity的生命周期：
    //1，正常启动 onCreate()----onStart()----onResume()   正常退出  onPause()----onStop()----onDestroy()
    //2,已经处于前台的activity,点击Home按钮离开当前activity,onPause()----onStop()   然后再回到zctivity,onRestart()----onStart()----onResume()
    //3,activity不可操作（如：息屏，打开了其他activity，而应用被强行杀死了kill）：由于内存空间不够，长时间没使用的程序，被系统销毁（kill）回收内存:onPause()----onStop()
    // 再回到activity：onCreate()----onStart()----onResume()
    //**多个activity切换时
    //当启动另一个activity时：当前activity：onPause()----onStop()
    //当点击返回按钮时，使第二个activity退出时，当前activity：onRestart()----onStart()----onResume()
    //**对话框存在时：
    //1，普通对话框对生命周期没有任何影响
    //2，如果有一个activity伪装成对话框时，那么它启动的时候，之前的activity：onPause()
    //当这个伪装的对话框（activity）消失时，回调onResume()方法，再次回到前台
    //创建方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate(创建)方法");
    }

    //启动方法
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart（启动）方法");
    }

    //恢复方法
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume（恢复）方法");
    }

    //暂停方法（activity部分不可见）
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause（暂停）方法");
    }

    //停止方法（activity完全不可见）
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop（停止）方法");
    }

    //重启方法activity被onstop要重新恢复到运行状态是调用
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart（重启）方法");
    }

    //销毁方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy（销毁）方法");
    }

    public void myOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Intent intent = new Intent(MainActivity.this, MyActivity1.class);
                startActivity(intent);
                break;
            case R.id.btn2://打开一个对话框
                //普通对话框不会影响生命周期
                showMyDialog();
                break;
            case R.id.btn3://界面对话框
                Intent intent1 = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent1);
                break;
            case R.id.start_system_activity_btn://隐式启动系统activity
                //action 是activity的别名
                //参数1，某activity的别名
                //参数2，Url对象，打开的路径  通过协议来具体确定打开什么activity
                Intent startSystemActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
                startActivity(startSystemActivity);
                break;
            case R.id.start_activity_btn://启动普通activity
                //action 普通activity的别名
                Intent startActivity = new Intent("com.intent.MyAction");
                startActivity(startActivity);
                break;
            case R.id.start_return_btn:
                Intent returnData = new Intent(MainActivity.this, CanRetrunDataActivity.class);
                startActivityForResult(returnData, REQUESTCODE);
                break;
            case R.id.pass_data:
                Intent passData = new Intent(MainActivity.this, PassDataActivity.class);
                passData.putExtra("msg1", "这是数据1");
                passData.putExtra("msg2", 100);
                startActivity(passData);
                break;
            case R.id.pass_obj_data://利用intent传递对象   需要序列化  implements Serializable
                //需要序列化：
                //1,想把内存中的对象保存到一个文件或数据库中时
                //2，利用套接字Socker在网络中传递对象
                Intent passObjData = new Intent(MainActivity.this, PassOBJDataActivity.class);
                Student stu = new Student("战三", 12, "男");
                passObjData.putExtra("stu",stu);
                startActivity(passObjData);
                break;
        }
    }

    //如果通过startActivityForResult()方式启动了第二个activity
    //当第二个activity处理结束后，再回到当前的activity时，一定会回调onActivityResult()方法
    //在该方法中可以处理第二个activity返回的结果，例如拍照后得到的照片，从图库中选取的图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //resultCode 0,点返回按钮时是：RESULT_CAVCEL取消   -1，RESULT_OK 正确处理完后返回
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUESTCODE:
                    //判断结果吗是否和请求吗一致，区分返回的结果来自哪个activity
                    Log.d(TAG, "返回的数据是：" + data.getStringExtra("userName"));
                    Log.d(TAG, "自动进入了onActivityResult()方法,requestCode=" + requestCode + "  resultCode=" + resultCode);
                    break;
                case PASSREQUESTCODE:
                    Log.d(TAG, "返回的数据是：" + data.getStringExtra("myMsg"));
                    break;
            }


        }

    }

    private void showMyDialog() {
        //普通对话框不会影响生命周期
        final AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("提示对话框").setMessage("是否要对当前activity进行操作？").setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
