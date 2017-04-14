package com.android.snake.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.snake.ObjectValueView;
import com.android.snake.book.R;
import com.android.snake.model.Sync;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by wenxy on 2017/3/18.
 */

public class SyncListAdapter extends BaseAdapter {

    private static final String LOG_TAG = "snake book Phrase Adapter";

    private Context context;

    public SyncListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return (int) Sync.count(Sync.class);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View sync_list_item = LayoutInflater.from(context).inflate(R.layout.sync_list_item, null);
        TextView text = (TextView) sync_list_item.findViewById(R.id.text_item_sync_json);
        final ObjectValueView objectValue = (ObjectValueView) sync_list_item.findViewById(R.id.object_item_sync_id);
        Sync sync = Sync.findById(Sync.class, position + 1);
        objectValue.setValue(sync.getId());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        text.setText(gson.toJson(sync));
        Button button = (Button) sync_list_item.findViewById(R.id.button_item_sync_clear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = (long) objectValue.getValue();
                Sync clearSync = Sync.findById(Sync.class,id);
                clearSync.setTotalCount(0);
                clearSync.setSyncCount(0);
                clearSync.update();
            }
        });
        return sync_list_item;
    }
}
