package com.huutho.photo.models;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenHuuTho on 10/30/2017.
 */

public class ImageAlbum {

    public String mName;

    public List<Image> mImages;

    public ImageAlbum(String name) {
        mName = name;
        mImages = new ArrayList<>();
    }
}
