package com.example.onetob.diancandemo.listviewdemo;

public class Msg {
    private int profile;//头像
    private String nickname;//用户名
    private String content;//内容
    private boolean isLike;//点赞

    public Msg(int profile, String nickname, String content, boolean isLike) {
        this.profile = profile;
        this.nickname = nickname;
        this.content = content;
        this.isLike = isLike;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
