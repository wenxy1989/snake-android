package com.android.snake.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wenxy on 2017/3/11.
 */
public class HttpInstant {

    private static final String CHAR_SET = "UTF-8";
//    private static final String serverHost = "http://localhost:8007/";
//    private static final String serverHost = "http://192.168.2.106:8007/";
    private static final String serverHost = "http://192.168.3.44:8007/";

    private static HttpInstant instant;

    public static HttpInstant getInstant() {
        return new HttpInstant();
    }

    public String decodeInputStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, CHAR_SET));
        int size = 1024;
        char[] data = new char[size];
        int offset = -1;
        CharArrayWriter writer = new CharArrayWriter();
        while ((offset = reader.read(data, 0, size)) != -1) {
            writer.write(data, 0, offset);
        }
        return writer.toString();
    }

    public String doGet(String uri) {
        return doHttpRequest(uri,"GET",null);
    }

    public String doGet(String uri,String json) {
        return doHttpRequest(uri,"GET",json);
    }

    public String doPost(String uri) {
        return doHttpRequest(uri,"POST",null);
    }

    public String doPost(String uri,String json) {
        return doHttpRequest(uri,"POST",json);
    }

    public String doHttpRequest(String uri,String method, String json) {
        String result = null;
        OutputStream out = null;
        InputStream in = null;
        try {
            Log.d("book http url ",serverHost + uri);
            if(null != json) {
                Log.d("book http data  ", json);
            }
            URL url = new URL(serverHost + uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setDoOutput(true);
            connection.setRequestMethod(method);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/json");
            if (null != json) {
                byte[] data = json.getBytes(CHAR_SET);
                connection.setRequestProperty("Content-Length", String.valueOf(data.length));
                connection.setDoInput(true);
                out = connection.getOutputStream();
                out.write(data);
            }
            int response = connection.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = connection.getInputStream();
                result = decodeInputStream(in);
            }
        } catch (IOException e) {
            Log.d("book http error ", e.getMessage());
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d("book http return ", result);
        return result;
    }

    /*public static void main(String args[]){
        String result = HttpInstant.getInstant().doPost("book/api/book/list");
        String result = HttpInstant.getInstant().doGet("book/api/book/list","{\"start\":0,\"count\":5}");
        System.out.println(result);
    }*/

}
