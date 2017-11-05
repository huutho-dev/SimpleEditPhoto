package com.huutho.photo.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenHuuTho on 10/30/2017.
 */

public class ImageAlbum implements Parcelable {

    public String mName;

    public List<Image> mImages;

    public ImageAlbum(String name) {
        mName = name;
        mImages = new ArrayList<>();
    }

    protected ImageAlbum(Parcel in) {
        mName = in.readString();
        mImages = in.createTypedArrayList(Image.CREATOR);
    }

    public static final Creator<ImageAlbum> CREATOR = new Creator<ImageAlbum>() {
        @Override
        public ImageAlbum createFromParcel(Parcel in) {
            return new ImageAlbum(in);
        }

        @Override
        public ImageAlbum[] newArray(int size) {
            return new ImageAlbum[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeTypedList(mImages);
    }


}
