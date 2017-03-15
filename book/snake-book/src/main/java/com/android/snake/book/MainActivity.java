package com.android.snake.book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mazouri.tools.Tools;

/**
 * Created by wenxy on 2017/3/12.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);
        this.startActivity(new Intent(this,BookActivity.class));
    }
}
