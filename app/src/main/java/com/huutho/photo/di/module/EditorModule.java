package com.huutho.photo.di.module;

import android.support.v4.app.Fragment;

import com.huutho.photo.R;
import com.huutho.photo.edit.fragment.adjust.AdjustFragment;
import com.huutho.photo.edit.fragment.drawing.DrawingFragment;
import com.huutho.photo.edit.fragment.frame.FrameFragment;
import com.huutho.photo.edit.fragment.overlay.OverlayFragment;
import com.huutho.photo.edit.fragment.sticker.StickerFragment;
import com.huutho.photo.models.Adjust;
import com.huutho.photo.models.BrushSize;
import com.huutho.photo.models.EditorColor;
import com.huutho.photo.models.Frame;
import com.huutho.photo.models.Overlay;
import com.huutho.photo.models.Sticker;
import com.huutho.photo.models.StickerCategory;
import com.huutho.photo.models.Tool;

import java.util.ArrayList;
import java.util.List;

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
        tools.add(new Tool("Adjust", R.drawable.ic_tool_adjust, AdjustFragment.newInstance()));
        tools.add(new Tool("Overlay", R.drawable.ic_tool_overlay, OverlayFragment.newInstance()));
        tools.add(new Tool("Sticker", R.drawable.ic_tool_sticker, StickerFragment.newInstance()));
        tools.add(new Tool("Frames", R.drawable.ic_tool_frame, FrameFragment.newInstance()));
        tools.add(new Tool("Drawing", R.drawable.ic_tool_draw, DrawingFragment.newInstance()));
        tools.add(new Tool("Text", R.drawable.ic_tool_text, new Fragment()));
        return tools;
    }

    @Provides
    List<Adjust> provideAdjust() {
        List<Adjust> adjusts = new ArrayList<>();
        adjusts.add(new Adjust("Brightness", R.mipmap.ic_launcher_round, "Brightness"));
        adjusts.add(new Adjust("Contrast", R.mipmap.ic_launcher_round, "Contrast"));
        adjusts.add(new Adjust("Pixelation", R.mipmap.ic_launcher_round, "Pixelation"));
        adjusts.add(new Adjust("Saturation", R.mipmap.ic_launcher_round, "Saturation"));
        adjusts.add(new Adjust("Gamma", R.mipmap.ic_launcher_round, "Gamma"));
        adjusts.add(new Adjust("Sharpness", R.mipmap.ic_launcher_round, "Sharpness"));
        adjusts.add(new Adjust("Vignette", R.mipmap.ic_launcher_round, "Vignette"));
        adjusts.add(new Adjust("Sepia", R.mipmap.ic_launcher_round, "Sepia"));
        adjusts.add(new Adjust("White balance", R.mipmap.ic_launcher_round, "WhiteBalance"));
        adjusts.add(new Adjust("Lookup", R.mipmap.ic_launcher_round, "Lookup"));
        return adjusts;
    }

    @Provides
    List<Overlay> provideOverlay() {
        List<Overlay> overlays = new ArrayList<>();
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        overlays.add(new Overlay("https://resizing.flixster.com/NGNXpOTfcpdQns8g7FU9YOncZXg=/300x300/v1.aDsxMzcyNzI7ajsxNzUwNjsxMjAwOzQyMDA7MTc2MA"));
        return overlays;
    }

    @Provides
    List<StickerCategory> provideSticker() {
        List<StickerCategory> stickerCategories = new ArrayList<>();
        stickerCategories.add(new StickerCategory(R.mipmap.ic_launcher, getSticker1()));
        stickerCategories.add(new StickerCategory(R.mipmap.ic_launcher, getSticker2()));
        stickerCategories.add(new StickerCategory(R.mipmap.ic_launcher, getSticker3()));
        stickerCategories.add(new StickerCategory(R.mipmap.ic_launcher, getSticker4()));
        stickerCategories.add(new StickerCategory(R.mipmap.ic_launcher, getSticker5()));
        return stickerCategories;
    }

    @Provides
    List<Frame> provideFrame() {
        List<Frame> frames = new ArrayList<>();
        frames.add(new Frame(R.drawable.ic_launcher));
        frames.add(new Frame(R.drawable.ic_launcher));
        frames.add(new Frame(R.drawable.ic_launcher));
        frames.add(new Frame(R.drawable.ic_launcher));
        frames.add(new Frame(R.drawable.ic_launcher));
        frames.add(new Frame(R.drawable.ic_launcher));
        frames.add(new Frame(R.drawable.ic_launcher));
        frames.add(new Frame(R.drawable.ic_launcher));
        frames.add(new Frame(R.drawable.ic_launcher));

        return frames;
    }

    @Provides
    List<BrushSize> provideBrushSize() {
        List<BrushSize> sizes = new ArrayList<>();
        sizes.add(new BrushSize(10));
        sizes.add(new BrushSize(12));
        sizes.add(new BrushSize(14));
        sizes.add(new BrushSize(18));
        sizes.add(new BrushSize(20));
        sizes.add(new BrushSize(22));
        sizes.add(new BrushSize(24));
        sizes.add(new BrushSize(26));
        sizes.add(new BrushSize(28));
        sizes.add(new BrushSize(30));
        sizes.add(new BrushSize(33));
        sizes.add(new BrushSize(36));
        sizes.add(new BrushSize(39));
        sizes.add(new BrushSize(42));
        sizes.add(new BrushSize(45));
        sizes.add(new BrushSize(50));
        return sizes;
    }

    @Provides
    List<EditorColor> provideEditorColor(){
        return EditorColor.getColorsList();
    }


    private List<Sticker> getSticker1() {
        List<Sticker> stickers = new ArrayList<>();
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        return stickers;
    }

    private List<Sticker> getSticker2() {
        List<Sticker> stickers = new ArrayList<>();
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        return stickers;
    }

    private List<Sticker> getSticker3() {
        List<Sticker> stickers = new ArrayList<>();
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        return stickers;
    }

    private List<Sticker> getSticker4() {
        List<Sticker> stickers = new ArrayList<>();
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        return stickers;
    }

    private List<Sticker> getSticker5() {
        List<Sticker> stickers = new ArrayList<>();
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        stickers.add(new Sticker(R.mipmap.ic_launcher_round));
        return stickers;
    }

}
