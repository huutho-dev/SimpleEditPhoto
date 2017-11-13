package com.huutho.photo.di.module;

import com.huutho.photo.R;
import com.huutho.photo.models.Blur;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ThoNh on 11/13/2017.
 */

@Module
public class BlurModule {

    @Provides
    List<Blur> provideBlur() {
        List<Blur> blurs = new ArrayList<>();
        blurs.add(new Blur("Blur Round", R.drawable.icon_blur_round_dark));
        blurs.add(new Blur("Distortion", R.drawable.icon_blur_linear_dark));
        return blurs;
    }
}
