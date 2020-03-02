package com.example.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.sqlitedemo.adapter.DisLvAdapter;
import com.example.sqlitedemo.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DbAPITestActivity extends AppCompatActivity {

    public static final String TABLE_NAME = "user_info_tb";
    private EditText nameEt;
    private EditText ageEt;
    private RadioGroup genderRg;
    private EditText idEt;
    private SQLiteDatabase database;
    private String genders = "男";
    private ListView dis_lv;
    private String idAvlues;
    private String userName;
    private String userAge;
    private LinearLayout llInfo;
    private RadioButton manRb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basesqltest);
        initView();
        createDatabase();
    }

    public void clear() {
        nameEt.setText("");
        ageEt.setText("");
        idEt.setText("");
        manRb.setChecked(true);
    }

    private void initView() {
        //final String genders = "男";
        nameEt = findViewById(R.id.name_et);
        ageEt = findViewById(R.id.age_et);
        genderRg = findViewById(R.id.gender_rg);
        manRb = findViewById(R.id.man_rb);
        idEt = findViewById(R.id._id_et);
        genderRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.man_rb:
                        genders = "男";
                        break;
                    case R.id.gender_rb:
                        genders = "";
                        break;
                }
            }
        });
        dis_lv = findViewById(R.id.base_lv_dis);
    }

    public void bastBtnOnclick(View v) {
        idAvlues = idEt.getText().toString();
        userName = nameEt.getText().toString();
        userAge = ageEt.getText().toString();
        llInfo = findViewById(R.id.ll_info);
        Log.d(MainActivity.TAG, idAvlues);
        llInfo.setVisibility(View.INVISIBLE);
        switch (v.getId()) {
            case R.id.insert_btn://添加
                insert(database);
                Toast.makeText(DbAPITestActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.select_btn://查找
                llInfo.setVisibility(View.VISIBLE);
                select(database);
                Toast.makeText(DbAPITestActivity.this, "查找成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_btn://删除
                delete(database);
                Toast.makeText(DbAPITestActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_btn://修改
                upodate(database);
                Toast.makeText(DbAPITestActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                break;
        }
        clear();//操作结束后初始化控件
    }

    //=======================================创建数据库
    public void createDatabase() {
        /**
         * 参数1：上下文
         * 参数2：数据库名
         *        1,带路径：则在指定的路径下创建数据库
         *        2,不带路径：则在应用的私有目录下创建(data/data/包名/数据库名.db)
         */
        SQLiteOpenHelper helper = new SQLiteOpenHelper(DbAPITestActivity.this, "user_info.db", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                //数据库创建方法
                //如果数据库不存在，则会调用onCreate方法创建方法，我们将表的创建工作放在这里面完成
                //准备sql语句
                String createTbleSql = "create table user_info_tb(_id varchar primary key autoincrement not null," +
                        "name varchar(30) not null," +
                        "age integer not null," +
                        "gender varchar(2)" + ")";
                db.execSQL(createTbleSql);//调用SQLiteDatabase的execSQL创建表
                Log.d(MainActivity.TAG, "创建数据库，创建表");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                //数据库更新方法
            }
        };
        //1.数据库存在则打开数据库，获取数据库对象;
        // 2,不存在，则调用建数据库创建方法创建数据库，然后再打开,获取数据库对象;
        // 3,数据库版本升高了，则调用数据库升级方法
        database = helper.getReadableDatabase();
    }

    //=======================================添加
    public void insert(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("name", userName);
        values.put("age", userAge);
        values.put("gender", genders);
        /**
         * 参数1：数据库表名
         * 		参数2：可以为空的列，防止语法错误，如果第三个参数是null或者里面没有数据；
         * 		           sql语句就会是 insert into 表名() values() 这样在语法上是错误的
         * 		           此时通过参数2，制定一个可以为空的列，语句就变成了；insert into 表名(可控列) values(null)  在语法上是正确的，
         */
        db.insert(TABLE_NAME, null, values);

    }

    //=======================================查找
    public void select(SQLiteDatabase db) {
        Cursor c;
        if (!idAvlues.equals("")) {
            /**
             * 参数1：数据库表名
             * 	              参数2：要查询的列，例如{“name”，“age”,"sex"},null 表示查询所有
             * 	              参数3：条件，？号前面的部分{“name=？”}
             * 	              参数4：条件，？的部分  {“zhangsan”}
             * 	              参数5：分组：
             * 	              参数6：当group by对数据进行分组后，我们乐意通过having来去除不符合条件的组
             * 	              参数7：排序
             */
            String[] queryCoulmns = new String[]{};//要查询的列
            String ctente = "_id=?";//查询条件列
            String[] values = new String[]{idAvlues};//查询条件的值
            c = db.query(TABLE_NAME, null, ctente, values, null, null, null);
        }
        else {
            c = db.query(TABLE_NAME, null, null, null, null, null, null);
        }
        //使用SimpleCursorAdapter数据库   最后一个参数，当数据库发生改变时，通知界面更新
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(DbAPITestActivity.this, R.layout.lv_dis_item, c, new String[]{"_id", "name", "age", "gender"}, new int[]{R.id.dis_id_tv, R.id.dis_name_tv, R.id.dis_age_tv, R.id.dis_sex_tv}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        dis_lv.setAdapter(cursorAdapter);
    }

    //=======================================删除
    public void delete(SQLiteDatabase db) {
        if (!idAvlues.equals("")) {
            /**
             * 参数1：表名
             * 	             参数2：条件
             * 	             参数3：条件的值
             * 	             返回值：int 类型，>0则执行成功，删除成功
             */
            int flag = db.delete(TABLE_NAME, "_id=?", new String[]{idAvlues});
            if (flag > 0) {
                Toast.makeText(DbAPITestActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //=======================================修改
    public void upodate(SQLiteDatabase db) {
        if (!idAvlues.equals("")) {
            ContentValues values = new ContentValues();
            values.put("name", userName);
            values.put("age", userAge);
            values.put("gender", genders);
            /**
             * 参数1：表名
             * 	                参数2：准备修改的数据ContentValues ContentValues values = new ContentValues()
             * 						avlues.put(String key,Obj obj);
             * 	                参数3：条件
             * 		参数4：条件值new String[]{}
             * 		返回值：int类型，代表影响的行数，>0表示修改成功
             */
            int flag = db.update(TABLE_NAME, values, "_id=?", new String[]{idAvlues});
            if (flag > 0) {
                Toast.makeText(DbAPITestActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
