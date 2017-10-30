package com.huutho.photo.utils;

import android.util.Log;

import com.huutho.photo.BuildConfig;

/**
 * Created by NguyenHuuTho on 10/30/2017.
 */

public class LogUtils {

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG)
            Log.e(tag, ">>>>>>>>>>>" + msg);
    }

}
