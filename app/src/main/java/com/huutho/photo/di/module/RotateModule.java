package com.huutho.photo.di.module;

import com.huutho.photo.R;
import com.huutho.photo.models.Rotate;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ThoNh on 11/13/2017.
 */

@Module
public class RotateModule {

    @Provides
    List<Rotate> provideRotate() {
        List<Rotate> rotates = new ArrayList<>();
        rotates.add(new Rotate("Rotate left", R.drawable.icon_rotate_left, Rotate.RotateMode.ROTATE_LEFT));
        rotates.add(new Rotate("Rotate right", R.drawable.icon_rotate_right, Rotate.RotateMode.ROTATE_RIGHT));
        rotates.add(new Rotate("Rotate left & right", R.drawable.icon_rotate_left_and_right, Rotate.RotateMode.FLIP_HORIZONTAL));
        rotates.add(new Rotate("Rotate up & down", R.drawable.icon_rotate_up_and_down, Rotate.RotateMode.FLIP_VERTICAL));
        return rotates;
    }
}
