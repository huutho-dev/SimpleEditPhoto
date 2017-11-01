package com.huutho.photo.di;

import com.huutho.photo.di.module.EditorModule;
import com.huutho.photo.edit.fragment.ToolsPresenter;
import com.huutho.photo.edit.fragment.adjust.AdjustPresenter;
import com.huutho.photo.edit.fragment.drawing.DrawingPresenter;
import com.huutho.photo.edit.fragment.frame.FramePresenter;
import com.huutho.photo.edit.fragment.overlay.OverlayPresenter;
import com.huutho.photo.edit.fragment.sticker.StickerPresenter;

import dagger.Component;

/**
 * Created by NguyenHuuTho on 10/31/2017.
 */

@Component(modules = {EditorModule.class})
public interface EditorComponent {

    void inject(ToolsPresenter presenter);

    void inject(DrawingPresenter presenter);

    void inject(StickerPresenter presenter);

    void inject(AdjustPresenter presenter);

    void inject(FramePresenter presenter);

    void inject(OverlayPresenter presenter);

}
