package com.example.expandablelistviewdemo.bean;

public class ChapterItem {
    private int id;
    private int pid;
    private String name;
    public ChapterItem(){}
    public static final  String TABLE_NAME = "tb_chapter_item";
    public static final String COL_ID="_id";//子表操作信息有自己提供，避免后面混乱，方便管理
    public static final String COL_NAME="name";
    public static final String COL_PID="pid";

    @Override
    public String toString() {
        return "ChapterItem{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                '}';
    }

    public ChapterItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
