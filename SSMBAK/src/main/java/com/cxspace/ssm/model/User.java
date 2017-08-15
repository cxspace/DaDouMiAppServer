package com.cxspace.ssm.model;

import java.io.Serializable;

/**
 * Created by liujie on 2017/7/26.
 */
public class User implements Serializable{

    private String id;

    private String name;

    private String phone;

    private String email;

    private String wechat;

    private String password;

    private String imgsrc;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public User(String id, String name, String phone, String email, String wechat, String password, String imgsrc) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.wechat = wechat;
        this.password = password;
        this.imgsrc = imgsrc;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", wechat='" + wechat + '\'' +
                ", password='" + password + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                '}';
    }
}
