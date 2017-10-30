package com.huutho.photo.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private static final int REQ_CAMERA = 0x0001;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_OK)
            switch (requestCode) {
                case REQ_CAMERA:
                    mPresenter.captureImageResult();
                    break;
            }
    }

    @Override
    public void openCamera(File fileToSave) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoUri = FileProvider.getUriForFile(this, "com.huutho.photo.fileprovider",fileToSave);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, REQ_CAMERA);
        }
        Log.e("ThoNH","PathImage:"+fileToSave);
    }

    @Override
    public void startGallery() {

    }

    @Override
    public void permissionDenied() {

    }

    @Override
    public void startEdit(String imagePath) {

    }
}
