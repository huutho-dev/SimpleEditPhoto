package com.huutho.photo.di.module;

import android.support.v4.app.Fragment;

import com.huutho.photo.R;
import com.huutho.photo.edit.fragment.adjust.AdjustFragment;
import com.huutho.photo.edit.fragment.crop.CropFragment;
import com.huutho.photo.edit.fragment.drawing.DrawingFragment;
import com.huutho.photo.edit.fragment.filter.FilterFragment;
import com.huutho.photo.edit.fragment.frame.FrameFragment;
import com.huutho.photo.edit.fragment.overlay.OverlayFragment;
import com.huutho.photo.edit.fragment.rotate.RotateFragment;
import com.huutho.photo.edit.fragment.sticker.StickerFragment;
import com.huutho.photo.models.Tool;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ThoNh on 11/13/2017.
 */

@Module
public class ToolsModule {

    @Provides
    List<Tool> provideTools() {
        List<Tool> tools = new ArrayList<>();
        tools.add(new Tool("Filters", R.drawable.icon_cat_filter, FilterFragment.newInstance()));
        tools.add(new Tool("Adjust", R.drawable.icon_cat_adjust, AdjustFragment.newInstance()));
        tools.add(new Tool("Crop", R.drawable.icon_cat_crop, CropFragment.newInstance()));
        tools.add(new Tool("Rotate", R.drawable.icon_cat_rotate, RotateFragment.newInstance()));
        tools.add(new Tool("Overlay", R.drawable.ic_tool_overlay, OverlayFragment.newInstance())); // Khảm
        tools.add(new Tool("Sticker", R.drawable.ic_emoji, StickerFragment.newInstance()));
        tools.add(new Tool("Frames", R.drawable.icon_cat_frame, FrameFragment.newInstance()));
        tools.add(new Tool("Drawing", R.drawable.icon_cat_drawing, DrawingFragment.newInstance()));
        tools.add(new Tool("Text", R.drawable.ic_tool_text, new Fragment()));
        return tools;
    }
}
