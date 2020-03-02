package com.example.netgetdemo.utils;

class DataItemEntity {
    private int id;
    private int learner;
    private String name;
    private String picSmall;
    private String picBig;
    private String description;

    public DataItemEntity() {

    }

    public DataItemEntity(int id, int learner, String name, String picSmall, String picBig, String description) {
        this.id = id;
        this.learner = learner;
        this.name = name;
        this.picSmall = picSmall;
        this.picBig = picBig;
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nDataItemEntity:" +
                "\nid=" + id +
                ",\n learner=" + learner +
                ", \nname='" + name + '\'' +
                ",\n picSmall='" + picSmall + '\'' +
                ",\n picBig='" + picBig + '\'' +
                ", \ndescription='" + description + '\'';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLearner() {
        return learner;
    }

    public void setLearner(int learner) {
        this.learner = learner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getPicBig() {
        return picBig;
    }

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
