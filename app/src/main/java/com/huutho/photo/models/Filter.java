package com.huutho.photo.models;

import android.graphics.Bitmap;

import org.wysaid.nativePort.CGENativeLibrary;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class Filter {
    public String filter;

    public Filter(String filter) {
        this.filter = filter;
    }

    public Bitmap getBitmapFilter(Bitmap bitmap) {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap);
        return CGENativeLibrary.filterImage_MultipleEffects(newBitmap, filter, 1.0f);
    }
}
