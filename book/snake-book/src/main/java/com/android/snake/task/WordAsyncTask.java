package com.android.snake.task;

import android.util.Log;

import com.android.snake.model.Word;

import org.json.JSONObject;

/**
 * Created by wenxy on 2017/3/18.
 */

public class WordAsyncTask extends AbstractBookSyncTask {

    private static final String LOG_TAG = "snake book WordAsyncTask";

    @Override
    public String getModule() {
        return "word";
    }

    @Override
    public String getSyncKey() {
        return getModule();
    }

    @Override
    public void afterSyncList(JSONObject json) {
        try {
            Word word = new Word();
            word.setWordId(json.getLong("id"));
            word.setBookId(json.getLong("bookId"));
            word.setValue(json.getString("word"));
            word.setCreatedTime(json.getString("createdTime"));
            long id = word.save();
            Log.i(LOG_TAG, "save word id " + id);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }
}
