package com.huutho.photo.di.module;

import com.huutho.photo.Constant;
import com.huutho.photo.models.Sticker;
import com.huutho.photo.models.StickerCategory;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ThoNh on 11/13/2017.
 */

@Module
public class StickerModule {

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
