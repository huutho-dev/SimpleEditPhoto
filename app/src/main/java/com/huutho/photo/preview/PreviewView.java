package com.huutho.photo.preview;

import com.arellomobile.mvp.MvpView;

/**
 * Created by NguyenHuuTho on 11/5/2017.
 */

interface PreviewView extends MvpView{
    void getImagesIntent();

    void setupPreviewPager();

    void setupToolbar();
}
