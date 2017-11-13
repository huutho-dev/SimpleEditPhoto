package com.huutho.photo.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.huutho.photo.R;
import com.huutho.photo.edit.EditActivity;

import org.wysaid.nativePort.CGENativeLibrary;
import org.wysaid.view.ImageGLSurfaceView;

import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/9/2017.
 */

public abstract class BaseToolFragment extends MvpAppCompatFragment implements SeekBar.OnSeekBarChangeListener {

    public FrameLayout mContainerSeekbar;
    public ImageGLSurfaceView mImageGLSurfaceView;

    public SeekBar mSeekBar;
    public TextView mSeekValue;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageGLSurfaceView = ((EditActivity) getActivity()).getImageView();
        mContainerSeekbar = ((EditActivity) getActivity()).getContainerSeekBar();
        mContainerSeekbar.setVisibility(View.INVISIBLE);

        View adjustView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_adjust, null);
        mContainerSeekbar.addView(adjustView);

        mSeekValue = adjustView.findViewById(R.id.tv_value);
        mSeekBar = adjustView.findViewById(R.id.seek_bar);
        mSeekBar.setOnSeekBarChangeListener(this);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContainerSeekbar.removeAllViews();
        hideSeekBarLayout();
    }


    /**
     * Override when tool use SeekBar
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mSeekValue.setText(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void showSeekBarLayout() {
        if (mContainerSeekbar.getVisibility() == View.INVISIBLE
                || mContainerSeekbar.getVisibility() == View.GONE) {
            mContainerSeekbar.setVisibility(View.VISIBLE);
        }
    }

    public void hideSeekBarLayout() {
        if (mContainerSeekbar.getVisibility() == View.VISIBLE) {
            mContainerSeekbar.setVisibility(View.INVISIBLE);
        }
    }

    public Bitmap getBitmapSurfaceView() {
        return ((EditActivity) getActivity()).getBitmapSurface();
    }

    public void setBitmapSurfaceView(Bitmap bitmap) {
        ((EditActivity) getActivity()).setBitmapSurfaceView(bitmap);
    }

    /**
     * When click Save in toolbar
     */
    public abstract void onSave();


    /**
     * When click button back in toolbar
     */
    public abstract void onCancel();

    public Bitmap saveBitmap(String config, @FloatRange(from = 0.0f, to = 1.0f) float intensity) {
        return CGENativeLibrary.filterImage_MultipleEffects(getBitmapSurfaceView(), config, intensity);
    }
}
