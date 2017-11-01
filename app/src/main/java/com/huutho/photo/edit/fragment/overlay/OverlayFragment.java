package com.huutho.photo.edit.fragment.overlay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.models.Overlay;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class OverlayFragment extends MvpAppCompatFragment implements OverlayView {

    @InjectPresenter
    OverlayPresenter mPresenter;

    @BindView(R.id.rv_overlay)
    RecyclerView mOverlayView;

    public static OverlayFragment newInstance() {
        Bundle args = new Bundle();
        OverlayFragment fragment = new OverlayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_overlay, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void setupOverlayView(List<Overlay> overlayList) {
        OverlayAdapter mAdapter = new OverlayAdapter();
        mOverlayView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mOverlayView.setAdapter(mAdapter);
        mAdapter.setData(overlayList);
        mAdapter.setListener(new OverlayAdapter.OverlayEventListener() {
            @Override
            public void onClick(View view, int position, Overlay overlay) {
                mPresenter.onOverlayOnClick(overlay);
            }
        });
    }

    @Override
    public void onOverlayOnClick(Overlay overlay) {

    }
}
