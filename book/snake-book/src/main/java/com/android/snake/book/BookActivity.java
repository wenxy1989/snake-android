package com.android.snake.book;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.mazouri.tools.Tools;
import com.mazouri.tools.app.ToastTool;

import java.util.List;

/**
 * Created by wenxy on 2017/3/15.
 */

public class BookActivity extends Activity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.book_home);
        Log.i("book activity","load activity");
        BookNetService netService = new BookNetService(this,"list");
        new Thread(netService).start();
        List<Book> list = (List<Book>) netService.getResult();
        StringBuffer sb = new StringBuffer();
        for(Book book : list) {
            sb.append(book.getId()+":"+book.getName()+"\\n");
        }
//        ToastTool.instance().showToastLong(this,sb.toString());
    }
}
