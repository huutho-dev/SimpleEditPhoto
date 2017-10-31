package com.huutho.photo.di.module;

import android.support.v4.app.Fragment;

import com.huutho.photo.R;
import com.huutho.photo.models.Tool;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NguyenHuuTho on 10/31/2017.
 */

@Module
public class EditorModule {

    @Provides
    List<Tool> provideTools() {
        List<Tool> tools = new ArrayList<>();
        tools.add(new Tool("Filters", R.drawable.ic_tool_fillter, new Fragment()));
        tools.add(new Tool("Adjust", R.drawable.ic_tool_adjust, new Fragment()));
        tools.add(new Tool("Overlay", R.drawable.ic_tool_overlay, new Fragment()));
        tools.add(new Tool("Sticker", R.drawable.ic_tool_sticker, new Fragment()));
        tools.add(new Tool("Frames", R.drawable.ic_tool_frame, new Fragment()));
        tools.add(new Tool("Transform", R.drawable.ic_tool_transform, new Fragment()));
        tools.add(new Tool("Vignette", R.drawable.ic_tool_vignette, new Fragment()));
        tools.add(new Tool("Tilt Shift", R.drawable.ic_tool_linear_blur, new Fragment()));
        tools.add(new Tool("Drawing", R.drawable.ic_tool_draw, new Fragment()));
        tools.add(new Tool("Text", R.drawable.ic_tool_text, new Fragment()));
        return tools;
    }

}
