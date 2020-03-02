package com.example.sqlitedemo.higsql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.sqlitedemo.MainActivity;
import com.example.sqlitedemo.entity.UserEntity;

/**
 * 封装好user所需要的的数据库操作类
 */
public class UserDao {
    public static final String USER_INFO_TB = "user_info_tb";
    private  SQLiteDatabase database;
    private  Context mContext;

    public UserDao(Context context) {
        mContext = context;
        //=======================================创建数据库
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context, "user_info.db", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                String createTbleSql = "create table " + USER_INFO_TB + "(_id varchar primary key autoincrement not null," +
                        "name varchar(30) not null," +
                        "age integer not null," +
                        "gender varchar(2)" + ")";
                db.execSQL(createTbleSql);//调用SQLiteDatabase的execSQL创建表
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

    public  void userAddData(UserEntity user) {//添加
        String addSql = "insert into " + USER_INFO_TB + "(name,age,gender) values('" + user.getName() + "','" + user.getAge() + "','" + user.getSex() + "')";
        database.execSQL(addSql);
    }

    public  void userDeleteData(String... strings) {//删除
        String deleteSql = "delete from " + USER_INFO_TB;
        if (!(strings.length != 0)) {
            Toast.makeText(mContext, "将删除所有", Toast.LENGTH_SHORT).show();
        } else {
            deleteSql += " where " + strings[0] + "='" + strings[1] + "'";
        }
        database.execSQL(deleteSql);
    }

    public  void userUpdateData(UserEntity user) {//修改update 表名 set
        String updteSqls = "update " + USER_INFO_TB + " set name=?,age=?,gender=? where _id=?";
        // String updateSql = "update user_info_tb set name='" + userName + "' ,age=" + userAge + " ,gender='" + genders + "'where _id='" + idAvlues + "'";
        String[] values = new String[]{user.getName(), user.getAge() + "", user.getSex(),user.get_id()};
        database.execSQL(updteSqls, values);
    }

    public  Cursor userSelectData(String... strings) {//查找
        String selectSql = "select * from " + USER_INFO_TB;
        if (strings.length != 0) {
            selectSql += " where " + strings[0] +"= '" + strings[1] + "'";
        }
        Cursor c = database.rawQuery(selectSql,null);
        return c;
    }
}
