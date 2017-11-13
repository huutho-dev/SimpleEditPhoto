package com.huutho.photo.edit.fragment.rotate;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.huutho.photo.App;
import com.huutho.photo.R;
import com.huutho.photo.base.BaseToolFragment;
import com.huutho.photo.edit.EditActivity;
import com.huutho.photo.models.Rotate;
import com.isseiaoki.simplecropview.CropImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class RotateFragment extends BaseToolFragment {
    @BindView(R.id.rotate_container)
    LinearLayout mContainer;

    @Inject
    List<Rotate> mRotates;

    private CropImageView mCropImageView;
    private Bitmap mBitmap;


    public static RotateFragment newInstance() {
        Bundle args = new Bundle();
        RotateFragment fragment = new RotateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.editorComponent.inject(this);

        mCropImageView = ((EditActivity) getActivity()).mCropImageView;
        mBitmap = Bitmap.createBitmap(getBitmapSurfaceView());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rotate, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        for (int i = 0; i < mRotates.size(); i++) {
            final Rotate rotate = mRotates.get(i);
            RotateItem mView = new RotateItem(getContext(), rotate);
            mContainer.addView(mView);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRotateClick(rotate);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mCropImageView.setVisibility(View.VISIBLE);
        mCropImageView.setImageBitmap(mBitmap);
        mCropImageView.setCropEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mImageGLSurfaceView.setVisibility(View.GONE);

            }
        }, 150);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSave() {
        Bitmap bitmap = mCropImageView.getCroppedBitmap();
        setBitmapSurfaceView(bitmap);
        mCropImageView.setVisibility(View.GONE);
    }

    @Override
    public void onCancel() {

    }

    private void onRotateClick(Rotate rotate) {
        switch (rotate.mRotateMode) {
            case Rotate.RotateMode.ROTATE_LEFT:
                mCropImageView.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D);
                break;
            case Rotate.RotateMode.ROTATE_RIGHT:
                mCropImageView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
                break;
            case Rotate.RotateMode.FLIP_HORIZONTAL:
                flipHorizontal(mCropImageView.getCroppedBitmap());
                break;
            case Rotate.RotateMode.FLIP_VERTICAL:
                flipVertical(mCropImageView.getCroppedBitmap());
                break;
        }
    }

    public void flipHorizontal(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        // create new bitmap and remove old bitmap prevent OutOfMemory
//        mBitmap = Bitmap.createBitmap(bitmap);
//        mCropImageView.setImageBitmap(mBitmap);
//        bitmap.recycle();

        mCropImageView.setImageBitmap(newBitmap);
    }

    public void flipVertical(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.setScale(1, -1, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        // create new bitmap and remove old bitmap prevent OutOfMemory
//        mBitmap = Bitmap.createBitmap(bitmap);
//        mCropImageView.setImageBitmap(mBitmap);
//        bitmap.recycle();

        mCropImageView.setImageBitmap(newBitmap);
    }
}
