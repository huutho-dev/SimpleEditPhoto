package com.huutho.photo.gallery;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.gallery.fragment.albums.GalleryAlbumsFragment;
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

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();

        mFragmentManager
                .beginTransaction()
                .add(R.id.gallery_container,
                        GalleryAlbumsFragment.newInstance(),
                        GalleryAlbumsFragment.class.getSimpleName())
                .commit();

    }

    @Override
    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() == 1) {
            onBackPressed();
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

    }
}
