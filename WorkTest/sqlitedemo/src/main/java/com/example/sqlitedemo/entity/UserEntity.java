package com.example.sqlitedemo.entity;

public class UserEntity {
    private String _id;
    private String name;
    private int age;
    private String sex;
    public UserEntity(){}
    public UserEntity(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    public UserEntity(String _id,String name, int age, String sex) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
