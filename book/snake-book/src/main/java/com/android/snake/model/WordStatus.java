package com.android.snake.model;

/**
 * Created by HP on 2017/4/13.
 */

public enum WordStatus {
    verified(1),
    veto(2);
    private int status;

    WordStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
