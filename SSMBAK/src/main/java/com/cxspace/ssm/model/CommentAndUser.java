package com.cxspace.ssm.model;

import java.io.Serializable;

/**
 * Created by liujie on 2017/8/20.
 */
public class CommentAndUser implements Serializable {

    private String comment_id;

    private String user_name;

    private String comment_create_time;

    private String comment_content;

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment_create_time() {
        return comment_create_time;
    }

    public void setComment_create_time(String comment_create_time) {
        this.comment_create_time = comment_create_time;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public CommentAndUser(String comment_id, String user_name, String comment_create_time, String comment_content) {
        this.comment_id = comment_id;
        this.user_name = user_name;
        this.comment_create_time = comment_create_time;
        this.comment_content = comment_content;
    }

    public CommentAndUser() {
    }

    @Override
    public String toString() {
        return "CommentAndUser{" +
                "comment_id='" + comment_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", comment_create_time='" + comment_create_time + '\'' +
                ", comment_content='" + comment_content + '\'' +
                '}';
    }
}
