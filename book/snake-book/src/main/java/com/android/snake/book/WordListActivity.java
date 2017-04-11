package com.android.snake.book;

import android.app.Activity;
import android.content.Context;
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

public class WordListActivity extends Activity {

    private static final String LOG_TAG = "WordActivity";

    private Context context = null;
    private ListView word_home_list = null;
    private WordListViewAdapter word_list_adapter = null;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.list_view);
        word_list_adapter = new WordListViewAdapter(this);
        word_list_adapter.setMode(Attributes.Mode.Single);
        word_home_list = (ListView) findViewById(R.id.list_view);
        word_home_list.setAdapter(word_list_adapter);
        word_home_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(LOG_TAG, "OnItemClickListener:");
                ((SwipeLayout)(word_home_list.getChildAt(position - word_home_list.getFirstVisiblePosition()))).open(true);
            }
        });
        word_home_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(LOG_TAG, "OnTouch");
                return false;
            }
        });
        word_home_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "OnItemLongClickListener", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        word_home_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e(LOG_TAG, "onScrollStateChanged");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.e(LOG_TAG, "onScroll:");
            }
        });

        word_home_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e(LOG_TAG, "onItemSelected:" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e(LOG_TAG, "onNothingSelected:");
            }
        });
    }

}
