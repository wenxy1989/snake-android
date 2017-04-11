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
        this.setContentView(R.layout.list_view);
        phrase_home_list = (ListView) findViewById(R.id.list_view);
        phrase_list_adapter = new PhraseListViewAdapter(this,BookHomeListViewAdapter.selected_book_id);
        phrase_home_list.setAdapter(phrase_list_adapter);
        phrase_list_adapter.setMode(Attributes.Mode.Single);

    }

}
