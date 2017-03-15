package com.android.snake.utils;

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
public class HttpNetInstant {

    private static final String CHAR_SET = "UTF-8";
//    private static final String serverHost = "http://localhost:8007/";
    private static final String serverHost = "http://192.168.2.106:8007/";

    private static HttpNetInstant instant;

    public static HttpNetInstant getInstant() {
        return new HttpNetInstant();
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

    public String doHttpRequest(String uri,String method, String params) {
        String result = null;
        OutputStream out = null;
        InputStream in = null;
        try {
            URL url = new URL(serverHost + uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setDoInput(true);
            connection.setRequestMethod(method);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (null != params) {
                byte[] data = params.toString().getBytes(CHAR_SET);
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
            result = "error : " + e.getMessage();
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
        return result;
    }
}
