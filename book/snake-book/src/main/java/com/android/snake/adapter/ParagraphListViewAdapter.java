package com.android.snake.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.snake.ObjectValueView;
import com.android.snake.book.R;
import com.android.snake.model.Book;
import com.android.snake.model.Paragraph;
import com.android.snake.task.ParagraphAsyncTask;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;

import java.util.List;

/**
 * Created by wenxy on 2017/3/18.
 */

public class ParagraphListViewAdapter extends BaseSwipeAdapter {

    private static final String LOG_TAG = "snake book Paragraph Adapter";

    private Context context;
    private Long bookId;

    public ParagraphListViewAdapter(Context context,long bookId) {
        this.context = context;
        this.bookId = bookId;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.paragraph_list_swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.paragraph_list_item, null);
        SwipeLayout swipeLayout = (SwipeLayout) itemView.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(final SwipeLayout layout) {
                TextView textView = (TextView) layout.findViewById(R.id.text_paragraph_item_name);
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
        TextView text = (TextView) convertView.findViewById(R.id.text_paragraph_item_name);
        List<Paragraph> list = Paragraph.find(Paragraph.class,"book_id=? limit ?,1", new String[]{bookId+"",(position + 1)+""});
        if(null != list && list.size() > 0) {
            text.setText(list.get(0).getValue());
        }
    }

    @Override
    public int getCount() {
        return (int) Paragraph.count(Paragraph.class);
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
