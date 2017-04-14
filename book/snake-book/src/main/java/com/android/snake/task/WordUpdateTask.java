package com.android.snake.task;

import android.os.AsyncTask;
import android.util.Log;

import com.android.snake.model.Sync;
import com.android.snake.model.SyncKey;
import com.android.snake.model.Word;
import com.android.snake.model.WordStatus;
import com.android.snake.utils.DateTimeUtils;
import com.android.snake.utils.HttpInstance;

import java.util.List;

/**
 * Created by HP on 2017/3/17.
 */

public class WordUpdateTask extends AsyncTask<Object, Object, Object> {

    private static final String LOG_TAG = "WordUpdateTask";

    public void syncVerified() {
        String verified = WordStatus.verified.getStatus() + "";
        int verifiedCount = (int) Word.count(Word.class, "status_=?", new String[]{verified});
        Sync sync = Sync.getObjectByKey(SyncKey.word_checked_verified_update_key.getKey());
        if (null == sync) {
            sync = new Sync();
            sync.setKey(SyncKey.word_checked_verified_update_key.getKey());
            sync.setTotalCount(verifiedCount);
            sync.setSyncCount(0);
            sync.setSyncTime(DateTimeUtils.getInstance().getNowDateTime());
            sync.save();
        } else {
            sync.setTotalCount(verifiedCount);
            sync.update();
        }
        try {
            int count = 100;
            for (int i = sync.getSyncCount(); i < sync.getTotalCount(); i += count) {
                List<Word> verifiedList = Word.find(Word.class, "status_=? limit ?,?", verified, i + "", count + "");
                StringBuffer sb = new StringBuffer("[");
                for (int j = 0; j < verifiedList.size(); j++) {
                    Word word = verifiedList.get(j);
                    sb.append(word.getWordId());
                    if (j < verifiedList.size() - 1) {
                        sb.append(",");
                    }
                }
                sb.append("]");
                String result = HttpInstance.getInstant().doPost("book/api/word/verified", sb.toString());
                if ("success".equals(result)) {
                    int syncCount = i + count;
                    syncCount = syncCount > sync.getTotalCount() ? sync.getTotalCount() : syncCount;
                    sync.setSyncCount(syncCount);
                    sync.setSyncTime(DateTimeUtils.getInstance().getNowDateTime());
                    sync.save();
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    public void syncVeto() {
        String veto = WordStatus.veto.getStatus() + "";
        int verifiedCount = (int) Word.count(Word.class, "status_=?", new String[]{veto});
        Sync sync = Sync.getObjectByKey(SyncKey.word_checked_veto_update_key.getKey());
        if (null == sync) {
            sync = new Sync();
            sync.setKey(SyncKey.word_checked_veto_update_key.getKey());
            sync.setTotalCount(verifiedCount);
            sync.setSyncCount(0);
            sync.setSyncTime(DateTimeUtils.getInstance().getNowDateTime());
            sync.save();
        } else {
            sync.setTotalCount(verifiedCount);
            sync.save();
        }
        try {
            int count = 100;
            for (int i = sync.getSyncCount(); i < sync.getTotalCount(); i += count) {
                List<Word> verifiedList = Word.find(Word.class, "status_=? limit ?,?", veto, i + "", count + "");
                StringBuffer sb = new StringBuffer("[");
                for (int j = 0; j < verifiedList.size(); j++) {
                    Word word = verifiedList.get(j);
                    sb.append(word.getWordId());
                    if (j < verifiedList.size() - 1) {
                        sb.append(",");
                    }
                }
                sb.append("]");
                String result = HttpInstance.getInstant().doPost("book/api/word/veto", sb.toString());
                if ("success".equals(result)) {
                    int syncCount = i + count;
                    syncCount = syncCount > sync.getTotalCount() ? sync.getTotalCount() : syncCount;
                    sync.setSyncCount(syncCount);
                    sync.setSyncTime(DateTimeUtils.getInstance().getNowDateTime());
                    sync.save();
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    @Override
    protected String doInBackground(Object[] params) {
        try {
            syncVerified();
//            syncVeto();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return null;
    }

}
