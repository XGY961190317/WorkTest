package com.example.sqlitedemo.downloaddatadistolv.net;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.sqlitedemo.MainActivity;
import com.example.sqlitedemo.downloaddatadistolv.bean.Book;
import com.example.sqlitedemo.downloaddatadistolv.bean.BookEntitiy;
import com.example.sqlitedemo.downloaddatadistolv.datadao.DataDao;
import com.example.sqlitedemo.downloaddatadistolv.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookBiz {
    private Exception ex;
    DataDao dataDao = new DataDao();
    public void loadDatas(final Context context, final CallBack callback, final boolean isCache) {
        AsyncTask<Boolean, Void, List<Book>> asyncTask = new AsyncTask<Boolean, Void, List<Book>>() {
            private Exception ex;

            @Override
            protected List<Book> doInBackground(Boolean... booleans) {
                try{
                List<Book> bookList = new ArrayList<>();
                Log.e(MainActivity.TAG, "loadFailed ex=doInBackground ");
                if (isCache) {
                    //todo  从数据库获取数据
                    List<Book> bookDbList = dataDao.loadfromDb(context);
                    Log.d(MainActivity.TAG,bookDbList.size()+"   list="+bookDbList);
                    bookList.addAll(bookDbList);
                }
                if (bookList.isEmpty()) {
                    //todo 从网络获取数据
                    List<Book> bookNetList = loadfromNet(context);
                    //todo 储存到数据库
                    dataDao.insert2Db(context, bookNetList);
                    bookList.addAll(bookNetList);
                }
                return bookList;}catch (Exception ex){
                    ex.printStackTrace();
                    this.ex=ex;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<Book> bookList) {
                super.onPostExecute(bookList);
                if (ex != null) {
                    callback.onFaill(ex);
                }
                callback.onSuccess(bookList);
            }
        };
        asyncTask.execute(isCache);
    }

    private List<Book> loadfromNet(Context context) {
        List<Book> bookList = null;
        String urlStr = "http://www.imooc.com./api/teacher?type=2&page=1";
        String content = HttpUtils.doGet(urlStr);
        Log.d(MainActivity.TAG,"content="+content);
        if (content != null) {
            bookList = parseContent(content);//解析数据
            if(bookList!=null){
                Log.d(MainActivity.TAG,"bookEntitiyList  =   "+bookList.toString());
            }

        }

        return bookList;
    }

    private List<Book> parseContent(String content) {
        List<Book> books = null;
        try {
            JSONObject jsonObject = new JSONObject(content);
            BookEntitiy bookEntitiy = new BookEntitiy();
            bookEntitiy.setStatus(jsonObject.optInt("status"));
            bookEntitiy.setMsg(jsonObject.optString("msg"));
            books = new ArrayList<>();
//            if ("成功".equals(bookEntitiy.getMsg())) {
                JSONArray jsonArray = jsonObject.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject bookObj = jsonArray.optJSONObject(i);
                    Book book = new Book();
                    book.setId(bookObj.optInt("id"));
                    book.setName(bookObj.optString("name"));
                    book.setPicBig(bookObj.optString("picBig"));
                    book.setPicSmall(bookObj.optString("picSmall"));
                    book.setDescription(bookObj.optString("description"));
                    book.setLearner(bookObj.optInt("learner"));
                    books.add(book);
                    Log.d(MainActivity.TAG,book.toString());
                }
                bookEntitiy.setBookList(books);
//            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return books;

    }

    ;

    public interface CallBack {
        void onSuccess(List<Book> bookList);

        void onFaill(Exception ex);
    }
}
