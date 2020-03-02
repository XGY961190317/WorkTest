package com.example.myapplicationdemo.awork.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.myapplicationdemo.awork.bean.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作类，执行增 删 改 差操作
 */
public class Dao {
    /**
     * 查询整张表
     *
     * @param context
     * @return
     */
    public List<Article> loadFromDb(Context context) {
        List<Article> articleList = new ArrayList<>();
        //在查询之前，通过Helper类获取数据库操作对象SQLiteDatabase
        DaoHelper helper = DaoHelper.getsInstance(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query(Article.TABLE_NAME, null, null, null, null, null, null);
        while (c.moveToNext()) {//循环数据库数据，做对应的处理
            Article article = new Article();
            article.setAuthor(c.getString(c.getColumnIndex(Article.CLD_AUTHOR)));
            article.setImg(c.getString(c.getColumnIndex(Article.CLD_IMG)));
            article.setTitle(c.getString(c.getColumnIndex(Article.CLD_TITLE)));
            article.setContent(c.getString(c.getColumnIndex(Article.CLD_CONTENT)));
            articleList.add(article);
        }
        return articleList;
    }

    /**
     * 通过id查询，按条件查询
     *
     * @param context  上下文
     * @param selectId 查询的条件 id
     * @return
     */
    public Article loadFromDb(Context context, int selectId) {
        DaoHelper helper = DaoHelper.getsInstance(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String selection = Article.CLD_ID + "=?";//查询条件
        String[] selectionValues = new String[]{selectId + ""};//查询条件的值
        Cursor c = db.query(Article.TABLE_NAME, null, selection, selectionValues, null, null, null);
        Article article = new Article();
        while (c.moveToNext()) {
            article.setAuthor(c.getString(c.getColumnIndex(Article.CLD_AUTHOR)));
            article.setImg(c.getString(c.getColumnIndex(Article.CLD_IMG)));
            article.setTitle(c.getString(c.getColumnIndex(Article.CLD_TITLE)));
            article.setContent(c.getString(c.getColumnIndex(Article.CLD_CONTENT)));
        }
        return article;
    }

    /**
     * 插入数据，添加数据
     *
     * @param context
     * @param articleList
     */
    public void insert2Db(Context context, List<Article> articleList) {
        DaoHelper helper = DaoHelper.getsInstance(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        db.beginTransaction();
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article != null) {//对传进来的数进行判空
                ContentValues values = new ContentValues();
                values.put(Article.CLD_AUTHOR, article.getAuthor());
                values.put(Article.CLD_IMG, article.getImg());
                values.put(Article.CLD_TITLE, article.getTitle());
                values.put(Article.CLD_CONTENT, article.getContent());
                db.insertWithOnConflict(Article.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
}
