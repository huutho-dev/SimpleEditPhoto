package com.huutho.photo.edit.fragment.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.models.Frame;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class FrameFragment extends MvpAppCompatFragment implements FrameView {

    @InjectPresenter
    FramePresenter mPresenter;

    @BindView(R.id.frame_container)
    LinearLayout mContainer;


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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
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
    public void onFrameClick(Frame frame) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage("Show Sticker Here");
        alertDialog.show();
    }
}