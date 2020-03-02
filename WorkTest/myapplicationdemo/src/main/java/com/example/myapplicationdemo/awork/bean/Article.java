package com.example.myapplicationdemo.awork.bean;

import android.content.SharedPreferences;

import java.io.Serializable;

public class Article implements Serializable {
    public static final String TABLE_NAME = "article_info";
    public static final String CLD_ID = "_id";
    public static final String CLD_IMG = "img";
    public static final String CLD_TITLE = "title";
    public static final String CLD_AUTHOR = "author";
    public static final String CLD_CONTENT = "content";
    private String title;
    private String author;
    private String content;
    private String img;
    private boolean keep = false;

    public boolean isKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
