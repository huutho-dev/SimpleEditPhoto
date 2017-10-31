package com.huutho.photo.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NguyenHuuTho on 10/30/2017.
 */

public class Image implements Parcelable {
    public int mId;
    public String mPath;

    public Image(int id, String path) {
        mId = id;
        mPath = path;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mPath);
    }

    protected Image(Parcel in) {
        this.mId = in.readInt();
        this.mPath = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
