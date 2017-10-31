package com.huutho.photo.edit;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.App;
import com.huutho.photo.Constant;
import com.huutho.photo.R;
import com.huutho.photo.edit.fragment.ToolsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 10/31/2017.
 */

public class EditActivity extends MvpAppCompatActivity implements EditView {

    @InjectPresenter
    EditPresenter mPresenter;

    @BindView(R.id.image)
    ImageView mImageView;

    @BindView(R.id.imv_nav)
    ImageView mNavigationIcon;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        Bitmap bitmap = App.getInstance()
                .getBitmapFromMemoryCache(Constant.KEY_CACHE_BITMAP_CROPPED);

        mImageView.setImageBitmap(bitmap);

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager
                .beginTransaction()
                .replace(R.id.bottom_container, ToolsFragment.newInstance())
                .commitAllowingStateLoss();
    }
}
