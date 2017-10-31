package com.huutho.photo.main;

import com.arellomobile.mvp.MvpView;

import java.io.File;

/**
 * Created by ThoNh on 10/30/2017.
 */

public interface MainView extends MvpView {

    void openCamera(File file);

    void startGallery();

    void permissionDenied();

    void startEdit(String imagePath);

}
