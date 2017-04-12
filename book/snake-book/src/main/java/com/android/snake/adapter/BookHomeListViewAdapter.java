package com.android.snake.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.snake.ObjectValueView;
import com.android.snake.book.model.ParagraphListActivity;
import com.android.snake.book.model.PhraseListActivity;
import com.android.snake.book.R;
import com.android.snake.model.Book;
import com.android.snake.task.ParagraphAsyncTask;
import com.android.snake.task.PhraseAsyncTask;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by wenxy on 2017/3/18.
 */

public class BookHomeListViewAdapter extends BaseSwipeAdapter {

    private static final String LOG_TAG = "snake book B";

    public static long selected_book_id = 0l;

    private Context context;

    private View.OnClickListener bookNameOnClick;
    public void bookNameOnClick(View.OnClickListener bookNameOnClick){
        this.bookNameOnClick = bookNameOnClick;
    }

    public BookHomeListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.book_home_list_swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.book_home_list_item, null);
        SwipeLayout swipeLayout = (SwipeLayout) itemView.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(final SwipeLayout layout) {
                TextView textBookName = (TextView) layout.findViewById(R.id.text_book_home_item_name);
//                textBookName.setOnClickListener(bookNameOnClick);
                textBookName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selected_book_id = (Long) ((ObjectValueView) layout.findViewById(R.id.object_book_home_item_book_id)).getValue();
                        context.startActivity(new Intent(context,ParagraphListActivity.class));
                    }
                });
                TextView text_book_home_paragraph = (TextView) layout.findViewById(R.id.text_book_home_paragraph);
                text_book_home_paragraph.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selected_book_id = (Long) ((ObjectValueView) layout.findViewById(R.id.object_book_home_item_book_id)).getValue();
                        context.startActivity(new Intent(context,ParagraphListActivity.class));
                    }
                });
                TextView text_book_home_phrase = (TextView) layout.findViewById(R.id.text_book_home_phrase);
                text_book_home_phrase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selected_book_id = (Long) ((ObjectValueView) layout.findViewById(R.id.object_book_home_item_book_id)).getValue();
                        context.startActivity(new Intent(context,PhraseListActivity.class));
                    }
                });
                TextView text_async_phrase = (TextView) layout.findViewById(R.id.text_book_list_async_phrase);
                text_async_phrase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Long bookId = (Long) ((ObjectValueView) layout.findViewById(R.id.object_book_home_item_book_id)).getValue();
                        asyncPhraseData(bookId);
                    }
                });
                TextView text_async_paragraph = (TextView) layout.findViewById(R.id.text_book_list_async_paragraph);
                text_async_paragraph.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Long bookId = (Long) ((ObjectValueView) layout.findViewById(R.id.object_book_home_item_book_id)).getValue();
                        asyncParagraphData(bookId);
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

    public void asyncPhraseData(final Long bookId) {
        try {
            AsyncTask asyncTask = new PhraseAsyncTask(bookId);
            asyncTask.execute();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    public void asyncParagraphData(final Long bookId) {
        try {
            AsyncTask asyncTask = new ParagraphAsyncTask(bookId);
            asyncTask.execute();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    @Override
    public void fillValues(int position, View convertView) {
        TextView text = (TextView) convertView.findViewById(R.id.text_book_home_item_name);
        ObjectValueView objectValueView = (ObjectValueView) convertView.findViewById(R.id.object_book_home_item_book_id);
        Book book = Book.findById(Book.class, position + 1);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        text.setText(gson.toJson(book));
        objectValueView.setValue(book.getBookId());
    }

    @Override
    public int getCount() {
        return (int) Book.count(Book.class);
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
