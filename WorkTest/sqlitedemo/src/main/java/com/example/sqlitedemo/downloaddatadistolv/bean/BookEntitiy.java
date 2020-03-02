package com.example.sqlitedemo.downloaddatadistolv.bean;

import java.util.List;

public class BookEntitiy {
    private int status;
    private String msg;
    private List<Book> bookList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
