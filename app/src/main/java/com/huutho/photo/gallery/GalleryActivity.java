package com.huutho.photo.gallery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.crop.CropActivity;
import com.huutho.photo.gallery.fragment.albums.GalleryAlbumsFragment;
import com.huutho.photo.gallery.fragment.images.GalleryImagesFragment;
import com.huutho.photo.models.Image;
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

        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setSubtitleTextColor(Color.LTGRAY);
        updateToolbar(R.string.gallery, R.drawable.ic_close, "");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager
                .beginTransaction()
                .replace(R.id.gallery_container, GalleryAlbumsFragment.newInstance())
                .commitAllowingStateLoss();

    }

    @Override
    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() == 1) {
            mFragmentManager.popBackStack();
            updateToolbar(R.string.gallery, R.drawable.ic_close, "");
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
        updateToolbar(R.string.gallery, R.drawable.ic_back, album.mName);
        mFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.hozirontal_enter, R.anim.horizontal_exit, R.anim.horizontal_pop_enter, R.anim.horizontal_pop_exit)
                .replace(R.id.gallery_container, GalleryImagesFragment.newInstance(album.mImages))
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    private void updateToolbar(int title, int icon, String subTitle) {
        mToolbar.setTitle(title);
        mToolbar.setNavigationIcon(icon);
        mToolbar.setSubtitle(subTitle);
    }

    /**
     * Call when click image in gallery
     *
     * @param image
     */
    public void startEdit(Image image) {
        finish();
        Intent intent = new Intent(this, CropActivity.class);
        intent.putExtra(CropActivity.EXTRA_IMAGE_PATH, image.mPath);
        startActivity(intent);
    }
}
