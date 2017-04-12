package com.android.snake.book.model;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.snake.adapter.ModelAdapter;
import com.android.snake.book.R;
import com.orm.SugarRecord;

/**
 * Created by HP on 2017/4/12.
 */

public class ModelListActivity<T extends SugarRecord> extends Activity {

    private ListView list_view = null;

    protected void onCreate(Bundle bundle,Class<T> clazz) {
        super.onCreate(bundle);
        this.setContentView(R.layout.list_view);
        list_view = (ListView) findViewById(R.id.list_view);
        list_view.setAdapter(new ModelAdapter<T>(this,clazz));
    }
}
