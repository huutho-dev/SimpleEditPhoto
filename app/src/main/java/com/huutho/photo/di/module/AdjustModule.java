package com.huutho.photo.di.module;

import com.huutho.photo.R;
import com.huutho.photo.models.Adjust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ThoNh on 11/13/2017.
 */

@Module
public class AdjustModule {

    @Provides
    List<Adjust> provideAdjust() {
        List<Adjust> adjusts = new ArrayList<>();

        adjusts.add(new Adjust("Sharpness", R.drawable.icon_adjust_shaprness_dark, 0, -1.0f, 10.0f, 0.0f));
        adjusts.add(new Adjust("Brightness", R.drawable.icon_adjust_brightness_dark, 1, -1.0f, 1, 0));
        adjusts.add(new Adjust("Contrast", R.drawable.icon_adjust_contrast_dark, 2, 0.1f, 3.0f, 1.0f));
        adjusts.add(new Adjust("Vignette", R.drawable.icon_adjust_vignette_dark, 3, 0.0f, 1.0f, 1.0f));
        adjusts.add(new Adjust("Hue", R.drawable.icon_adjust_hue_dark, 4, 0.0f, 6.0f, 0.0f));
        adjusts.add(new Adjust("Saturation", R.drawable.icon_adjust_saturation_dark, 5, 0.0f, 3.0f, 1.0f));
        return adjusts;
    }

    @Provides
    String provideAdjustConfig() {
        return "@adjust sharpen 0 @adjust brightness 0 @adjust contrast 1 @vignette 1 0.15 @adjust hue 0 @adjust saturation 1";


    }
}
