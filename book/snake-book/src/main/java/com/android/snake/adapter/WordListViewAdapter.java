package com.android.snake.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.snake.ObjectValueView;
import com.android.snake.book.R;
import com.android.snake.model.Book;
import com.android.snake.model.Paragraph;
import com.android.snake.model.Word;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by wenxy on 2017/3/18.
 */

public class WordListViewAdapter extends BaseSwipeAdapter {

    private static final String LOG_TAG = "snake book Word Adapter";

    private Context context;

    public WordListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_word_list;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.word_list_item, null);
        SwipeLayout swipeLayout = (SwipeLayout) itemView.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(final SwipeLayout layout) {
                TextView textView = (TextView) layout.findViewById(R.id.text_word_item_name);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                super.onOpen(layout);
            }
        });
        swipeLayout.addOnLayoutListener(new SwipeLayout.OnLayout() {
            @Override
            public void onLayout(SwipeLayout v) {

            }
        });
        return itemView;
    }

    @Override
    public void fillValues(int position, View convertView) {
        TextView text = (TextView) convertView.findViewById(R.id.text_word_item_name);
        ObjectValueView objectValueView = (ObjectValueView) convertView.findViewById(R.id.object_word_item_book_id);
        Word word = Word.findById(Word.class, position + 1);
        if(null != word) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            text.setText(gson.toJson(word));
            objectValueView.setValue(word.getBookId());
        }
    }

    @Override
    public int getCount() {
        return (int) Word.count(Word.class);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
