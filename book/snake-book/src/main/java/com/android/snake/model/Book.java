package com.android.snake.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * 书籍text
 * Created by wen on 2015/8/11.
 */
@Table(name = "snake_book")
public class Book extends SugarRecord{

    private Long id;
    private String name;
    private String author;//作者
    private String introduction;//简介
    private String createdTime;

    public Book(){}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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
