package com.huutho.photo.models;

import android.support.v4.app.Fragment;

/**
 * Created by NguyenHuuTho on 10/31/2017.
 */

public class Tool {
    public String name;
    public int icon;
    public Fragment child;

    public Tool(String name, int icon, Fragment child) {
        this.name = name;
        this.icon = icon;
        this.child = child;
    }
}
