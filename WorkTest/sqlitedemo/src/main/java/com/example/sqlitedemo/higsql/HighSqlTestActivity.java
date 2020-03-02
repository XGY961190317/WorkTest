package com.example.sqlitedemo.higsql;

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

import com.example.sqlitedemo.BaseSqlTestActivity;
import com.example.sqlitedemo.MainActivity;
import com.example.sqlitedemo.R;
import com.example.sqlitedemo.adapter.DisLvAdapter;
import com.example.sqlitedemo.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HighSqlTestActivity extends AppCompatActivity {

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
    private UserDao userDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basesqltest);
        initView();
        userDao = new UserDao(HighSqlTestActivity.this);
    }

    public void clear() {
        nameEt.setText("");
        ageEt.setText("");
        idEt.setText("");
        manRb.setChecked(true);
    }

    private void initView() {
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
        String[] values = getValues(userName, userAge, idAvlues);
        switch (v.getId()) {
            case R.id.insert_btn://添加
                UserEntity user = new UserEntity(userName, Integer.parseInt(userAge), genders);
                userDao.userAddData(user);
                Toast.makeText(HighSqlTestActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.select_btn://查找
                llInfo.setVisibility(View.VISIBLE);
                Cursor c;
                if (values[0] == null) {//判断key是否为空，空的话就执行查询全部，否则，按条件查询
                    c = userDao.userSelectData();
                } else {
                    c = userDao.userSelectData(values[0], values[1]);
                }
                SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(HighSqlTestActivity.this, R.layout.lv_dis_item, c, new String[]{"_id", "name", "age", "gender"}, new int[]{R.id.dis_id_tv, R.id.dis_name_tv, R.id.dis_age_tv, R.id.dis_sex_tv}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                dis_lv.setAdapter(cursorAdapter);
                Toast.makeText(HighSqlTestActivity.this, "查找成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_btn://删除
                if (values[0] == null) {
                    userDao.userDeleteData();
                } else {

                    userDao.userDeleteData(values[0], values[1]);
                }

                Toast.makeText(HighSqlTestActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_btn://修改
                UserEntity updateUserData = new UserEntity(idAvlues, userName, Integer.parseInt(userAge), genders);
                userDao.userUpdateData(updateUserData);
                Toast.makeText(HighSqlTestActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                break;
        }
        clear();//操作结束后初始化控件
    }

    private String[] getValues(String muserName, String muserAge, String midAvlues) {
        String[] getValues = new String[2];
        if (!muserName.equals("")) {
            getValues[0] = "name";
            getValues[1] = muserName;
        } else if (!muserAge.equals("")) {
            getValues[0] = "age";
            getValues[1] = muserAge;
        } else if (!midAvlues.equals("")) {
            getValues[0] = "_id";
            getValues[1] = midAvlues;
        }
        return getValues;
    }


//    //=======================================添加
//    public void insert(SQLiteDatabase db) {
//
//        //方式1，直接凭借sql语句
//        //String insertSql = "insert into user_info_tb(name,age,gender) values('"+userName+"','"+userAge+"','"+genders+"')";
//        // database.execSQL(insertSql);
//        //方式2，通过两个参数的方法,条件values的值用一个数组给用
//        String insertSql = "insert into user_info_tb(name,age,gender)values(?,?,?)";
//        String[] valuess = new String[]{userName, userAge, genders};
//        //方法2：
//        database.execSQL(insertSql, valuess);
//    }
//
//    //=======================================查找
//    public void select(SQLiteDatabase db) {
//        String selectSql = "select * from user_info_tb";
//
//        if (!idAvlues.equals("")) {
//            selectSql += " where _id=" + idAvlues;
//        }
//        //方式1
//        //selectSql = "select * from user_info_tb";
//        //方式2：
//        //方式2，通过两个参数的方法,条件values的值用一个数组给用
//        //String insertSql ="select * from user_info_tb where _id=?";
//        Cursor c = database.rawQuery(selectSql, null);
//        List<UserEntity> listData = new ArrayList<>();
//        while (c.moveToNext()) {
//            UserEntity userEntity = new UserEntity();
//            userEntity.set_id(c.getString(0));
//            userEntity.setName(c.getString(1));
//            userEntity.setAge(c.getInt(2));
//            userEntity.setSex(c.getString(3));
//            listData.add(userEntity);
//        }
//        DisLvAdapter adapter = new DisLvAdapter(HighSqlTestActivity.this, listData);
//        //使用SimpleCursorAdapter数据库   最后一个参数，当数据库发生改变时，通知界面更新
//        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(HighSqlTestActivity.this, R.layout.lv_dis_item, c, new String[]{"_id", "name", "age", "gender"}, new int[]{R.id.dis_id_tv, R.id.dis_name_tv, R.id.dis_age_tv, R.id.dis_sex_tv}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//        dis_lv.setAdapter(cursorAdapter);
//    }
//
//    //=======================================删除
//    public void delete(SQLiteDatabase db) {
//        String deleteSql = "delete from user_info_tb";//删除整张表的数据
//        if (!idAvlues.equals("")) {
//            //方式1：
//            deleteSql += " where _id=" + idAvlues;
//            //方式2：
//            // deleteSql += " where _id=?";
//        }
//        //方式2：
//        // db.execSQL(deleteSql,new String[]{idAvlues});
//        db.execSQL(deleteSql);
//    }
//
//    //=======================================修改
//    public void upodate(SQLiteDatabase db) {
//        String updateSql = "update user_info_tb set name='" + userName + "' ,age=" + userAge + " ,gender='" + genders + "'where _id='" + idAvlues + "'";
//        String updateSq2 = "update user_info_tb set name=?,age=?,gender=?";
//        if (!idAvlues.equals("")) {
//            updateSq2 += "where _id=" + idAvlues;
//        }
//        //db.execSQL(updateSq2, new String[]{userName, userAge, genders});
//        db.execSQL(updateSql);
//    }
}
