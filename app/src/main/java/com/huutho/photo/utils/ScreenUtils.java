package com.huutho.photo.utils;

import android.content.res.Resources;

/**
 * Created by ThoNh on 10/31/2017.
 */

public class ScreenUtils {
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
