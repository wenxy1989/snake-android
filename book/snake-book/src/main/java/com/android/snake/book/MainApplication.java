package com.android.snake.book;

import android.app.Application;

import com.mazouri.tools.Tools;

public class MainApplication extends Application {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Tools.init(this);
        Tools.openToolsLog(true);
    }
}
