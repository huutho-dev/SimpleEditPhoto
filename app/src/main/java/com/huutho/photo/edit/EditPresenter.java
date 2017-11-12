package com.huutho.photo.edit;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by ThoNh on 10/31/2017.
 */

@InjectViewState
public class EditPresenter extends MvpPresenter<EditView> {

    public EditPresenter(){

        getViewState().getBitmapFromPath();

        getViewState().getBitmapFromPath();
    }

}
