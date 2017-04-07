package com.android.snake.task;

import android.util.Log;

import com.android.snake.book.AbstractBookSyncTask;
import com.android.snake.model.Phrase;

import org.json.JSONObject;

/**
 * Created by wenxy on 2017/3/18.
 */

public class PhraseAsyncTask extends AbstractBookSyncTask {

    private static final String LOG_TAG = "snake book PhraseAsyncTask";

    private Long bookId;

    public PhraseAsyncTask(Long bookId){
        this.bookId = bookId;
    }

    @Override
    public String getModule() {
        return "phrase";
    }

    @Override
    public String getSyncKey() {
        return "book_" + bookId + "_phrase";
    }

    @Override
    public String listRequestJson(int start, int count) {
        return "{\"bookId\":" + bookId + ",\"start\":" + start + ",\"count\":" + count + "}";
    }

    @Override
    public void afterSyncList(JSONObject json) {
        try {
            Phrase object = new Phrase();
            object.setBookId(json.getLong("bookId"));
            object.setPhraseId(json.getLong("id"));
            object.setValue(json.getString("phrase"));
            object.setCreatedTime(json.getString("createdTime"));
            object.setStatus(0);
            object.setDeleted(0);
            long id = object.save();
            Log.d(LOG_TAG, "save phrase id " + id);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }
}
