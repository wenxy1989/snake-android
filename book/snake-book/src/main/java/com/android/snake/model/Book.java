package com.android.snake.model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import java.io.Serializable;

/**
 * 书籍text
 * Created by wen on 2015/8/11.
 */
@Table(name = "snake_book")
public class Book extends SugarRecord implements Serializable {

    @Expose
    @Unique
    private Long id;
    @Expose
    @Column(name = "book_id",unique = true)
    private long bookId;
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
    @Expose
    @Column(name = "status_")
    private int status;

    public Book(){}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
