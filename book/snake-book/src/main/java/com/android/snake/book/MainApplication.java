package com.android.snake.book;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.snake.model.Book;
import com.android.snake.model.Sync;
import com.orm.SugarContext;

public class MainApplication extends Application {

    private static final String  PACKAGE_NAME = "com.android.snake.book";
    private static final String  VERSION_KEY = "com.android.snake.book.version";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
        if(isNewApplication()){
            Sync object = new Sync("book");
            object.setSyncCount(0);
            object.setTotalCount(0);
            Sync.save(object);
        }
    }

    private boolean isNewApplication(){
        boolean result = false;
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            int lastVersion = prefs.getInt(VERSION_KEY, 0);
            PackageInfo info = getPackageManager().getPackageInfo(PACKAGE_NAME, 0);
            int currentVersion = info.versionCode;
            result = currentVersion > lastVersion;
            if (result) {
                //如果当前版本大于上次版本，该版本属于第一次启动
                //将当前版本写入preference中，则下次启动的时候，据此判断，不再为首次启动
                prefs.edit().putInt(VERSION_KEY,currentVersion).commit();
            }
        } catch (Exception e) {
            Log.e("Application start",e.getMessage());
        }
        return result;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
