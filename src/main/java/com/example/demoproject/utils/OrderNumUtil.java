package com.example.demoproject.utils;

import java.text.SimpleDateFormat;

public class OrderNumUtil {
    /**
     * 根据时间戳生成唯一id
     */
    public static String getOrderId(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");

        return sdf.format(System.currentTimeMillis());
    }
}
