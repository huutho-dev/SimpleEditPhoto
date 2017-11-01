package com.huutho.photo.edit.fragment.sticker;

import com.arellomobile.mvp.MvpView;
import com.huutho.photo.models.StickerCategory;

import java.util.List;

/**
 * Created by ThoNh on 11/1/2017.
 */

public interface StickerView extends MvpView{
    void setUpStickerLst(List<StickerCategory> stickerCategories);

    void onCategoryClick(StickerCategory category);
}
