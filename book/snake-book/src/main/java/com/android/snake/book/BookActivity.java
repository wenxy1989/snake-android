package com.android.snake.book;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.snake.common.AbstractNetCallback;
import com.android.snake.service.BookNetService;
import com.android.snake.service.BookService;

/**
 * Created by wenxy on 2017/3/15.
 */

public class BookActivity extends Activity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.book_home);
        Log.i("book activity","load activity");
//        ToastTool.instance().showToastLong(this,sb.toString());
        MyTask task = new MyTask();
        task.execute("");

    }

    class MyTask extends AsyncTask<Object,Object,Object>{

        @Override
        protected Object doInBackground(Object[] params) {
            new BookService(BookActivity.this).updateLocal();
            return null;
        }

    }

}
