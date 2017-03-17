package com.android.snake.model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

/**
 * 书籍text
 * Created by wen on 2015/8/11.
 */
@Table(name = "snake_book")
public class Book extends SugarRecord implements Serializable {

    @Expose
    @Column(name = "name_")
    private String name;

    @Expose
    @Column(name = "author_")
    private String author;//作者

    @Expose
    @Column(name = "introduction_")
    private String introduction;//简介

    @Expose
    @Column(name = "created_time")
    private String createdTime;

    public Book(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
