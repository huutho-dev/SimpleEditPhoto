package com.huutho.photo.crop;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.arellomobile.mvp.MvpView;

/**
 * Created by ThoNh on 10/31/2017.
 */

public interface CropView extends MvpView {
    void fliped(Bitmap bitmap, Matrix matrix);
}
