package com.huutho.photo.edit.fragment.adjust;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.huutho.photo.App;
import com.huutho.photo.models.Adjust;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ThoNh on 11/1/2017.
 */

@InjectViewState
public class AdjustPresenter extends MvpPresenter<AdjustView> {

    @Inject
    List<Adjust> mAdjustList;

    public AdjustPresenter(){
        App.editorComponent.inject(this);
        getViewState().setupAdjustView(mAdjustList);
    }

    public void onAdjustClick(Adjust data) {
        getViewState().onAdjustClick(data);
    }
}
