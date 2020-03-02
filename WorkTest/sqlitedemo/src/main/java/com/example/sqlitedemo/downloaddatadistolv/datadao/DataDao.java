package com.example.sqlitedemo.downloaddatadistolv.datadao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sqlitedemo.MainActivity;
import com.example.sqlitedemo.downloaddatadistolv.bean.Book;
import com.example.sqlitedemo.downloaddatadistolv.bean.BookEntitiy;

import java.util.ArrayList;
import java.util.List;

public class DataDao {

    public  List<Book> loadfromDb(Context context) {//查找数据
        Log.d(MainActivity.TAG,"loadfromDb");
        BookEntitiy bookEntitiy = new BookEntitiy();
        List<Book> bookDbList = new ArrayList<>();
        DataDoHelper doHelper = DataDoHelper.getsInstence(context);
        SQLiteDatabase db = doHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Book.TABLE_NAME, null);
        if(cursor==null){
            Log.d(MainActivity.TAG,"cursor==null"+cursor);
        }
        while (cursor.moveToNext()) {
            Book book = new Book();
            String name = cursor.getString(cursor.getColumnIndex("name"));
            book.setName(name);
            int learner = cursor.getInt(cursor.getColumnIndex("learner"));
            Log.d(MainActivity.TAG,"db.rawQuery(="+name+learner);
            book.setLearner(learner);
            bookDbList.add(book);
        }
        bookEntitiy.setBookList(bookDbList);

        return bookDbList;
    }

    public  void insert2Db(Context context, List<Book> bookList) {//添加
        if (context == null) {
            throw new IllegalArgumentException("context can not be null.");
        }
        if (bookList != null || bookList.isEmpty()) {
            DataDoHelper doHelper = DataDoHelper.getsInstence(context);
            SQLiteDatabase db = doHelper.getReadableDatabase();
            db.beginTransaction();
            Log.d(MainActivity.TAG, "insert2Db");
            for (int j = 0; j < bookList.size(); j++) {
                String name = bookList.get(j).getName();
                int learner = bookList.get(j).getLearner();
                Log.d(MainActivity.TAG, "name="+name+"   learner="+learner+"    doHelper:"+doHelper);
                ContentValues values = new ContentValues();
                values.put(Book.COL_NAME, name);
                values.put(Book.COL_LEARNER, learner);
               long k =  db.insertWithOnConflict(Book.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);//插入时更新重复的数据
                Log.d(MainActivity.TAG,"kk = "+k);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        }else {
            Log.d(MainActivity.TAG, "bookList =null");
        }
    }

}
