package com.android.snake.book.model;

import android.os.Bundle;

import com.android.snake.model.Phrase;

public class PhraseListActivity extends ModelListActivity<Phrase> {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle,Phrase.class);
    }

}
