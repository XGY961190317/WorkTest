package com.example.myapplicationdemo.awork.bean;

import java.util.List;

public class ArticleImage {
    private int status;
    private String msg;
    private List<String>imgList;

    @Override
    public String toString() {
        return "ArticleImage{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", imgList=" + imgList +
                '}';
    }

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

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
