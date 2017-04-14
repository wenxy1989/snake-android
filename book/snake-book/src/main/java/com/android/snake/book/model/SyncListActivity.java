package com.android.snake.book.model;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.snake.adapter.ModelAdapter;
import com.android.snake.adapter.SyncListAdapter;
import com.android.snake.book.R;
import com.android.snake.model.Sync;
import com.android.snake.model.Word;

public class SyncListActivity extends Activity {

    private ListView list_view = null;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.list_view);
        list_view = (ListView) findViewById(R.id.list_view);
        list_view.setAdapter(new SyncListAdapter(this));
    }

}
