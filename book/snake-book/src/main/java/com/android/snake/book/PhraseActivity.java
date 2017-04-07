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

import com.android.snake.adapter.BookHomeListViewAdapter;
import com.android.snake.adapter.PhraseListViewAdapter;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;

/**
 * Created by wenxy on 2017/3/15.
 */

public class PhraseActivity extends Activity {

    private static final String LOG_TAG = "Snake Book PhraseActivity";

    private Context context = null;
    private ListView phrase_home_list = null;
    private PhraseListViewAdapter phrase_list_adapter = null;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("phrase activity", "load activity");
        this.setContentView(R.layout.phrase_list);
        phrase_home_list = (ListView) findViewById(R.id.list_phrase_home);
        phrase_list_adapter = new PhraseListViewAdapter(this,BookHomeListViewAdapter.selected_book_id);
        phrase_home_list.setAdapter(phrase_list_adapter);
        phrase_list_adapter.setMode(Attributes.Mode.Single);
        phrase_home_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ((SwipeLayout)(phrase_home_list.getChildAt(position - phrase_home_list.getFirstVisiblePosition()))).open(true);
            }
        });
        phrase_home_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("ListView", "OnTouch");
                return false;
            }
        });
        phrase_home_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "OnItemLongClickListener", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        phrase_home_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e("ListView", "onScrollStateChanged");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        phrase_home_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("ListView", "onItemSelected:" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("ListView", "onNothingSelected:");
            }
        });
    }

}
