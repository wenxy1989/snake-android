package com.android.snake.book.model;

import android.os.Bundle;
import com.android.snake.model.Paragraph;

public class ParagraphListActivity extends ModelListActivity<Paragraph> {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle,Paragraph.class);
    }

}
