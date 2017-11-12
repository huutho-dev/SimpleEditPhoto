package com.huutho.photo.edit.fragment.crop;

import android.graphics.Bitmap;
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
import com.huutho.photo.models.Crop;
import com.isseiaoki.simplecropview.CropImageView;

import org.wysaid.texUtils.TextureRendererMask;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class CropFragment extends BaseToolFragment {
    @BindView(R.id.crop_container)
    LinearLayout mCropContainer;

    @Inject
    List<Crop> mCrops;

    private Bitmap mBitmap;
    private CropImageView mCropImageView;


    public static CropFragment newInstance() {
        Bundle args = new Bundle();
        CropFragment fragment = new CropFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.editorComponent.inject(this);
        mBitmap = getBitmapSurfaceView();
        mCropImageView = ((EditActivity) getActivity()).mCropImageView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crop, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        for (int i = 0; i < mCrops.size(); i++) {
            final Crop crop = mCrops.get(i);
            CropItem mView = new CropItem(getContext(), crop);
            mCropContainer.addView(mView);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOptionClick(crop);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mCropImageView.setVisibility(View.VISIBLE);
        mCropImageView.setCropEnabled(true);
        mCropImageView.setImageBitmap(mBitmap);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mImageGLSurfaceView.setVisibility(View.GONE);

            }
        }, 150);


    }

    private void onOptionClick(Crop crop) {
        mCropImageView.setCropMode(crop.mCropMode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mImageGLSurfaceView.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCropImageView.setVisibility(View.GONE);
            }
        }, 100);

    }

    @Override
    public void onSave() {
        mCropImageView.setVisibility(View.GONE);
        Bitmap bitmap = mCropImageView.getCroppedBitmap();
        setBitmapSurfaceView(bitmap);
    }

    @Override
    public void onCancel() {

    }
}
