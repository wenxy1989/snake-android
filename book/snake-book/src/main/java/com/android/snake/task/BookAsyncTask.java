package com.android.snake.task;

import android.util.Log;

import com.android.snake.model.Book;

import org.json.JSONObject;

/**
 * Created by wenxy on 2017/3/18.
 */

public class BookAsyncTask extends AbstractBookSyncTask {

    private static final String LOG_TAG = "snake book BookAsyncTask";

    @Override
    public String getModule() {
        return "book";
    }

    @Override
    public String getSyncKey() {
        return getModule();
    }

    @Override
    public void afterSyncList(JSONObject json) {
        try {
            Book book = new Book();
            book.setBookId(json.getLong("id"));
            book.setName(json.getString("name"));
            book.setAuthor(json.getString("author"));
            book.setIntroduction(json.getString("introduction"));
            book.setCreatedTime(json.getString("createdTime"));
            long id = book.save();
            Log.i(LOG_TAG, "save book id " + id);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }
}
