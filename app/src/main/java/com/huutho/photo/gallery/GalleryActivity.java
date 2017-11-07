package com.huutho.photo.gallery;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.gallery.fragment.gallery.images.GalleryImagesFragment;
import com.huutho.photo.models.ImageAlbum;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NguyenHuuTho on 10/30/2017.
 */

public class GalleryActivity extends MvpAppCompatActivity implements GalleryView {
    private static final String TAG = GalleryActivity.class.getSimpleName();

    @InjectPresenter
    GalleryPresenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    public void setUpToolbar() {
        setSupportActionBar(mToolbar);
        updateToolbar(R.string.gallery, "");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void setupPagerAndTab() {
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        adapter.setIconTab(mTabLayout);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() > 0) {
            mFragmentManager.popBackStack();
            updateToolbar(R.string.gallery, "");
        } else {
            finish();
        }
    }

    /**
     * Call when click to album
     *
     * @param album album clicked
     */
    public void openAlbum(ImageAlbum album) {
        updateToolbar(R.string.gallery, album.mName);
        mFragmentManager
                .beginTransaction()
                .replace(R.id.root, GalleryImagesFragment.newInstance(album))
                .addToBackStack("ádasd")
                .commitAllowingStateLoss();
    }

    private void updateToolbar(int title, String subTitle) {
        mToolbar.setTitle(title);
        mToolbar.setSubtitle(subTitle);
    }
}
