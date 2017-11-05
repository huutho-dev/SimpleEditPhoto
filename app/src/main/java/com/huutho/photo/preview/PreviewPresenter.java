package com.huutho.photo.preview;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by NguyenHuuTho on 11/5/2017.
 */

@InjectViewState
public class PreviewPresenter extends MvpPresenter<PreviewView> {

    public PreviewPresenter(){
        getViewState().getImagesIntent();
        getViewState().setupToolbar();
        getViewState().setupPreviewPager();
    }
}
