package com.example.onetob.diancandemo.ctivitylifecycle;

import java.io.Serializable;
//需要序列化：
//1,想把内存中的对象保存到一个文件或数据库中时
//2，利用套接字Socker在网络中传递对象
public class Student implements Serializable{
    private String name;
    private int age;
    private String sex;

    public Student(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
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
