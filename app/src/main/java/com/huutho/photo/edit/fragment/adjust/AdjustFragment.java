package com.huutho.photo.edit.fragment.adjust;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.edit.EditActivity;
import com.huutho.photo.models.Adjust;

import org.wysaid.view.ImageGLSurfaceView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class AdjustFragment extends MvpAppCompatFragment implements AdjustView, SeekBar.OnSeekBarChangeListener {
    private static final String TAG = AdjustFragment.class.getSimpleName();

    @InjectPresenter
    AdjustPresenter mPresenter;

    @BindView(R.id.container_adjust)
    LinearLayout mAdjustToolLayout;

    ImageGLSurfaceView mImageView;
    Unbinder unbinder;

    private String mStringConfig;


    private SeekBar mSeekBar;
    private TextView mSeekValue;
    private TextView mReset;
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
        View view = inflater.inflate(R.layout.fragment_adjust, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mImageView = ((EditActivity) getActivity()).getImageView();

        View adjustView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_adjust, null);
        ((EditActivity) getActivity()).getAdjustLayout().addView(adjustView);

        mSeekValue = adjustView.findViewById(R.id.tv_value);
        mSeekBar = adjustView.findViewById(R.id.seek_bar);
        mReset = adjustView.findViewById(R.id.tv_reset);
        mSeekBar.setOnSeekBarChangeListener(this);

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekBar.setProgress(50);
            }
        });

    }


    @Override
    public void setupAdjustView(List<Adjust> adjustList, String config) {
        mImageView.setFilterWithConfig(config);
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

        // selected first adjust
        onAdjustClick(adjustList.get(0));
    }

    @Override
    public void onAdjustClick(Adjust adjust) {
        mCurrentAdjust = adjust;
        mSeekValue.setText(String.valueOf(mSeekBar.getProgress() * 2 - 100));
        mSeekBar.setProgress((int) (mCurrentAdjust.savePositionSeekBar * mSeekBar.getMax()));
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        mSeekValue.setText(String.valueOf(progress * 2 - 100));
        float intensity = mCurrentAdjust.calcIntensity(progress / 100.0f);
        mImageView.setFilterIntensityForIndex(intensity, mCurrentAdjust.indexConfig, true);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
