package com.huutho.photo.edit.fragment.adjust;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.huutho.photo.App;
import com.huutho.photo.di.module.EditorModule;
import com.huutho.photo.models.Adjust;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by ThoNh on 11/1/2017.
 */

@InjectViewState
public class AdjustPresenter extends MvpPresenter<AdjustView> {

    @Inject
    Map<String, Object> mAdjustMap;

    public AdjustPresenter(){
        App.editorComponent.inject(this);
        getViewState().setupAdjustView(
                (List<Adjust>) mAdjustMap.get(EditorModule.KEY_ADJUST_TOOL),
                (String) mAdjustMap.get(EditorModule.KEY_ADJUST_CONFIG));
    }

}
