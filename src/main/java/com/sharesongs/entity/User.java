package com.sharesongs.entity;

import java.util.Date;

public class User {
    int id;
    String yourname;
    String passwd;
    String headphoto;
    int sex;
    Date creat_time;
    String phone;
    String wechat;
    String qq;

    public User() {
    }

    public User(int id, String yourname, String passwd, String headphoto, int sex, Date creat_time, String phone, String wechat, String qq) {
        this.id = id;
        this.yourname = yourname;
        this.passwd = passwd;
        this.headphoto = headphoto;
        this.sex = sex;
        this.creat_time = creat_time;
        this.phone = phone;
        this.wechat = wechat;
        this.qq = qq;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYourname() {
        return yourname;
    }

    public void setYourname(String yourname) {
        this.yourname = yourname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getHeadphoto() {
        return headphoto;
    }

    public void setHeadphoto(String headphoto) {
        this.headphoto = headphoto;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Date creat_time) {
        this.creat_time = creat_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
