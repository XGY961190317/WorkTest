package com.ontob.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.ontob.greendaodemo.goodsmodule.DaoMaster;
import com.ontob.greendaodemo.goodsmodule.DaoSession;

public class MyApplication extends Application {

    public static DaoSession mSession;

    @Override
    public void onCreate() {
        super.onCreate();
        //创建数据库，连接数据库，并创建会话
        initDb();
    }

    /**
     * 连接数据库，并创建会话
     */
    private void initDb() {
        //1,获取需要连接的数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(),"goods_info.db");
        //2,创建数据库连接
        SQLiteDatabase db  = devOpenHelper.getReadableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        //3，创建数据库会话
        mSession = daoMaster.newSession();
    }
}
