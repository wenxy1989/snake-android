package com.android.snake.book;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.snake.adapter.WordListViewAdapter;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;

/**
 * Created by wenxy on 2017/3/15.
 */

public class WordCheckBottomActivity extends WordCheckActivity {

    private static final String LOG_TAG = "WordActivity";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.word_check_layout_bottom);
        initLayout(this);
        afterCheckCancel(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,WordCheckTopActivity.class));
                WordCheckBottomActivity.this.finish();
            }
        });
        afterCheckPass(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,WordCheckTopActivity.class));
                WordCheckBottomActivity.this.finish();
            }
        });
    }

}
