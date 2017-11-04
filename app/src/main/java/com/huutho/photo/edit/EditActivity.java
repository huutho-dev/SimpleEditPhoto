package com.huutho.photo.edit;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.App;
import com.huutho.photo.Constant;
import com.huutho.photo.R;
import com.huutho.photo.edit.fragment.ToolsFragment;
import com.huutho.photo.models.Tool;

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

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.adjust_container)
    FrameLayout mAdjustContainer;

    private Bitmap mBitmap;


    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setSubtitleTextColor(Color.LTGRAY);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mBitmap = App.getInstance()
                .getBitmapFromMemoryCache(Constant.KEY_CACHE_BITMAP_CROPPED);

        mImageView.setImageBitmap(mBitmap);

        mFragmentManager = getSupportFragmentManager();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.bottom_container, ToolsFragment.newInstance())
                .commitAllowingStateLoss();
    }

    public void updateToolbar(String title, int icon) {
        mToolbar.setTitle(title);
        mToolbar.setNavigationIcon(icon);
    }

    public ImageView getImageEditor() {
        return mImageView;
    }

    // Call when tool click on ToolsFragment.class
    @Override
    public void openTool(Tool tool) {
        updateToolbar(tool.name, R.drawable.ic_back);

        if (!tool.name.equals("Filters")){
            mFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.vertical_enter, R.anim.vertical_exit, R.anim.vertical_pop_enter, R.anim.vertical_pop_exit)
                    .replace(R.id.bottom_container, tool.child)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }else {
            mFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.vertical_enter, R.anim.vertical_exit, R.anim.vertical_pop_enter, R.anim.vertical_pop_exit)
                    .replace(R.id.adjust_container, tool.child)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackPressed() {

        Log.e("cc", "size:" + mFragmentManager.getBackStackEntryCount());
        if (mFragmentManager.getBackStackEntryCount() > 0) {
            mFragmentManager.popBackStack();
        } else {
            finish();
        }
    }

    public FrameLayout getAdjustContainer(){
        return mAdjustContainer;
    }
}
