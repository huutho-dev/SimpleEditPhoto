package com.huutho.photo.di.module;

import android.support.v4.app.Fragment;

import com.huutho.photo.Constant;
import com.huutho.photo.R;
import com.huutho.photo.edit.fragment.adjust.AdjustFragment;
import com.huutho.photo.edit.fragment.adjust0.Adjust0Fragment;
import com.huutho.photo.edit.fragment.blur.BlurFragment;
import com.huutho.photo.edit.fragment.crop.CropFragment;
import com.huutho.photo.edit.fragment.drawing.DrawingFragment;
import com.huutho.photo.edit.fragment.filter.FilterFragment;
import com.huutho.photo.edit.fragment.frame.FrameFragment;
import com.huutho.photo.edit.fragment.mosaic.MosaicFragment;
import com.huutho.photo.edit.fragment.overlay.OverlayFragment;
import com.huutho.photo.edit.fragment.rotate.RotateFragment;
import com.huutho.photo.edit.fragment.sticker.StickerFragment;
import com.huutho.photo.models.Adjust;
import com.huutho.photo.models.Adjust0;
import com.huutho.photo.models.Blur;
import com.huutho.photo.models.BrushSize;
import com.huutho.photo.models.Crop;
import com.huutho.photo.models.EditorColor;
import com.huutho.photo.models.Filter;
import com.huutho.photo.models.Frame;
import com.huutho.photo.models.Mosaic;
import com.huutho.photo.models.Overlay;
import com.huutho.photo.models.Rotate;
import com.huutho.photo.models.Sticker;
import com.huutho.photo.models.StickerCategory;
import com.huutho.photo.models.Tool;
import com.isseiaoki.simplecropview.CropImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NguyenHuuTho on 10/31/2017.
 */

@Module
public class EditorModule {

    public static final String KEY_ADJUST_TOOL = "KEY_ADJUST_TOOL";
    public static final String KEY_ADJUST_CONFIG = "KEY_ADJUST_CONFIG";

    public static final String KEY_OVERLAY_TOOL = "KEY_OVERLAY_TOOL";
    public static final String KEY_OVERLAY_CONFIG = "KEY_OVERLAY_CONFIG";

    @Provides
    List<Tool> provideTools() {
        List<Tool> tools = new ArrayList<>();
        tools.add(new Tool("Filters", R.drawable.icon_cat_filter, FilterFragment.newInstance()));
        tools.add(new Tool("Mosaic", R.drawable.icon_cat_mosaic_class, MosaicFragment.newInstance()));
        tools.add(new Tool("Blur", R.drawable.icon_cat_blur, BlurFragment.newInstance()));
        tools.add(new Tool("Adjust", R.drawable.icon_cat_adjust_1, Adjust0Fragment.newInstance()));
        tools.add(new Tool("Adjust", R.drawable.icon_cat_adjust, AdjustFragment.newInstance()));
        tools.add(new Tool("Crop", R.drawable.icon_cat_crop, CropFragment.newInstance()));
        tools.add(new Tool("Rotate", R.drawable.icon_cat_rotate, RotateFragment.newInstance()));
        tools.add(new Tool("Overlay", R.drawable.ic_tool_overlay, OverlayFragment.newInstance())); // Khảm
        tools.add(new Tool("Sticker", R.drawable.ic_tool_sticker, StickerFragment.newInstance()));
        tools.add(new Tool("Frames", R.drawable.icon_cat_frame, FrameFragment.newInstance()));
        tools.add(new Tool("Drawing", R.drawable.icon_cat_drawing, DrawingFragment.newInstance()));
        tools.add(new Tool("Text", R.drawable.ic_tool_text, new Fragment()));
        return tools;
    }


    @Provides
    List<Adjust0> provideAdjust0() {
        List<Adjust0> adjust0s = new ArrayList<>();
        adjust0s.add(new Adjust0("Rotate", R.drawable.ic_adjust1_rotate_dark));
        adjust0s.add(new Adjust0("Distortion", R.drawable.icon_adjust1_correction_distortion_dark));
        adjust0s.add(new Adjust0("Horizontal", R.drawable.icon_adjust1_horizontal_dark));
        adjust0s.add(new Adjust0("Vertical", R.drawable.icon_adjust1_vertical_dark));
        adjust0s.add(new Adjust0("Perspective vertical", R.drawable.icon_adjust1_perspective_vertical_dark));
        adjust0s.add(new Adjust0("Perspective horizontal", R.drawable.icon_adjust1_perspective_dark));
        adjust0s.add(new Adjust0("Stretch horizontal", R.drawable.icon_adjust1_stretch_dark));
        adjust0s.add(new Adjust0("Stretch vertical", R.drawable.icon_adjust1_stretch_vertical_dark));
        return adjust0s;
    }


    @Provides
    List<Blur> provideBlur() {
        List<Blur> blurs = new ArrayList<>();
        blurs.add(new Blur("Blur Round", R.drawable.icon_blur_round_dark));
        blurs.add(new Blur("Distortion", R.drawable.icon_blur_linear_dark));
        return blurs;
    }

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


    @Provides
    List<Rotate> provideRotate() {
        List<Rotate> rotates = new ArrayList<>();
        rotates.add(new Rotate("Rotate left", R.drawable.icon_rotate_left, Rotate.RotateMode.ROTATE_LEFT));
        rotates.add(new Rotate("Rotate right", R.drawable.icon_rotate_right, Rotate.RotateMode.ROTATE_RIGHT));
        rotates.add(new Rotate("Rotate left & right", R.drawable.icon_rotate_left_and_right, Rotate.RotateMode.FLIP_HORIZONTAL));
        rotates.add(new Rotate("Rotate up & down", R.drawable.icon_rotate_up_and_down, Rotate.RotateMode.FLIP_VERTICAL));
        return rotates;
    }


    @Provides
    List<Filter> provideFilter() {

        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("@adjust lut filter/edgy_amber.png"));
        filters.add(new Filter("@adjust lut filter/filmstock.png"));
        filters.add(new Filter("@adjust lut filter/foggy_night.png"));
        filters.add(new Filter("@adjust lut filter/hehe.png"));
        filters.add(new Filter("@adjust lut filter/soft_warming.png"));
        filters.add(new Filter("@adjust lut filter/wildbird.png"));
        filters.add(new Filter("@curve RGB(0,255)(255,0) @style cm filter/mapping0.jpg 80 80 8 3"));
        return filters;
    }


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


    @Provides
    List<Overlay> provideOverlay() {
        List<Overlay> overlays = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {            String path =  Constant.OVERLAY.OVERLAY + "overlay_";
            overlays.add(new Overlay(path + i + ".jpg"));
        }
        return overlays;
    }

    @Provides
    List<StickerCategory> provideSticker() {
        List<StickerCategory> stickerCategories = new ArrayList<>();
        String path = Constant.FILE_ASSETS + Constant.STICKER.STICKER;
        stickerCategories.add(new StickerCategory(path + Constant.STICKER.BOWKNOT + "image_1.png", getStickerBowknot()));
        stickerCategories.add(new StickerCategory(path + Constant.STICKER.CATFACE + "image_1.png", getStickerCatFace()));
        stickerCategories.add(new StickerCategory(path + Constant.STICKER.EMOTION + "image_1.png", getStickerEmotion()));
        stickerCategories.add(new StickerCategory(path + Constant.STICKER.EYES + "image_1.png", getStickerEyes()));
        stickerCategories.add(new StickerCategory(path + Constant.STICKER.GLASS + "image_1.png", getStickerGlass()));
        stickerCategories.add(new StickerCategory(path + Constant.STICKER.HEART + "image_1.png", getStickerHeart()));
        stickerCategories.add(new StickerCategory(path + Constant.STICKER.SUN + "image_1.png", getStickerSun()));
        stickerCategories.add(new StickerCategory(path + Constant.STICKER.TATTOO + "image_1.png", getStickerTattoo()));
        stickerCategories.add(new StickerCategory(path + Constant.STICKER.TEXT + "image_1.png", getStickerText()));
        return stickerCategories;
    }

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
    List<EditorColor> provideEditorColor() {
        return EditorColor.getColorsList();
    }


    private List<Sticker> getStickerBowknot() {
        List<Sticker> stickers = new ArrayList<>();
        String path = Constant.FILE_ASSETS + Constant.STICKER.STICKER + Constant.STICKER.BOWKNOT;
        for (int i = 1; i <= 22; i++)
            stickers.add(new Sticker(path + "image_" + i + ".jpg"));
        return stickers;
    }

    private List<Sticker> getStickerCatFace() {
        List<Sticker> stickers = new ArrayList<>();
        String path = Constant.FILE_ASSETS + Constant.STICKER.STICKER + Constant.STICKER.CATFACE;
        for (int i = 1; i <= 22; i++)
            stickers.add(new Sticker(path + "image_" + i + ".jpg"));
        return stickers;
    }

    private List<Sticker> getStickerEmotion() {
        List<Sticker> stickers = new ArrayList<>();
        String path = Constant.FILE_ASSETS + Constant.STICKER.STICKER + Constant.STICKER.EMOTION;
        for (int i = 1; i <= 22; i++)
            stickers.add(new Sticker(path + "image_" + i + ".jpg"));
        return stickers;
    }

    private List<Sticker> getStickerEyes() {
        List<Sticker> stickers = new ArrayList<>();
        String path = Constant.FILE_ASSETS + Constant.STICKER.STICKER + Constant.STICKER.EYES;
        for (int i = 1; i <= 22; i++)
            stickers.add(new Sticker(path + "image_" + i + ".jpg"));
        return stickers;
    }

    private List<Sticker> getStickerGlass() {
        List<Sticker> stickers = new ArrayList<>();
        String path = Constant.FILE_ASSETS + Constant.STICKER.STICKER + Constant.STICKER.GLASS;
        for (int i = 1; i <= 22; i++)
            stickers.add(new Sticker(path + "image_" + i + ".jpg"));
        return stickers;
    }

    private List<Sticker> getStickerHeart() {
        List<Sticker> stickers = new ArrayList<>();
        String path = Constant.FILE_ASSETS + Constant.STICKER.STICKER + Constant.STICKER.HEART;
        for (int i = 1; i <= 22; i++)
            stickers.add(new Sticker(path + "image_" + i + ".jpg"));
        return stickers;
    }

    private List<Sticker> getStickerSun() {
        List<Sticker> stickers = new ArrayList<>();
        String path = Constant.FILE_ASSETS + Constant.STICKER.STICKER + Constant.STICKER.SUN;
        for (int i = 1; i <= 22; i++)
            stickers.add(new Sticker(path + "image_" + i + ".jpg"));
        return stickers;
    }

    private List<Sticker> getStickerTattoo() {
        List<Sticker> stickers = new ArrayList<>();
        String path = Constant.FILE_ASSETS + Constant.STICKER.STICKER + Constant.STICKER.TATTOO;
        for (int i = 1; i <= 22; i++) stickers.add(new Sticker(path + "image_" + i + ".jpg"));
        return stickers;
    }

    private List<Sticker> getStickerText() {
        List<Sticker> stickers = new ArrayList<>();
        String path = Constant.FILE_ASSETS + Constant.STICKER.STICKER + Constant.STICKER.TEXT;
        for (int i = 1; i <= 22; i++)
            stickers.add(new Sticker(path + "image_" + i + ".jpg"));
        return stickers;
    }

}
