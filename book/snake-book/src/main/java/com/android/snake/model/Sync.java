package com.android.snake.model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

/**
 * Created by HP on 2017/3/17.
 */
@Table(name = "snake_sync")
public class Sync extends SugarRecord implements Serializable {

    @Expose
    @Column(name = "module_",unique = true,notNull = true)
    private String module;

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
    public Sync(String module){
        this.module = module;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
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
}
