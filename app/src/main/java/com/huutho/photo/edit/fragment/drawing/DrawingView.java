package com.huutho.photo.edit.fragment.drawing;

import com.arellomobile.mvp.MvpView;
import com.huutho.photo.models.BrushSize;
import com.huutho.photo.models.EditorColor;

import java.util.List;

/**
 * Created by ThoNh on 11/1/2017.
 */

public interface DrawingView extends MvpView {

    void setupSizeView(List<BrushSize> sizes);

    void setupColorView(List<EditorColor> colors);

}
