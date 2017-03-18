package com.android.snake.task;

import android.util.Log;

import com.android.snake.book.AbstractBookSyncTask;
import com.android.snake.model.Paragraph;

import org.json.JSONObject;

/**
 * Created by wenxy on 2017/3/18.
 */

public class ParagraphAsyncTask extends AbstractBookSyncTask {

    private static final String LOG_TAG = "snake book ParagraphAsyncTask";

    private Long bookId;

    public ParagraphAsyncTask(Long bookId){
        this.bookId = bookId;
    }

    @Override
    public String getModule() {
        return "paragraph";
    }

    @Override
    public String getSyncKey() {
        return "book_" + bookId + "_paragraph";
    }

    @Override
    public String listRequestJson(int start, int count) {
        return "{\"bookId\":" + bookId + ",\"start\":" + start + ",\"count\":" + count + "}";
    }

    @Override
    public void afterSyncList(JSONObject json) {
        try {
            Paragraph object = new Paragraph();
            object.setBookId(json.getLong("bookId"));
            object.setValue(json.getString("name"));
            object.setCreatedTime(json.getString("createdTime"));
            object.setStatus(0);
            object.setDeleted(0);
            long id = object.save();
            Log.d(LOG_TAG, "save paragraph id " + id);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }
}
