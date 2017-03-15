package com.android.snake.book;

import android.content.Context;

import com.android.snake.common.NetService;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by wenxy on 2017/3/15.
 */

public class BookNetService extends NetService<Book> {

    public BookNetService(Context context, String command) {
        super(context, command);
    }

    @Override
    public Book getObject(JSONObject json) throws JSONException {
        Book object = new Book();
        if(null != json){
            object.setId(json.getLong("id"));
            object.setName(json.getString("name"));
            object.setAuthor(json.getString("author"));
            object.setIntroduction(json.getString("instroduction"));
            object.setCreatedTime(json.getString("createdTime"));
        }
        return object;
    }
}
