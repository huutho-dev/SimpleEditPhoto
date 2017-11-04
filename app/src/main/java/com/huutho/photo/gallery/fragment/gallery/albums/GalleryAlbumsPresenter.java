package com.huutho.photo.gallery.fragment.gallery.albums;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.huutho.photo.App;
import com.huutho.photo.models.Image;
import com.huutho.photo.models.ImageAlbum;
import com.huutho.photo.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by NguyenHuuTho on 10/30/2017.
 */

@InjectViewState
public class GalleryAlbumsPresenter extends MvpPresenter<GalleryAlbumsView> {
    private static final String TAG = GalleryAlbumsPresenter.class.getSimpleName();


    public void fetchAlbums() {
        fetchImageDatabase();
    }


    private void fetchImageDatabase() {
        Observable.create(
                new ObservableOnSubscribe<List<ImageAlbum>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<ImageAlbum>> e) throws Exception {
                        e.onNext(queryDatabase());
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<List<ImageAlbum>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<ImageAlbum> imageAlbums) {
                                LogUtils.e(TAG, "onNext() --->size:" + imageAlbums.size());
                                if (imageAlbums.size() > 0) {
                                    getViewState().fetchImageSuccess(imageAlbums);
                                    return;
                                }
                                getViewState().fetchImageEmpty();
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                LogUtils.e(TAG, "onError(Throwable e)");
                                getViewState().fetchImageError();
                            }

                            @Override
                            public void onComplete() {
                                LogUtils.e(TAG, " onComplete()");
                                getViewState().fetchImageComplete();
                            }
                        });
    }

    private List<ImageAlbum> queryDatabase() {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String projection[] = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        Map<String, ImageAlbum> albumMap = new HashMap<>();

        Cursor cursor = App.getInstance()
                .getContentResolver()
                .query(uri, projection, null, null, MediaStore.Images.Media.DATE_ADDED);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int imgId = cursor.getInt(cursor.getColumnIndex(projection[0]));
                String imgPath = cursor.getString(cursor.getColumnIndex(projection[1]));
                String folderName = cursor.getString(cursor.getColumnIndex(projection[2]));

                Image image = new Image(imgId, imgPath);

                ImageAlbum album = albumMap.get(folderName);
                if (album == null) {
                    album = new ImageAlbum(folderName);
                    albumMap.put(folderName, album);
                }
                album.mImages.add(image);
            }
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return new ArrayList<>(albumMap.values());
    }
}
