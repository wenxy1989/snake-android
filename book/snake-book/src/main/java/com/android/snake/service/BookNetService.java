package com.android.snake.service;

import android.util.Log;

import com.android.snake.common.NetCallback;
import com.android.snake.model.Book;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wenxy on 2017/3/15.
 */

public class BookNetService extends AbstractNetService<Book> {

    public BookNetService(NetCallback callback) {
        super(callback);
    }

    public static BookNetService getInstance(NetCallback callback) {
        return new BookNetService(callback);
    }

    @Override
    public String baseURI() {
        return "book/api/book/";
    }

    @Override
    public Book getObject(JSONObject json) {
        Book object = new Book();
        if (null != json) {
            try {
                object.setId(json.getLong("id"));
                object.setName(json.getString("name"));
                object.setAuthor(json.getString("author"));
                object.setIntroduction(json.getString("introduction"));
                object.setCreatedTime(json.getString("createdTime"));
            } catch (JSONException e) {
                Log.e("book json ",e.getMessage());
            }
        }
        return object;
    }
}
