package com.android.snake.book.model;

import android.os.Bundle;

import com.android.snake.model.Sync;
import com.android.snake.model.Word;

public class SyncListActivity extends ModelListActivity<Sync> {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle,Sync.class);
    }

}
