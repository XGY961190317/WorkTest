package com.example.myapplicationdemo.awork.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplicationdemo.awork.bean.Article;

/**
 * 数据库帮助类，建库，建表
 */
public class DaoHelper extends SQLiteOpenHelper {

    public static final String ARTICLE_DB = "article.db";
    public static final int VERSION = 1;
    //通过静态单例构造DaoHelper对象，保证只有一个对象：
    private static DaoHelper sInstance;

    public static synchronized DaoHelper getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DaoHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public DaoHelper(Context context) {
        super(context, ARTICLE_DB, null, VERSION);//通过集成父类的方式建数据库，需要提供数据库地址，版本号
    }

    public DaoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 初始化表
     *
     * @param db 数据库操作对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Article.TABLE_NAME + "(" + Article.CLD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Article.CLD_AUTHOR + " VARCHAR NOT NULL, " +
                Article.CLD_IMG + " VARCHAR NOT NULL, " +
                Article.CLD_TITLE + " VARCHAR NOT NULL, " +
                Article.CLD_CONTENT + " VARCHAR NOT NULL" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
