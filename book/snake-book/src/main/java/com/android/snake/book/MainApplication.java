package com.android.snake.book;

import android.app.Application;
import android.content.Intent;

import com.orm.SugarContext;

public class MainApplication extends Application {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
