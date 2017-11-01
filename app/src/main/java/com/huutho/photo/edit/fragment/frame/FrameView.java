package com.huutho.photo.edit.fragment.frame;

import com.arellomobile.mvp.MvpView;
import com.huutho.photo.models.Frame;

import java.util.List;

/**
 * Created by ThoNh on 11/1/2017.
 */

public interface FrameView extends MvpView {
    void setupFrameLst(List<Frame> frameList);

    void onFrameClick(Frame frame);
}
