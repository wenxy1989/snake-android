package com.android.snake.book;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.snake.model.Paragraph;
import com.android.snake.model.Sync;
import com.android.snake.model.Book;
import com.android.snake.task.BookAsyncTask;
import com.android.snake.task.PhraseAsyncTask;
import com.android.snake.task.WordAsyncTask;
import com.android.snake.utils.DateTimeUtils;
import com.android.snake.utils.HttpInstance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by wenxy on 2017/3/12.
 */

public class MainActivity extends Activity {

    private static final String LOG_TAG = "Snake Book MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.startActivity(new Intent(this, WordCheckTopActivity.class));
        this.setContentView(R.layout.main);

        Button book_sync_button = (Button) findViewById(R.id.button_book_sync);
        book_sync_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncBookData();
            }
        });

        Button book_home_button = (Button) findViewById(R.id.book_home_button);
        book_home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BookActivity.class));
            }
        });

        Button button_word_sync = (Button) findViewById(R.id.button_word_sync);
        button_word_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncWordData();
            }
        });

        Button button_word_list = (Button) findViewById(R.id.button_word_list);
        button_word_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WordListActivity.class));
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
