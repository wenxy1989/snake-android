package com.android.snake.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.snake.model.Sync;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orm.SugarRecord;

import java.util.List;

public class ModelAdapter<T extends SugarRecord> extends BaseAdapter {

    private Context context;
    private Class<T> clazz;

    public ModelAdapter(Context context, Class<T> clazz) {
        this.context = context;
        this.clazz = clazz;
    }

    ModelAdapter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public int getCount() {
        int count = (int) Sync.count(clazz);
        return count < 20 ? count : 20;
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
        TextView view = new TextView(context);
        List<T> list = SugarRecord.find(clazz, "1=1 limit ?,1", position + "");
        if (null != list && list.size() > 0) {
            T object = list.get(0);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            view.setText(gson.toJson(object));
        }
        return view;
    }
}