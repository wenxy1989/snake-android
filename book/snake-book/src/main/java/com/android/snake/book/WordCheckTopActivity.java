package com.android.snake.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by wenxy on 2017/3/15.
 */

public class WordCheckTopActivity extends WordCheckActivity {

    private static final String LOG_TAG = "WordActivity";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.word_check_layout_top);
        initContext(this);
        afterCheckCancel(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,WordCheckBottomActivity.class));
                WordCheckTopActivity.this.finish();
            }
        });
        afterCheckPass(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,WordCheckBottomActivity.class));
                WordCheckTopActivity.this.finish();
            }
        });
    }

}
