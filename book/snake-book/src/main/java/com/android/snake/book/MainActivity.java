package com.android.snake.book;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.snake.book.model.ParagraphListActivity;
import com.android.snake.book.model.PhraseListActivity;
import com.android.snake.book.model.SyncListActivity;
import com.android.snake.book.model.WordListActivity;
import com.android.snake.task.BookAsyncTask;
import com.android.snake.task.WordAsyncTask;

/**
 * Created by wenxy on 2017/3/12.
 */

public class MainActivity extends Activity {

    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.startActivity(new Intent(this, WordCheckActivity.class));
        this.setContentView(R.layout.main);

        findViewById(R.id.button_book_sync).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncBookData();
            }
        });

        findViewById(R.id.book_home_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BookActivity.class));
            }
        });

        findViewById(R.id.button_word_sync).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncWordData();
            }
        });

        findViewById(R.id.button_word_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WordListActivity.class));
            }
        });

        findViewById(R.id.button_paragraph_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ParagraphListActivity.class));
            }
        });

        findViewById(R.id.button_phrase_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PhraseListActivity.class));
            }
        });

        findViewById(R.id.button_sync_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SyncListActivity.class));
            }
        });

    }

    public void asyncWordData() {
        try {
            AsyncTask asyncTask = new WordAsyncTask();
            asyncTask.execute();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    public void asyncBookData() {
        try {
            AsyncTask asyncTask = new BookAsyncTask();
            asyncTask.execute();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }
}
