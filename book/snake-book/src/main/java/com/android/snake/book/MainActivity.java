package com.android.snake.book;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.snake.model.Paragraph;
import com.android.snake.model.Sync;
import com.android.snake.model.Book;
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
        this.setContentView(R.layout.main);
//        this.startActivity(new Intent(this,BookActivity.class));
        Button book_sync_button = (Button) findViewById(R.id.button_book_sync);
        book_sync_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncBookData();
            }
        });
        Button paragraph_sync_button = (Button) findViewById(R.id.button_paragraph_sync);
        paragraph_sync_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncParagraphData();
            }
        });

    }

    public void asyncBookData() {
        try {
            AsyncTask asyncTask = new AbstractBookSyncTask() {
                @Override
                public String getModule() {
                    return "book";
                }

                @Override
                public void afterSyncList(JSONObject json) {
                    try {
                        Book book = new Book();
                        book.setName(json.getString("name"));
                        book.setAuthor(json.getString("author"));
                        book.setIntroduction(json.getString("introduction"));
                        book.setCreatedTime(json.getString("createdTime"));
                        long id = book.save();
                        Log.i(LOG_TAG, "save book id " + id);
                    }catch (Exception e){
                        Log.e(LOG_TAG,e.getMessage());
                    }
                }
            };
            asyncTask.execute();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    public void asyncParagraphData() {
        try {
            AsyncTask asyncTask= new AbstractBookSyncTask() {
                @Override
                public String getModule() {
                    return "paragraph";
                }

                @Override
                public void afterSyncList(JSONObject json) {
                    try {
                        Paragraph book = new Paragraph();
                        book.setValue(json.getString("name"));
                        book.setCreatedTime(json.getString("createdTime"));
                        long id = book.save();
                        Log.i(LOG_TAG, "save paragraph id " + id);
                    }catch (Exception e){
                        Log.e(LOG_TAG,e.getMessage());
                    }
                }
            };
            asyncTask.execute();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }
}
