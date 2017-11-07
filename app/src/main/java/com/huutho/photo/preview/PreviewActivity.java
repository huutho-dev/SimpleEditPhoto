package com.huutho.photo.preview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.edit.EditActivity;
import com.huutho.photo.models.Image;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by NguyenHuuTho on 11/5/2017.
 */

public class PreviewActivity extends MvpAppCompatActivity implements PreviewView {
    private static final String EXTRA_IMAGES = "EXTRA_IMAGES";
    private static final String EXTRA_POSITION = "EXTRA_POSITION";

    public static void startActivity(Context activity, List<Image> images, int position) {
        Intent intent = new Intent(activity, PreviewActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_IMAGES, (ArrayList<? extends Parcelable>) images);
        intent.putExtra(EXTRA_POSITION, position);
        activity.startActivity(intent);
    }

    @InjectPresenter
    PreviewPresenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private List<Image> mImages;
    private int mCurrentPosition;

    private PreviewPagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ButterKnife.bind(this);
    }

    @Override
    public void getImagesIntent() {
        Intent intent = getIntent();
        mImages = intent.getParcelableArrayListExtra(EXTRA_IMAGES);
        mCurrentPosition = intent.getIntExtra(EXTRA_POSITION, 0);
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setTitle(R.string.preview_activity);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void setupPreviewPager() {
        mPagerAdapter = new PreviewPagerAdapter(this, mImages);
        mViewpager.setAdapter(mPagerAdapter);
        mViewpager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewpager.setCurrentItem(mCurrentPosition);
    }


    @OnClick({R.id.imv_delete, R.id.imv_filter, R.id.imv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_delete:
                break;
            case R.id.imv_filter:
                Image image = mImages.get(mViewpager.getCurrentItem());
                EditActivity.newInstance(this,image.mPath);
                finish();
                break;
            case R.id.imv_share:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}
