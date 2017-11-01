package com.huutho.photo.edit.fragment.overlay;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.huutho.photo.App;
import com.huutho.photo.models.Overlay;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ThoNh on 11/1/2017.
 */

@InjectViewState
public class OverlayPresenter extends MvpPresenter<OverlayView> {

    @Inject
    List<Overlay> mOverlayList;

    public OverlayPresenter(){
        App.editorComponent.inject(this);
        getViewState().setupOverlayView(mOverlayList);
    }

    public void onOverlayOnClick(Overlay overlay) {
        getViewState().onOverlayOnClick(overlay);
    }
}
