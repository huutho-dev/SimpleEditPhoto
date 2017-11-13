package com.huutho.photo.di.module;

import com.huutho.photo.R;
import com.huutho.photo.models.Mosaic;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ThoNh on 11/13/2017.
 */

@Module
public class MosaicModule {

    @Provides
    List<Mosaic> provideMosaic() {
        List<Mosaic> mosaics = new ArrayList<>();
        mosaics.add(new Mosaic("Erase", R.drawable.icon_mosaic_erase));
        mosaics.add(new Mosaic("Normal", R.drawable.icon_mosaic_icon_normal));
        mosaics.add(new Mosaic("VanGogh", R.drawable.icon_mosaic_icon_pastose_vangogh));
        mosaics.add(new Mosaic("Pastel", R.drawable.icon_mosaic_icon_oilpaint_pastel));
        mosaics.add(new Mosaic("Beast", R.drawable.icon_mosaic_icon_oilpaint_beast));
        mosaics.add(new Mosaic("Ink", R.drawable.icon_mosaic_icon_oilpaint_ink));
        mosaics.add(new Mosaic("Sakura", R.drawable.icon_mosaic_icon_oilpaint_sakura));
        mosaics.add(new Mosaic("Lips", R.drawable.icon_mosaic_icon_oilpaint_lips));
        mosaics.add(new Mosaic("Feather", R.drawable.icon_mosaic_icon_oilpaint_feather));
        mosaics.add(new Mosaic("Claw", R.drawable.icon_mosaic_icon_oilpaint_claw));
        mosaics.add(new Mosaic("Butterfly", R.drawable.icon_mosaic_multibrush_butterfly));
        return mosaics;
    }

}
