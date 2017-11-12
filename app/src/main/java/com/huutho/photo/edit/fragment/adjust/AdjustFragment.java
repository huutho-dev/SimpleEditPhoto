package com.huutho.photo.edit.fragment.adjust;

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

import java.util.List;

import butterknife.BindView;

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
        Log.e("ThoNH","fuck:" + config);
        mImageGLSurfaceView.setFilterWithConfig("@adjust sharpen 0");
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

//        // selected first adjust
        onAdjustClick(adjustList.get(0));
    }

    @Override
    public void onAdjustClick(Adjust adjust) {
        showSeekBarLayout();
        mCurrentAdjust = adjust;
        mSeekValue.setText(String.valueOf(mSeekBar.getProgress() * 2 - 100));
        mSeekBar.setProgress((int) (mCurrentAdjust.savePositionSeekBar * mSeekBar.getMax()));
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mSeekValue.setText(String.valueOf(progress * 2 - 100));
        float intensity = mCurrentAdjust.calcIntensity(progress / 100.0f);
        Log.e("ThoNH","intensity:" + intensity);
        mImageGLSurfaceView.setFilterIntensityForIndex(progress/10f,0, true);


    }


    @Override
    public void onSave() {
    }

    @Override
    public void onCancel() {

    }
}
