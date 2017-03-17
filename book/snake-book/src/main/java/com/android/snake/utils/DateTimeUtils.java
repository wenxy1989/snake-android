package com.android.snake.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_TIME_NUMBER_PATTERN = "yyyyMMddHHmmss";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String DATE_NUMBER_PATTERN = "yyyyMMdd";
    private static final String DAY_TIME_PATTERN = "HH:mm";
    private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_PATTERN);

    private DateTimeUtils(){}

    public static DateTimeUtils getInstance(){
        return new DateTimeUtils();
    }

    public String getNowDateTime(){
        return DATE_TIME_FORMAT.format(new Date());
    }

}