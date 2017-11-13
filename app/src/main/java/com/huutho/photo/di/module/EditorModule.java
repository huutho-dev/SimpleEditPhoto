package com.huutho.photo.di.module;

import com.huutho.photo.R;
import com.huutho.photo.models.Adjust0;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NguyenHuuTho on 10/31/2017.
 */

@Module
public class EditorModule {

    // Un Use
    @Provides
    List<Adjust0> provideAdjust0() {
        List<Adjust0> adjust0s = new ArrayList<>();
        adjust0s.add(new Adjust0("Rotate", R.drawable.ic_adjust1_rotate_dark));
        adjust0s.add(new Adjust0("Distortion", R.drawable.icon_adjust1_correction_distortion_dark));
        adjust0s.add(new Adjust0("Horizontal", R.drawable.icon_adjust1_horizontal_dark));
        adjust0s.add(new Adjust0("Vertical", R.drawable.icon_adjust1_vertical_dark));
        adjust0s.add(new Adjust0("Perspective vertical", R.drawable.icon_adjust1_perspective_vertical_dark));
        adjust0s.add(new Adjust0("Perspective horizontal", R.drawable.icon_adjust1_perspective_dark));
        adjust0s.add(new Adjust0("Stretch horizontal", R.drawable.icon_adjust1_stretch_dark));
        adjust0s.add(new Adjust0("Stretch vertical", R.drawable.icon_adjust1_stretch_vertical_dark));
        return adjust0s;
    }


}
