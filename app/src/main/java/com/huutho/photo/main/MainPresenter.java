package com.huutho.photo.main;

import android.Manifest;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.huutho.photo.App;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ThoNh on 10/30/2017.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private String mImagePath;

    /**
     * Request permission for open camera
     *
     * @param context
     */
    public void requestOpenCamera(AppCompatActivity context) {
        requestPermission(context, Manifest.permission.CAMERA);
    }

    /**
     * Request permission to read image from internal storage
     *
     * @param context
     */
    public void requestOpenGallery(AppCompatActivity context) {
        requestPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    /**
     * Capture photo from camera success
     * Start Edit image
     */
    public void captureImageResult() {
        getViewState().startEdit(mImagePath);
    }

    //========================================PRIVATE FUN==========================================
    private void requestPermission(AppCompatActivity context, final String permission) {
        new RxPermissions(context)
                .request(permission)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {  // GRANTED

                            switch (permission) {
                                case Manifest.permission.CAMERA:
                                    try {
                                        mImagePath = createImageFile().getAbsolutePath();
                                        getViewState().openCamera(createImageFile());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;

                                case Manifest.permission.READ_EXTERNAL_STORAGE:
                                    getViewState().startGallery();
                                    break;
                            }

                        } else { // DENIED
                            getViewState().permissionDenied();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().permissionDenied();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = App.getInstance().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        return image;
    }
}
