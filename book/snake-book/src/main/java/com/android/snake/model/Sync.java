package com.android.snake.model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HP on 2017/3/17.
 */
@Table(name = "snake_sync")
public class Sync extends SugarRecord implements Serializable {

    @Expose
    @Column(name = "key_",unique = true,notNull = true)
    private String key;

    @Expose
    @Column(name = "total_count")
    private int totalCount;

    @Expose
    @Column(name = "sync_count")
    private int syncCount;

    @Expose
    @Column(name = "sync_time")
    private String syncTime;

    public Sync(){}
    public Sync(String key){
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSyncCount() {
        return syncCount;
    }

    public void setSyncCount(int syncCount) {
        this.syncCount = syncCount;
    }

    public String getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(String syncTime) {
        this.syncTime = syncTime;
    }

    public static Sync getObjectByKey(String key){
        Sync sync = null;
        List<Sync> list = Sync.find(Sync.class, "key_=?", key);
        if (null != list && list.size() > 0) {
            sync = list.get(0);
        }
        return sync;
    }
}
