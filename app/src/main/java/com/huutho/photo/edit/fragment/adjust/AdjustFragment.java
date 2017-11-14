package com.huutho.photo.edit.fragment.adjust;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.base.BaseToolFragment;
import com.huutho.photo.models.Adjust;

import org.wysaid.nativePort.CGENativeLibrary;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class AdjustFragment extends BaseToolFragment implements AdjustView, SeekBar.OnSeekBarChangeListener {
    private static final String TAG = AdjustFragment.class.getSimpleName();

    @InjectPresenter
    AdjustPresenter mPresenter;

    @BindView(R.id.container_adjust)
    LinearLayout mAdjustToolLayout;

    private Adjust mCurrentAdjust;
    private List<Adjust> mAdjustList;


    public static AdjustFragment newInstance() {
        Bundle args = new Bundle();
        AdjustFragment fragment = new AdjustFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_adjust, container, false);
    }


    @Override
    public void setupAdjustView(List<Adjust> adjustList, String config) {
        mAdjustList = adjustList;
        mImageGLSurfaceView.setFilterWithConfig(config);
        for (int i = 0; i < adjustList.size(); i++) {
            final AdjustItem adjustItem = new AdjustItem(getContext());
            adjustItem.setData(adjustList.get(i));
            mAdjustToolLayout.addView(adjustItem);
            adjustItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAdjustClick(adjustItem.getData());
                }
            });
        }

    }

    @Override
    public void onAdjustClick(Adjust adjust) {
        showSeekBarLayout();
        mCurrentAdjust = adjust;
        mSeekValue.setText(String.valueOf(mSeekBar.getProgress() * 2 - 100));
        mSeekBar.setProgress((int) (mCurrentAdjust.savePositionSeekBar * mSeekBar.getMax()));
        Log.e(TAG, "onAdjustClick: " + adjust.toString());
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mSeekValue.setText(String.valueOf(progress * 2 - 100));
        float intensity = mCurrentAdjust.calcIntensity(progress / 100.0f);
        mImageGLSurfaceView.setFilterIntensityForIndex(intensity, mCurrentAdjust.indexConfig, true);
    }

    @Override
    public void onReset() {
        super.onReset();
        mImageGLSurfaceView.setFilterIntensityForIndex(0, mCurrentAdjust.indexConfig, true);
    }

    @Override
    public void onSave() {
        Bitmap bitmap = Bitmap.createBitmap(getBitmapSurfaceView());
        String mConfig =
                "@adjust sharpen " + mAdjustList.get(0).mCurrentValue +
                        " @adjust brightness " + mAdjustList.get(1).mCurrentValue +
                        " @adjust contrast " + mAdjustList.get(2).mCurrentValue +
                        " @vignette 1 0.15 " +
                        " @adjust hue 0 " + mAdjustList.get(4).mCurrentValue +
                        " @adjust saturation " + mAdjustList.get(5).mCurrentValue;

        Log.e("ThoNH","mConfig:" + mConfig);
        bitmap = CGENativeLibrary.filterImage_MultipleEffects(bitmap, mConfig, 1.0f);
        setBitmapSurfaceView(bitmap);
    }

    @Override
    public void onCancel() {
        Bitmap bitmap = CGENativeLibrary.filterImage_MultipleEffects(getBitmapSurfaceView(), "", 1.0f);
        setBitmapSurfaceView(bitmap);
    }

}
