package com.huutho.photo.edit.fragment.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.base.BaseToolFragment;
import com.huutho.photo.models.Frame;
import com.huutho.photo.utils.LogUtils;

import java.util.List;

import butterknife.BindView;

import static com.huutho.photo.gallery.fragment.gallery.images.GalleryImagesFragment.TAG;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class FrameFragment extends BaseToolFragment implements FrameView {

    @InjectPresenter
    FramePresenter mPresenter;

    @BindView(R.id.frame_container)
    LinearLayout mContainer;

    private String mConfig;


    public static FrameFragment newInstance() {
        Bundle args = new Bundle();
        FrameFragment fragment = new FrameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frame, container, false);
    }


    @Override
    public void onSave() {
        getActivity().onBackPressed();
        mBitmapManager.appendConfig(mConfig);
        mImageView.setFilterWithConfig(mBitmapManager.getResultConfig());
        LogUtils.e(TAG, "config--->" + mConfig + "\nresult:" + mBitmapManager.getResultConfig());
    }

    @Override
    public void onCancel() {
        mImageView.setFilterWithConfig(mBitmapManager.getResultConfig());
    }

    @Override
    public void setupFrameLst(List<Frame> lst) {
        for (int i = 0; i < lst.size(); i++) {
            final FrameItem item = new FrameItem(getContext());
            item.setData(lst.get(i));
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.onFrameClick(item.getData());
                }
            });
            mContainer.addView(item);
        }
    }

    @Override
    public void onFrameClick(final Frame frame) {
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mConfig = frame.mConfig;
                mImageView.setFilterWithConfig(mBitmapManager.getResultConfig() + frame.mConfig);
            }
        });
    }
}