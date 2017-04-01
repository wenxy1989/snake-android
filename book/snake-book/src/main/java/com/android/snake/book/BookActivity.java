package com.android.snake.book;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.snake.adapter.BookHomeListViewAdapter;
import com.android.snake.common.AbstractNetCallback;
import com.android.snake.model.Book;
import com.android.snake.model.Paragraph;
import com.android.snake.service.BookNetService;
import com.android.snake.service.BookService;
import com.android.snake.utils.HttpInstance;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenxy on 2017/3/15.
 */

public class BookActivity extends Activity {

    private static final String LOG_TAG = "Snake Book BookActivity";

    private Context context = null;
    private ListView book_home_list = null;
    private BookHomeListViewAdapter book_list_adapter = null;

    private View.OnClickListener bookNameOnclick = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("book activity", "load activity");
        this.setContentView(R.layout.book_home);
        book_home_list = (ListView) findViewById(R.id.list_book_home);
        book_list_adapter = new BookHomeListViewAdapter(this);
        book_list_adapter.bookNameOnClick(bookNameOnclick);
        book_home_list.setAdapter(book_list_adapter);
        book_list_adapter.setMode(Attributes.Mode.Single);
        book_home_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ((SwipeLayout)(book_home_list.getChildAt(position - book_home_list.getFirstVisiblePosition()))).open(true);
            }
        });
        book_home_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("ListView", "OnTouch");
                return false;
            }
        });
        book_home_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "OnItemLongClickListener", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        book_home_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e("ListView", "onScrollStateChanged");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        book_home_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("ListView", "onItemSelected:" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("ListView", "onNothingSelected:");
            }
        });
        /*
        Button paragraph_sync_button = (Button) findViewById(R.id.button_paragraph_sync);
        paragraph_sync_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncParagraphData();
            }
        });*/
    }

}
