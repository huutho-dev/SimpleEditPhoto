package com.huutho.photo.edit.fragment;

import com.arellomobile.mvp.MvpView;
import com.huutho.photo.models.Tool;

import java.util.List;

/**
 * Created by ThoNh on 11/1/2017.
 */

public interface ToolsView extends MvpView {
    void setupToolsView(List<Tool> toolData);

    void onToolClick(Tool tool);
}
