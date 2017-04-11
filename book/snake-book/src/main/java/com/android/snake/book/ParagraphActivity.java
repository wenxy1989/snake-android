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
import com.android.snake.adapter.ParagraphListViewAdapter;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;

/**
 * Created by wenxy on 2017/3/15.
 */

public class ParagraphActivity extends Activity {

    private static final String LOG_TAG = "Snake Book ParagraphActivity";

    private Context context = null;
    private ListView paragraph_home_list = null;
    private ParagraphListViewAdapter paragraph_list_adapter = null;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        context = this;
        Log.i("paragraph activity", "load activity");
        this.setContentView(R.layout.list_view);
        paragraph_home_list = (ListView) findViewById(R.id.list_view);
        paragraph_list_adapter = new ParagraphListViewAdapter(this,BookHomeListViewAdapter.selected_book_id);
        paragraph_list_adapter.setMode(Attributes.Mode.Single);
        paragraph_home_list.setAdapter(paragraph_list_adapter);
    }

}
