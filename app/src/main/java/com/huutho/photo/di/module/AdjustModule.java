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
    public static final String KEY_ADJUST_TOOL = "KEY_ADJUST_TOOL";
    public static final String KEY_ADJUST_CONFIG = "KEY_ADJUST_CONFIG";


    @Provides
    Map<String, Object> provideAdjust() {
        List<Adjust> adjusts = new ArrayList<>();

        adjusts.add(new Adjust("Sharpness", R.drawable.icon_adjust_shaprness_dark, "Sharpness", 1, -1.0f, 10.0f, 0.0f));
        adjusts.add(new Adjust("Temperature", R.drawable.icon_adjust_temperature_dark, "Vignette", 2, 0.0f, 1.0f, 0.5f));
        adjusts.add(new Adjust("Hue", R.drawable.icon_adjust_hue_dark, "Vignette", 3, 0.0f, 1.0f, 0.5f));
        adjusts.add(new Adjust("Brightness", R.drawable.icon_adjust_brightness_dark, "Brightness", 4, -1.0f, 1, 0));
        adjusts.add(new Adjust("Contrast", R.drawable.icon_adjust_contrast_dark, "Contrast", 5, 0.1f, 3.0f, 1.0f));
        adjusts.add(new Adjust("Vibrance", R.drawable.icon_adjust_vibrance_dark, "Vignette", 6, 0.0f, 1.0f, 0.5f));
        adjusts.add(new Adjust("Saturation", R.drawable.icon_adjust_saturation_dark, "Saturation", 7, 0.0f, 3.0f, 1.0f));
        adjusts.add(new Adjust("Vignette", R.drawable.icon_adjust_vignette_dark, "Vignette", 8, 0.0f, 1.0f, 0.5f));
        adjusts.add(new Adjust("Light Center", R.drawable.icon_adjust_light_center_dark, "Vignette", 9, 0.0f, 1.0f, 0.5f));

        String adjustConfig = "@adjust sharpen 0";
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put(KEY_ADJUST_TOOL, adjusts);
        hashMap.put(KEY_ADJUST_CONFIG, adjustConfig);

        return hashMap;
    }
}
