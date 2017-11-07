package com.huutho.photo.edit.fragment.filter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.App;
import com.huutho.photo.Constant;
import com.huutho.photo.R;
import com.huutho.photo.models.Filter;

import org.wysaid.view.ImageGLSurfaceView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class FilterFragment extends MvpAppCompatFragment implements FilterView, FilterAdapter.FilterEventListener {

    @InjectPresenter
    FilterPresenter mPresenter;

    @Inject
    List<Filter> mFilterList;

    @BindView(R.id.rv_filters)
    RecyclerView mFiltersView;

    @BindView(R.id.image_filter)
    ImageGLSurfaceView mImageView;

    @BindView(R.id.seek_bar)
    SeekBar mSeekBar;

    private FilterAdapter mAdapter;

    private Bitmap mBitmap;
    private String mCurrentFilter;

    public static FilterFragment newInstance() {
        Bundle args = new Bundle();
        FilterFragment fragment = new FilterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.editorComponent.inject(this);
        mBitmap = App.getInstance()
                .getBitmapFromMemoryCache(Constant.KEY_CACHE_BITMAP_CROPPED);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mImageView.setDisplayMode(ImageGLSurfaceView.DisplayMode.DISPLAY_ASPECT_FIT);
        mImageView.setSurfaceCreatedCallback(new ImageGLSurfaceView.OnSurfaceCreatedCallback() {
            @Override
            public void surfaceCreated() {
                mImageView.setImageBitmap(mBitmap);
            }
        });


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void setupFilter() {

        mAdapter = new FilterAdapter(mBitmap);
        mAdapter.setListener(this);
        mFiltersView.setLayoutManager(new LinearLayoutManager
                (getContext(), LinearLayoutManager.HORIZONTAL, false));
        mFiltersView.setAdapter(mAdapter);
        mAdapter.setFilterList(mFilterList);
    }

    @Override
    public void onClick(View view, int position, Filter filter) {
        mImageView.setFilterWithConfig(filter.filter);
        mSeekBar.setProgress(50);
    }
}
