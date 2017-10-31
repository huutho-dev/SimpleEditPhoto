package com.huutho.photo.di;

import com.huutho.photo.di.module.EditorModule;
import com.huutho.photo.edit.fragment.ToolsFragment;

import dagger.Component;

/**
 * Created by NguyenHuuTho on 10/31/2017.
 */

@Component(modules = {EditorModule.class})
public interface EditorComponent {
    void inject (ToolsFragment fragment);
}
