package com.huutho.photo.edit.fragment.overlay;

import com.arellomobile.mvp.MvpView;
import com.huutho.photo.models.Overlay;

import java.util.List;

/**
 * Created by ThoNh on 11/1/2017.
 */

public interface OverlayView extends MvpView {
    void setupOverlayView(List<Overlay> overlayList);

    void onOverlayOnClick(Overlay overlay);
}
