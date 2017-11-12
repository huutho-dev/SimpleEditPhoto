package com.huutho.photo.models;

import com.isseiaoki.simplecropview.CropImageView;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class Crop {
    public int mIcon;
    public String mName;
    public CropImageView.CropMode mCropMode;

    public Crop(String name, int icon, CropImageView.CropMode cropMode) {
        mIcon = icon;
        mName = name;
        this.mCropMode = cropMode;
    }
}
