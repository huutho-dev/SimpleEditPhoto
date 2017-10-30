package com.huutho.photo.gallery.fragment.albums;

import com.arellomobile.mvp.MvpView;
import com.huutho.photo.models.ImageAlbum;

import java.util.List;

/**
 * Created by NguyenHuuTho on 10/30/2017.
 */

public interface GalleryAlbumsView extends MvpView{

    void fetchImageSuccess(List<ImageAlbum> imageAlbums);

    void fetchImageComplete();

    void fetchImageError();

    void fetchImageEmpty();
}
