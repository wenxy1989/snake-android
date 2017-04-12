package com.android.snake.book.model;

import android.os.Bundle;

import com.android.snake.model.Word;

public class WordListActivity extends ModelListActivity<Word> {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle,Word.class);
    }

}
