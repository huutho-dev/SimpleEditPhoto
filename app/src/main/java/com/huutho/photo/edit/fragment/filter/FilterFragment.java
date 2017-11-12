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

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.App;
import com.huutho.photo.R;
import com.huutho.photo.base.BaseToolFragment;
import com.huutho.photo.edit.EditActivity;
import com.huutho.photo.models.Filter;
import com.huutho.photo.utils.ScreenUtils;

import org.wysaid.view.ImageGLSurfaceView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class FilterFragment extends BaseToolFragment implements FilterView, FilterAdapter.FilterEventListener {

    @InjectPresenter
    FilterPresenter mPresenter;

    @Inject
    List<Filter> mFilterList;

    @BindView(R.id.rv_filters)
    RecyclerView mFiltersView;

    private String mConfig;
    private float mIntensity;


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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void setupFilter() {

        // TODO: 11/12/2017 Replace bitmap by image res --> prevent lag
        Bitmap bitmapResized = Bitmap.createScaledBitmap(getBitmapSurfaceView(),
                (int) ScreenUtils.convertDpToPixel(50),
                (int) ScreenUtils.convertDpToPixel(50), true);

        FilterAdapter mAdapter = new FilterAdapter(bitmapResized);
        mAdapter.setListener(this);
        mFiltersView.setLayoutManager(new LinearLayoutManager
                (getContext(), LinearLayoutManager.HORIZONTAL, false));
        mFiltersView.setAdapter(mAdapter);
        mAdapter.setFilterList(mFilterList);
    }


    @Override
    public void onClick(View view, int position, Filter filter) {
        showSeekBarLayout();
        mConfig = filter.filter;
        mImageGLSurfaceView.setFilterWithConfig(filter.filter);
        mSeekBar.setProgress(100);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        super.onProgressChanged(seekBar, progress, fromUser);
        mIntensity = progress/100.0f;
        mImageGLSurfaceView.setFilterIntensity(mIntensity);
    }

    @Override
    public void onSave() {
        Bitmap bitmap = saveBitmap(mConfig, mIntensity);
        setBitmapSurfaceView(bitmap);
    }

    @Override
    public void onCancel() {
        Bitmap bitmap = saveBitmap("", 0.0f);
        setBitmapSurfaceView(bitmap);
    }
}
