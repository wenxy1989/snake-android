package com.android.snake.model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

/**
 * Created by wenxy on 2017/3/18.
 */

@Table(name = "book_word")
public class Word extends SugarRecord implements Serializable{

    @Expose
    @Column(name = "word_id")
    private long wordId;
    @Expose
    @Column(name = "book_id")
    private long bookId;
    @Expose
    @Column(name = "value_")
    private String value;
    @Expose
    @Column(name = "count_")
    private int count;
    @Expose
    @Column(name = "created_time")
    private String createdTime;
    @Expose
    @Column(name = "status_")
    private int status;
    @Expose
    @Column(name = "deleted_")
    private int deleted;

    public long getWordId() {
        return wordId;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
