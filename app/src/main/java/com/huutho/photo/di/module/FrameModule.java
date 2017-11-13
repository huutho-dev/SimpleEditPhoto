package com.huutho.photo.di.module;

import com.huutho.photo.Constant;
import com.huutho.photo.models.Frame;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ThoNh on 11/13/2017.
 */

@Module
public class FrameModule {

    @Provides
    List<Frame> provideFrame() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i <= 43; i++) {
            String pathFrame = Constant.FILE_ASSETS + Constant.FRAME.FRAME + Constant.FRAME.FRAME + "border_" + i + ".png";
            String pathDescription = Constant.FILE_ASSETS + Constant.FRAME.FRAME + Constant.FRAME.ICON + "ic_border_" + i + ".png";
            frames.add(new Frame(pathFrame, pathDescription));
        }
        return frames;
    }
}
