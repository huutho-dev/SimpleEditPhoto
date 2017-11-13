package com.huutho.photo.di.module;

import com.huutho.photo.models.Filter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ThoNh on 11/13/2017.
 */

@Module
public class FilterModule {

    @Provides
    List<Filter> provideFilter() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("@adjust lut filter/edgy_amber.png"));
        filters.add(new Filter("@adjust lut filter/filmstock.png"));
        filters.add(new Filter("@adjust lut filter/foggy_night.png"));
        filters.add(new Filter("@adjust lut filter/hehe.png"));
        filters.add(new Filter("@adjust lut filter/soft_warming.png"));
        filters.add(new Filter("@adjust lut filter/wildbird.png"));
        filters.add(new Filter("@curve RGB(0,255)(255,0) @style cm filter/mapping0.jpg 80 80 8 3"));
        return filters;
    }
}
