package com.example.expandablelistviewdemo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.expandablelistviewdemo.bean.Chapter;
import com.example.expandablelistviewdemo.bean.ChapterItem;

import java.util.ArrayList;
import java.util.List;

public class ChapterDao {
    public List<Chapter> loadFromDb(Context context) {
        ChapterDbHelper helper =ChapterDbHelper.getsInstance(context);//获取数据库帮助类对象，建库，建表
        SQLiteDatabase db = helper.getReadableDatabase();//通过数据库帮助对象获取数据库操作对象
        //表的查询操作
        List<Chapter>chapterList = new ArrayList<>();
       Cursor c =  db.rawQuery("select * from "+Chapter.TABLE_NAME,null);
        Chapter chapter = null;
       while(c.moveToNext()){
           chapter = new Chapter();
           int id = c.getInt(c.getColumnIndex(Chapter.COL_ID));
           String  name = c.getString(c.getColumnIndex(Chapter.COL_NAME));
           chapter.setId(id);
           chapter.setName(name);
           chapterList.add(chapter);
       }
       c.close();
       //query chapter item  后期改写一次性读取，再比较
        ChapterItem chapterItem = null;

        for (Chapter parent :chapterList) {
            int pid = parent.getId();
            c = db.rawQuery("select * from "+ChapterItem.TABLE_NAME+" where "+ChapterItem.COL_PID+" = ? ",new String[]{pid+""});
            //可优化，不要在for循环里面写rawQuery，和上面一样，先拿到表，然后和pid做匹配
            while(c.moveToNext()){
                chapterItem = new ChapterItem();
                int id =c.getInt(c.getColumnIndex(ChapterItem.COL_ID));
                String  name = c.getString(c.getColumnIndex(ChapterItem.COL_NAME));
                chapterItem.setId(id);
                chapterItem.setName(name);
                parent.addChild(chapterItem);
            }
            c.close();
        }
        return chapterList;
    }

    /**
     * 添加数据到表
     * @param context
     * @param chapterList  需要添加的数据集合
     */
    public void insert2Db(Context context, List<Chapter> chapterList) {
        if (context == null) {
            throw new IllegalArgumentException("context can not be null.");
        }
        if (chapterList != null || chapterList.isEmpty()) {

            //存储到数据库
            ChapterDbHelper helper =ChapterDbHelper.getsInstance(context);//获取数据库帮助类对象，建库，建表
            SQLiteDatabase db = helper.getReadableDatabase();//通过数据库帮助对象获取数据库操作对象
            db.beginTransaction();//开始
            ContentValues contentValues = null;
            for (Chapter chapter : chapterList) {
                contentValues = new ContentValues();
                contentValues.put(Chapter.COL_ID, chapter.getId());
                contentValues.put(Chapter.COL_NAME, chapter.getName());
                db.insertWithOnConflict(Chapter.TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);//插入时更新重复的数据
                List<ChapterItem> children = chapter.getChildren();
                for (ChapterItem chapteritem : children) {
                    contentValues = new ContentValues();
                    contentValues.put(ChapterItem.COL_ID, chapteritem.getId());
                    contentValues.put(ChapterItem.COL_NAME, chapteritem.getName());
                    contentValues.put(ChapterItem.COL_PID, chapter.getId());
                    Log.d("fffffffffffffffffffffff",chapteritem.getId()+chapteritem.getName()+chapter.getId());
                    db.insertWithOnConflict(ChapterItem.TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);//插入时更新重复的数据
                }
            }

            db.setTransactionSuccessful();//设置操作，提交
            db.endTransaction();//结束
        }
    }
}
