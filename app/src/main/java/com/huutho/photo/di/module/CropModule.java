package com.huutho.photo.di.module;

import com.huutho.photo.R;
import com.huutho.photo.models.Crop;
import com.isseiaoki.simplecropview.CropImageView;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ThoNh on 11/13/2017.
 */

@Module
public class CropModule {
    @Provides
    List<Crop> provideCrop() {
        List<Crop> crops = new ArrayList<>();
        crops.add(new Crop("Free", R.drawable.icon_crop_free_dark, CropImageView.CropMode.FREE));
        crops.add(new Crop("1:1", R.drawable.icon_crop_1_1_dark, CropImageView.CropMode.SQUARE));
        crops.add(new Crop("2:3", R.drawable.icon_crop_2_3_dark, CropImageView.CropMode.RATIO_16_9));
        crops.add(new Crop("3:4", R.drawable.icon_crop_3_4_dark, CropImageView.CropMode.RATIO_3_4));
        crops.add(new Crop("9:16", R.drawable.icon_crop_9_16_dark, CropImageView.CropMode.RATIO_9_16));
        return crops;
    }

}
