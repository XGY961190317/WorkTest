package com.example.netgetdemo.utils;

import java.util.List;

public class GetDataentity {
    private int status;
    private String msg;
    private List<DataItemEntity> data;
    public GetDataentity(){}
    public GetDataentity(int status, String msg, List<DataItemEntity> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        String str = data.toString();
        for (int i = 0; i <data.size() ; i++) {
            str +=  data.get(i).toString();
        }
        return "GetDataentity:" +
                "\nstatus=" + status +
                ",\n msg='" + msg + '\'' +
                ",\n data=" + str;
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

    public List<DataItemEntity> getData() {
        return data;
    }

    public void setData(List<DataItemEntity> data) {
        this.data = data;
    }
}
