package com.huutho.photo.models;

import java.util.List;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class StickerCategory {

    public String iconCat;

    public List<Sticker> mStickers;

    public StickerCategory(String iconCat, List<Sticker> stickers) {
        this.iconCat = iconCat;
        mStickers = stickers;
    }
}
