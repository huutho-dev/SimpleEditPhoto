package com.huutho.photo.edit.fragment.drawing;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.huutho.photo.App;
import com.huutho.photo.models.BrushSize;
import com.huutho.photo.models.EditorColor;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ThoNh on 11/1/2017.
 */

@InjectViewState
public class DrawingPresenter extends MvpPresenter<DrawingView>{

    @Inject
    List<BrushSize> mBrushSizes;

    @Inject
    List<EditorColor> mBrushColors;

    public DrawingPresenter(){
        App.editorComponent.inject(this);
        getViewState().setupSizeView(mBrushSizes);
        getViewState().setupColorView(mBrushColors);
    }

}
