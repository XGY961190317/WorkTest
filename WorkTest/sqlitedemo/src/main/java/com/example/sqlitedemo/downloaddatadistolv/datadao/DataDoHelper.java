package com.example.sqlitedemo.downloaddatadistolv.datadao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sqlitedemo.MainActivity;
import com.example.sqlitedemo.downloaddatadistolv.bean.Book;

public class DataDoHelper extends SQLiteOpenHelper {
    private  static  final String DB_NAME="book_info.db";
    private  static  final int VIERSION=1;
    //单例
    private static DataDoHelper sInstence;

    public static synchronized DataDoHelper getsInstence(Context context) {
        if(sInstence==null){
            sInstence=new DataDoHelper(context.getApplicationContext());
        }
        return sInstence;
    }

    public DataDoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DataDoHelper(Context context){
        super(context, DB_NAME, null, VIERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//        //创建数据库表
//        String createTable = "create table "+ Book.TABLE_NAME+"("+Book.COL_ID+
//                " integer primary key autoincrement not null, " +
//                Book.COL_NAME + " varchar not null, " +
//                Book.COL_LEARNER+ " integer not null " +")";
//        Log.d(MainActivity.TAG,"DataDoHelper onCreate createTable");
//
        db.execSQL(" CREATE TABLE IF NOT EXISTS "+ Book.TABLE_NAME+
                "("+Book.COL_ID+" INTEGER PRIMARY KEY,"+
                Book.COL_NAME+
                " VARCHAR NOT NULL, "+
                Book.COL_LEARNER + " INTEGER"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
