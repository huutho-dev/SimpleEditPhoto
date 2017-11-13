package com.huutho.photo.di.module;

import com.huutho.photo.Constant;
import com.huutho.photo.models.Overlay;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ThoNh on 11/13/2017.
 */

@Module
public class OverlayModule {
    @Provides
    List<Overlay> provideOverlay() {
        List<Overlay> overlays = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            String path = Constant.OVERLAY.OVERLAY + "overlay_";
            overlays.add(new Overlay(path + i + ".jpg"));
        }
        return overlays;
    }
}
