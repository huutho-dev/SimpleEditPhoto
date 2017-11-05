package com.huutho.photo.utils;

import android.graphics.Bitmap;
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

    public static void logBitmap(String tag, Bitmap bitmap) {
        if (BuildConfig.DEBUG) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Log.d(tag, "width:" + width + "\nheight:" + height);
        }
    }

}
