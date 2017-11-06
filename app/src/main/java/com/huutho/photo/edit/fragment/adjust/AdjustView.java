package com.huutho.photo.edit.fragment.adjust;

import com.arellomobile.mvp.MvpView;
import com.huutho.photo.models.Adjust;

import java.util.List;

/**
 * Created by ThoNh on 11/1/2017.
 */

public interface AdjustView extends MvpView {
    void setupAdjustView(List<Adjust> adjustList, String config);

    void onAdjustClick(Adjust adjust);
}
