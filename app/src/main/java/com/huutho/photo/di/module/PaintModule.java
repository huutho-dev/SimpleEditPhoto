package com.huutho.photo.di.module;

import com.huutho.photo.models.BrushSize;
import com.huutho.photo.models.EditorColor;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ThoNh on 11/13/2017.
 */

@Module
public class PaintModule {

    @Provides
    List<BrushSize> provideBrushSize() {
        List<BrushSize> sizes = new ArrayList<>();
        sizes.add(new BrushSize(10));
        sizes.add(new BrushSize(12));
        sizes.add(new BrushSize(14));
        sizes.add(new BrushSize(18));
        sizes.add(new BrushSize(20));
        sizes.add(new BrushSize(22));
        sizes.add(new BrushSize(24));
        sizes.add(new BrushSize(26));
        sizes.add(new BrushSize(28));
        sizes.add(new BrushSize(30));
        sizes.add(new BrushSize(33));
        sizes.add(new BrushSize(36));
        sizes.add(new BrushSize(39));
        sizes.add(new BrushSize(42));
        sizes.add(new BrushSize(45));
        sizes.add(new BrushSize(50));
        return sizes;
    }

    @Provides
    List<EditorColor> provideEditorColor() {
        return EditorColor.getColorsList();
    }

}
