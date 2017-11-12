package com.huutho.photo.models;

import android.support.annotation.IntDef;

import static com.huutho.photo.models.Rotate.RotateMode.FLIP_HORIZONTAL;
import static com.huutho.photo.models.Rotate.RotateMode.FLIP_VERTICAL;
import static com.huutho.photo.models.Rotate.RotateMode.ROTATE_LEFT;
import static com.huutho.photo.models.Rotate.RotateMode.ROTATE_RIGHT;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class Rotate {
    public int mIcon;
    public String mName;
    public int mRotateMode;

    @IntDef({ROTATE_LEFT, ROTATE_RIGHT, FLIP_VERTICAL, FLIP_HORIZONTAL})
    public @interface RotateMode {
        public static final int ROTATE_LEFT = 1;
        public static final int ROTATE_RIGHT = 2;
        public static final int FLIP_VERTICAL = 3;
        public static final int FLIP_HORIZONTAL = 4;

    }


    public Rotate(String name, int icon, @RotateMode int rotateMode) {
        mIcon = icon;
        mName = name;
        this.mRotateMode = rotateMode;
    }
}
