package com.android.snake.service;

import android.util.Log;

import com.android.snake.common.Handler;
import com.android.snake.common.NetCallback;
import com.android.snake.utils.HttpInstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenxy on 2017/3/15.
 */

public abstract class AbstractNetService<T> implements Runnable,NetService {

    protected WeakReference<NetCallback> callback;
    protected Handler beforeCallback;
    protected Handler afterCallback;

    private String uri;
    private String json;

    public abstract String baseURI();

    public abstract T getObject(JSONObject object);

    public AbstractNetService<T> getCount(NetCallback callback) {
        this.uri = "count";
        this.callback = new WeakReference<NetCallback>(callback);
        this.beforeCallback = new Handler() {
            @Override
            public Object process(Object object) {
                Integer count = 0;
                try {
                    if(null != object) {
                        JSONObject json = new JSONObject((String)object);
                        count = json.getInt("count");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return count;
            }
        };
        return this;
    }

    public AbstractNetService<T> getList(NetCallback callback) {
        this.uri = "list";
        this.json = "{}";
        this.callback = new WeakReference<NetCallback>(callback);
        this.beforeCallback = new Handler() {
            @Override
            public Object process(Object object) {
                List<T> list = new ArrayList<T>();
                try {
                    if(null != object) {
                        JSONArray array = new JSONArray((String) object);
                        if (null != array && array.length() > 0) {
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject json = array.getJSONObject(i);
                                list.add(getObject(json));
                            }
                        }
                    }
                } catch (JSONException e) {
                    Log.e("book json array ",e.getMessage());
                }
                return list;
            }
        };
        return this;
    }

    public  AbstractNetService<T> setRequest(String uri, String json, NetCallback callback) {
        this.uri = uri;
        this.json = json;
        this.callback = new WeakReference<NetCallback>(callback);
        return this;
    }

    public void syncExecute() {
        new Thread(this).start();
    }

    private Object doBeforeCallback(final String data){
        if (null != beforeCallback) {
            return beforeCallback.process(data);
        }else{
            return data;
        }
    }

    private Object doAfterCallback(final Object object){
        if (null != beforeCallback) {
            return  beforeCallback.process(object);
        }else{
            return object;
        }
    }

    @Override
    public void run() {
        if (null != this.callback) {
            String data = null;
            if (null != this.json) {
                data = HttpInstant.getInstant().doPost(baseURI() + uri, json);
            } else {
                data = HttpInstant.getInstant().doGet(baseURI() + uri);
            }
            Object result = doBeforeCallback(data);
            this.callback.get().callback(result);
            doAfterCallback(result);
        }
    }

}
