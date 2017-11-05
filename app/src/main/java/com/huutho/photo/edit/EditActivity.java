package com.huutho.photo.edit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.App;
import com.huutho.photo.Constant;
import com.huutho.photo.R;
import com.huutho.photo.edit.fragment.ToolsFragment;
import com.huutho.photo.models.Tool;
import com.huutho.photo.utils.LogUtils;
import com.huutho.photo.utils.ScreenUtils;

import org.wysaid.view.ImageGLSurfaceView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dontcare.utils.BitmapUtils;

/**
 * Created by ThoNh on 10/31/2017.
 */

public class EditActivity extends MvpAppCompatActivity implements EditView {
    private static final String TAG = EditActivity.class.getSimpleName();
    private static final String EXTRA_IMAGE_PATH = "EXTRA_IMAGE_PATH";

    public static void newInstance(Activity activity, String pathImage) {
        Intent intent = new Intent(activity, EditActivity.class);
        intent.putExtra(EXTRA_IMAGE_PATH, pathImage);
        activity.startActivity(intent);
    }

    @InjectPresenter
    EditPresenter mPresenter;

    @BindView(R.id.image)
    ImageGLSurfaceView mImageView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.adjust_container)
    FrameLayout mAdjustContainer;

    private Bitmap mBitmap;
    private FragmentManager mFragmentManager;

    @Override
    public void getBitmapFromPath() {
        String imagePath = getIntent().getStringExtra(EXTRA_IMAGE_PATH);
        if (imagePath != null) {
            mBitmap = BitmapUtils.resizeBitmap(imagePath, ScreenUtils.getScreenWidth());
            LogUtils.e(TAG, "getBitmapFromPath() --> path:" + imagePath);
            LogUtils.logBitmap(TAG, mBitmap);
        } else {
            Toast.makeText(
                    this,
                    "EditActivity --> getBitmapFromPath --> imagePath = null",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        mImageView.setZOrderOnTop(true);
        mImageView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        mImageView.setSurfaceCreatedCallback(new ImageGLSurfaceView.OnSurfaceCreatedCallback() {
            @Override
            public void surfaceCreated() {
                mImageView.setDisplayMode(ImageGLSurfaceView.DisplayMode.DISPLAY_ASPECT_FIT);
                mImageView.setImageBitmap(mBitmap);
            }
        });


        mFragmentManager = getSupportFragmentManager();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.bottom_container, ToolsFragment.newInstance())
                .commitAllowingStateLoss();
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setSubtitleTextColor(Color.LTGRAY);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void updateToolbar(String title, int icon) {
        mToolbar.setTitle(title);
        mToolbar.setNavigationIcon(icon);
    }


    // Call when tool click on ToolsFragment.class
    @Override
    public void openTool(Tool tool) {
        updateToolbar(tool.name, R.drawable.ic_back);

        if (!tool.name.equals("Filters")) {
            mFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.vertical_enter, R.anim.vertical_exit, R.anim.vertical_pop_enter, R.anim.vertical_pop_exit)
                    .replace(R.id.bottom_container, tool.child)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        } else {
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
        if (mFragmentManager.getBackStackEntryCount() > 0) {
            mFragmentManager.popBackStack();
        } else {
            finish();
        }
    }

    public FrameLayout getAdjustContainer() {
        return mAdjustContainer;
    }
}
