package com.android.snake.common;

import android.content.Context;

/**
 * Created by HP on 2017/3/16.
 */

public abstract class AbstractNetCallback implements NetCallback {

    private Context context;

    public AbstractNetCallback(Context context){
        this.context = context;
    }

}
