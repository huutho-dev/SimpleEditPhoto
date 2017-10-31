package com.huutho.photo.crop;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.isseiaoki.simplecropview.CropImageView;

/**
 * Created by ThoNh on 10/31/2017.
 */

@InjectViewState
public class CropPresenter extends MvpPresenter<CropView> {

    public void fiipHorizontal(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(-1, 1, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        getViewState().fliped(newBitmap, matrix);
    }

    public void fiipVertical(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(1, -1, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        getViewState().fliped(newBitmap, matrix);
    }

    public void crop(CropImageView cropImageView) {

    }
}
