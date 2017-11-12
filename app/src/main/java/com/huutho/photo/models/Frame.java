package com.huutho.photo.models;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class Frame {
    public String mFramePath;
    public String mDescription;
    public String mConfig;

    public Frame(String frame, String description) {
        mFramePath = frame;
        mDescription = description;

        int dashIndex = mFramePath.lastIndexOf("/");
        String name = mFramePath.substring(dashIndex + 1);
        mConfig = "@blend mix frame/frame/" + name + " 100 ";
    }
}
