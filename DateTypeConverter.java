package com.example.myeonghusong.lightinstagram;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by myeonghusong on 2018. 11. 25..
 */

public class DateTypeConverter {
    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
