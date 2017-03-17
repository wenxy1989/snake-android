package com.android.snake.book;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.snake.common.AbstractNetCallback;
import com.android.snake.model.Book;
import com.android.snake.service.BookNetService;
import com.android.snake.service.BookService;
import com.android.snake.utils.HttpInstance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenxy on 2017/3/15.
 */

public class BookActivity extends Activity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.book_home);
        Log.i("book activity", "load activity");
//        ToastTool.instance().showToastLong(this,sb.toString());
        Button book_sync_button = (Button) findViewById(R.id.button_book_sync);
        book_sync_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}
