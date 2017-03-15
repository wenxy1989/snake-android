package com.android.snake.common;

import android.content.Context;

import com.android.snake.utils.HttpNetInstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by wenxy on 2017/3/15.
 */

public abstract class NetService<T> implements Runnable {

    protected WeakReference<Context> context;
    protected String command;

    private Object result;

    public NetService(Context context,String command){
        this.context = new WeakReference<Context>(context);
        this.command = command;
    }

    public Object getResult() {
        return result;
    }

    public abstract T getObject(JSONObject object) throws JSONException;

    public Integer getCount(){
        if(null != context) {
            String result = HttpNetInstant.getInstant().doGet("book/stat");
            try {
                JSONObject object = new JSONObject(result);
                return object.getInt("count");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public List<T> getList(){
        List<T> list = new ArrayList<T>();
        if(null != context) {
        try {
            String result = HttpNetInstant.getInstant().doGet("book/list");
            JSONArray array = new JSONArray(result);
            if(null != array && array.length() > 0){
                for(int i=0;i<array.length();i++){
                    JSONObject object = array.getJSONObject(i);
                    list.add(getObject(object));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
        return list;
    }

    @Override
    public void run() {
        if("count".equals(command)){
            result = getCount();
        }else if("list".equals(command)){
            result = getList();
        }
    }

}
