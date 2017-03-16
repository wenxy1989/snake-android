package com.android.snake.service;

import android.content.Context;
import android.util.Log;

import com.android.snake.book.BookActivity;
import com.android.snake.common.AbstractNetCallback;
import com.android.snake.model.Book;

import java.util.List;

/**
 * Created by HP on 2017/3/16.
 */

public class BookService {

    private Context context;

    public BookService(Context context) {
        this.context = context;
    }

    public void updateLocal() {
        NetService netService = BookNetService.getInstance().getList(new AbstractNetCallback(this.context) {
            @Override
            public void callback(Object result) {
                List<Book> list = (List<Book>) result;
                for (Book book : list) {
                    Log.d("book name ",book.getName());
                    Log.d("book author ",book.getAuthor());
                    Log.d("book introduction",book.getIntroduction());
                    Log.d("book createdTime",book.getCreatedTime());
//                    long id = book.save();
//                    Log.i("book save ", "book id is " + id);
                }
            }
        });
        Log.i("book activity", "prepare do http request");
        netService.syncExecute();
    }

}
