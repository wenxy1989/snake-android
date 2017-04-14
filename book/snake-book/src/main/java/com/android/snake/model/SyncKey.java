package com.android.snake.model;

/**
 * Created by HP on 2017/4/13.
 */

public enum SyncKey {

    word_checked_key("word_checked_key"),
    word_checked_verified_update_key("word_checked_verified_update_key"),
    word_checked_veto_update_key("word_checked_veto_update_key")
    ;

    private String key;

    public String getKey() {
        return key;
    }

    SyncKey(String key){
        this.key = key;
    }

}
