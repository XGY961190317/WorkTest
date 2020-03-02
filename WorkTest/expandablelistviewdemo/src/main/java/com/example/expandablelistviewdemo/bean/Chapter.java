package com.example.expandablelistviewdemo.bean;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    private int id;
    private String name;
    private List<ChapterItem> children = new ArrayList<>();
    public static final  String TABLE_NAME = "tb_chapter";//表操作信息有自己提供，避免后面混乱，方便管理
    public static final String COL_ID="_id";
    public static final String COL_NAME="name";

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }

    public Chapter(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Chapter(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChapterItem> getChildren() {
        return children;
    }

    public void setChildren(List<ChapterItem> children) {
        this.children = children;
    }
    //添加子几个的方法1
    public void addChild(ChapterItem item) {
        children.add(item);
        item.setPid(getId());//设置好Pid
    }
    //添加子几个的方法2
    public void addChild(int itemId, String itemName) {
        ChapterItem chapterItem = new ChapterItem(itemId, itemName);
        //设置pid
        chapterItem.setPid(getId());//设置子表的关联id:pid
        children.add(chapterItem);

    }
}