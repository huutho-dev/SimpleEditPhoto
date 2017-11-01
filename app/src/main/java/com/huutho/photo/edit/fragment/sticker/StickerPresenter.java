package com.huutho.photo.edit.fragment.sticker;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.huutho.photo.App;
import com.huutho.photo.models.StickerCategory;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ThoNh on 11/1/2017.
 */

@InjectViewState
public class StickerPresenter extends MvpPresenter<StickerView> {

    @Inject
    List<StickerCategory> mStickerCategories;

    public StickerPresenter(){
        App.editorComponent.inject(this);
        getViewState().setUpStickerLst(mStickerCategories);
    }

    public void onCategoryClick(StickerCategory category) {
        getViewState().onCategoryClick(category);
    }
}
