package com.huutho.photo.edit.fragment.frame;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.huutho.photo.App;
import com.huutho.photo.models.Frame;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ThoNh on 11/1/2017.
 */

@InjectViewState
public class FramePresenter extends MvpPresenter<FrameView> {

    @Inject
    List<Frame> mFrameList;

    public FramePresenter(){
        App.editorComponent.inject(this);
        getViewState().setupFrameLst(mFrameList);
    }

    public void onFrameClick(Frame data) {
        getViewState().onFrameClick(data);
    }
}
