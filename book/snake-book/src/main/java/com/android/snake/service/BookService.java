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
    }

}
