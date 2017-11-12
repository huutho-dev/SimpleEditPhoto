package com.huutho.photo.edit.fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.huutho.photo.App;
import com.huutho.photo.models.Tool;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ThoNh on 11/1/2017.
 */

@InjectViewState
public class ToolsPresenter extends MvpPresenter<ToolsView> {

    @Inject
    List<Tool> mToolData;

    public ToolsPresenter(){
        App.editorComponent.inject(this);
        getViewState().setupToolsView(mToolData);
    }

}
