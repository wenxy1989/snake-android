package com.android.snake;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wenxy on 2017/3/18.
 */

public class ObjectValueView extends View {

    private Object value;

    public ObjectValueView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setVisibility(View.GONE);
    }

    /*public ObjectValueView(Context context) {
        super(context);
        this.setVisibility(View.GONE);
    }*/

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
