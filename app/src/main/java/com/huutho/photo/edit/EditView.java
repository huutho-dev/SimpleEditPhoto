package com.huutho.photo.edit;

import com.arellomobile.mvp.MvpView;
import com.huutho.photo.models.Tool;

/**
 * Created by ThoNh on 10/31/2017.
 */

public interface EditView extends MvpView {

    void getBitmapFromPath();

    void updateToolbar(String title, int icon);

    void openTool(Tool tool);


}
