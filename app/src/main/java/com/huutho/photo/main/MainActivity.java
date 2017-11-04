package com.huutho.photo.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huutho.photo.R;
import com.huutho.photo.crop.CropActivity;
import com.huutho.photo.gallery.GalleryActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQ_CAMERA = 0x0001;

    @BindView(R.id.root)
    ConstraintLayout root;

    @BindView(R.id.imv_camera)
    ImageView mImvCamera;

    @BindView(R.id.imv_gallery)
    ImageView mImvGallery;

    @InjectPresenter
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void setBackGroundImage(String pathAssets) {
        Glide.with(this).load(pathAssets).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                root.setBackground(resource);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
            switch (requestCode) {
                case REQ_CAMERA:
                    mPresenter.captureImageResult();
                    break;
            }
    }


    @OnClick({R.id.imv_camera, R.id.imv_gallery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_camera:
                mPresenter.requestOpenCamera(this);
                break;
            case R.id.imv_gallery:
                mPresenter.requestOpenGallery(this);
                break;
        }
    }

    @Override
    public void openCamera(File file) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoUri = FileProvider.getUriForFile(this, "com.huutho.photo.provider", file);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, REQ_CAMERA);
        }
    }

    @Override
    public void startGallery() {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }

    @Override
    public void permissionDenied() {

    }

    @Override
    public void startEdit(String imagePath) {
        Intent intent = new Intent(this, CropActivity.class);
        intent.putExtra(CropActivity.EXTRA_IMAGE_PATH, imagePath);
        startActivity(intent);
    }
}
