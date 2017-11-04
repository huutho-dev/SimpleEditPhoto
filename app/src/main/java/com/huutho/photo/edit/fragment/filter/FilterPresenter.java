package com.huutho.photo.edit.fragment.filter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by ThoNh on 11/1/2017.
 */

@InjectViewState
public class FilterPresenter extends MvpPresenter<FilterView> {

    public FilterPresenter(){

        getViewState().setupFilter();

    }
}
