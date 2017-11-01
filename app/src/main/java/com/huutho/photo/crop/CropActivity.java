package com.huutho.photo.crop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.App;
import com.huutho.photo.Constant;
import com.huutho.photo.R;
import com.huutho.photo.edit.EditActivity;
import com.huutho.photo.utils.BitmapUtils;
import com.huutho.photo.utils.ScreenUtils;
import com.isseiaoki.simplecropview.CropImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ThoNh on 10/31/2017.
 */

public class CropActivity extends MvpAppCompatActivity implements CropView {
    private static final String TAG = CropActivity.class.getSimpleName();
    public static final String EXTRA_IMAGE_PATH = "EXTRA_IMAGE_PATH";

    @InjectPresenter
    CropPresenter mPresenter;

    @BindView(R.id.cropImageView)
    CropImageView mCropImageView;

    private Bitmap mBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        ButterKnife.bind(this);

        String mImagePath = getIntent().getStringExtra(EXTRA_IMAGE_PATH);
        mBitmap = BitmapUtils.decodeFilePath(mImagePath, ScreenUtils.getScreenWidth());
        mCropImageView.setImageBitmap(mBitmap);
    }

    @OnClick({R.id.imv_back, R.id.imv_done, R.id.imv_flip_horizontal,
            R.id.imv_rotate_left, R.id.imv_rotate_right, R.id.imv_flip_vertical, R.id.imv_fit_image,
            R.id.imv_square, R.id.imv_free, R.id.imv_3_4, R.id.imv_4_3, R.id.imv_9_16, R.id.imv_16_9,
            R.id.imv_7_5, R.id.imv_fircle, R.id.imv_show_circle_but_crop_as_square})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_back:
                finish();
                break;

            case R.id.imv_flip_horizontal:
                mPresenter.fiipHorizontal(mBitmap);
                break;

            case R.id.imv_flip_vertical:
                mPresenter.fiipVertical(mBitmap);
                break;

            case R.id.imv_rotate_left:
                mCropImageView.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D);
                break;

            case R.id.imv_rotate_right:
                mCropImageView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
                break;

            case R.id.imv_fit_image:
                mCropImageView.setCropMode(CropImageView.CropMode.FIT_IMAGE);
                break;

            case R.id.imv_square:
                mCropImageView.setCropMode(CropImageView.CropMode.SQUARE);
                break;
            case R.id.imv_free:
                mCropImageView.setCropMode(CropImageView.CropMode.FREE);
                break;

            case R.id.imv_3_4:
                mCropImageView.setCropMode(CropImageView.CropMode.RATIO_3_4);
                break;

            case R.id.imv_4_3:
                mCropImageView.setCropMode(CropImageView.CropMode.RATIO_4_3);
                break;

            case R.id.imv_9_16:
                mCropImageView.setCropMode(CropImageView.CropMode.RATIO_9_16);
                break;
            case R.id.imv_16_9:
                mCropImageView.setCropMode(CropImageView.CropMode.RATIO_16_9);
                break;

            case R.id.imv_7_5:
                mCropImageView.setCropMode(CropImageView.CropMode.CUSTOM);
                break;

            case R.id.imv_fircle:
                mCropImageView.setCropMode(CropImageView.CropMode.CIRCLE);
                break;

            case R.id.imv_show_circle_but_crop_as_square:
                mCropImageView.setCropMode(CropImageView.CropMode.CIRCLE_SQUARE);
                break;

            case R.id.imv_done:
                Bitmap bitmapCrop = mCropImageView.getCroppedBitmap();
                App.getInstance().removeBitmapMemoryCache(Constant.KEY_CACHE_BITMAP_CROPPED);
                App.getInstance().saveBitmapToMemoryCache(Constant.KEY_CACHE_BITMAP_CROPPED, bitmapCrop);
                Intent intent = new Intent(this, EditActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void fliped(Bitmap bitmap, Matrix matrix) {
        // create new bitmap and remove old bitmap prevent OutOfMemory
        mBitmap = Bitmap.createBitmap(bitmap);
        mCropImageView.setImageBitmap(mBitmap);
        bitmap.recycle();
    }
}
