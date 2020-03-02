package com.example.sqlitedemo.downloaddatadistolv.utils;

import com.example.sqlitedemo.downloaddatadistolv.bean.Book;
import com.example.sqlitedemo.downloaddatadistolv.bean.BookEntitiy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataUtile {
    /**
     * 解析json数据
     *
     * @param jsonData 需要解析的 从网络获取的json数据
     */
    public static  List<Book> getParsingData(String jsonData) {
        List<Book> bookList = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            BookEntitiy bookEntitiy = new BookEntitiy();
            bookEntitiy.setMsg(jsonObject.getString("msg"));
            bookEntitiy.setStatus(jsonObject.getInt("status"));
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            bookList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = jsonArray.getJSONObject(i);
                Book book = new Book();
                book.setId(data.getInt("id"));
                book.setName(data.getString("name"));
                book.setDescription(data.getString("description"));
                book.setPicSmall(data.getString("picSmall"));
                book.setPicBig(data.getString("picBig"));
                book.setLearner(data.getInt("learner"));
                bookList.add(book);
            }
            bookEntitiy.setBookList(bookList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
