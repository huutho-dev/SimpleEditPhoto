package com.huutho.photo.utils;


import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by NguyenHuuTho on 11/5/2017.
 */

// https://developer.android.com/reference/java/text/SimpleDateFormat.html

public class TimeUtils {

    public static String longToDate(long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd/MM", Locale.getDefault());
        return DateFormat.format(simpleDateFormat.toPattern(), new Date(millisecond)).toString();
    }
}
