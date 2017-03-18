package com.android.snake.book;

import android.os.AsyncTask;
import android.util.Log;

import com.android.snake.model.Book;
import com.android.snake.model.Sync;
import com.android.snake.utils.DateTimeUtils;
import com.android.snake.utils.HttpInstance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by HP on 2017/3/17.
 */

public abstract class AbstractBookSyncTask extends AsyncTask<Object, Object, Object> {


    public abstract String getModule();
    public abstract String getSyncKey();

    private static final String LOG_TAG = "snake book sync task";

    public abstract void afterSyncList(JSONObject json);

    public Sync getUpdateSync() {
        Sync sync = null;
        try {
            String jsonString = HttpInstance.getInstant().doGet("book/api/" + getModule() + "/stat");
            JSONObject json = new JSONObject(jsonString);
            int total = json.getInt("count");
            List<Sync> list = Sync.find(Sync.class, "key_=?", getSyncKey());
            if (null != list && list.size() > 0) {
                sync = list.get(0);
                if (sync.getTotalCount() == total) {
                    sync.setTotalCount(total);
                }
            } else {
                sync = new Sync();
                sync.setKey(getSyncKey());
                sync.setTotalCount(total);
                sync.setSyncCount(0);
                sync.setSyncTime(DateTimeUtils.getInstance().getNowDateTime());
            }
            sync.save();
            Log.i(LOG_TAG, getModule() + " total : " + total);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return sync;
    }

    public String listRequestJson(int start,int count){
        return "{\"start\":" + start + ",\"count\":" + count + "}";
    }

    public int syncList(Sync sync,int start) {
        int syncCount = 0;
        try {
            int totalCount = sync.getTotalCount();
            int count = 100;
            String jsonString = HttpInstance.getInstant().doPost("book/api/" + getModule() + "/list", listRequestJson(start,count));
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++) {
                JSONObject json = array.getJSONObject(i);
                afterSyncList(json);
            }
            syncCount = start + count;
            syncCount = syncCount > totalCount ? totalCount : syncCount;
            sync.setSyncCount(syncCount);
            sync.setSyncTime(DateTimeUtils.getInstance().getNowDateTime());
            sync.save();
            Log.i(LOG_TAG, getModule() + " sync count " + syncCount);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return syncCount;
    }

    @Override
    protected String doInBackground(Object[] params) {
        try {
            int syncSize = 100;
            Sync sync = getUpdateSync();
            int totalCount = sync.getTotalCount();
            int syncStart = sync.getSyncCount();
            for (int i = syncStart; i < totalCount; i += syncSize) {
                int start = i;
                syncList(sync,start);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return null;
    }

}
