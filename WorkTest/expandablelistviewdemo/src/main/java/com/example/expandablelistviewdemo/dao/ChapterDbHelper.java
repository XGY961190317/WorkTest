package com.example.expandablelistviewdemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.expandablelistviewdemo.bean.Chapter;
import com.example.expandablelistviewdemo.bean.ChapterItem;

public class ChapterDbHelper extends SQLiteOpenHelper {
    private static final String DB_TABLE = "db_chapter.db";//数据库名
    private static final int VERSION = 1;//数据库版本号
    //单例模式
    private static ChapterDbHelper sInstance;

    public static synchronized ChapterDbHelper getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ChapterDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    //通过构造方法 ，继承打方式，创建数据库
    public ChapterDbHelper(Context context) {
        super(context, DB_TABLE, null, VERSION);
    }

    public ChapterDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 在创建方法中创建表，表的字段信息由对应的实体类提供，避免混乱
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + Chapter.TABLE_NAME +
                "(" + Chapter.COL_ID + " INTEGER PRIMARY KEY," +
                Chapter.COL_NAME +
                " VARCHAR NOT NULL" + ")");
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + ChapterItem.TABLE_NAME +
                "(" + ChapterItem.COL_ID + " INTEGER PRIMARY KEY," +
                ChapterItem.COL_NAME +
                " VARCHAR NOT NULL, " +
                ChapterItem.COL_PID + " INTEGER" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
