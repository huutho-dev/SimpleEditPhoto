package com.huutho.photo.gallery;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by NguyenHuuTho on 10/30/2017.
 */

@InjectViewState
public class GalleryPresenter extends MvpPresenter<GalleryView> {

    public GalleryPresenter(){
        getViewState().setUpToolbar();
        getViewState().setupPagerAndTab();
    }
}
