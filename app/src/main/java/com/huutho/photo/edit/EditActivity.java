package com.huutho.photo.edit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.base.BaseToolFragment;
import com.huutho.photo.crop.StateBitmapManager;
import com.huutho.photo.edit.fragment.ToolsFragment;
import com.huutho.photo.models.Tool;
import com.huutho.photo.utils.LogUtils;
import com.huutho.photo.utils.ScreenUtils;
import com.isseiaoki.simplecropview.CropImageView;

import org.wysaid.view.ImageGLSurfaceView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dontcare.utils.BitmapUtils;

/**
 * Created by ThoNh on 10/31/2017.
 */

public class EditActivity extends MvpAppCompatActivity implements EditView {
    private static final String TAG = EditActivity.class.getSimpleName();
    private static final String EXTRA_IMAGE_PATH = "EXTRA_IMAGE_PATH";

    @InjectPresenter
    public EditPresenter mPresenter;
    @BindView(R.id.imv_back)
    public ImageView mImvBack;
    @BindView(R.id.imv_menu_undo)
    public ImageView mImvMenuUndo;
    @BindView(R.id.imv_menu_redo)
    public ImageView mImvMenuRedo;
    @BindView(R.id.imv_menu_done)
    public ImageView mImvMenuDone;
    @BindView(R.id.tv_title_toolbar)
    public TextView mTvTitleToolbar;
    @BindView(R.id.layout_editor)
    public LinearLayout mLayoutEditor;
    @BindView(R.id.tv_fragment_tool_save)
    public TextView mTvFragmentToolSave;
    @BindView(R.id.layout_tool)
    public LinearLayout mLayoutTool;
    @BindView(R.id.image)
    public ImageGLSurfaceView mImageView;
    @BindView(R.id.cropImageView)
    public CropImageView mCropImageView;
    @BindView(R.id.seekbar_container)
    public FrameLayout mContainerSeekBar;

    private FragmentManager mFragmentManager;
    public Bitmap mOriginBitmap;
    public Bitmap mEditBitmap;
    public StateBitmapManager mBitmapManager;


    public static void newInstance(Activity activity, String pathImage) {
        Intent intent = new Intent(activity, EditActivity.class);
        intent.putExtra(EXTRA_IMAGE_PATH, pathImage);
        activity.startActivity(intent);
    }

    @Override
    public void getBitmapFromPath() {
        String imagePath = getIntent().getStringExtra(EXTRA_IMAGE_PATH);
        if (imagePath != null) {
            mOriginBitmap = BitmapUtils.resizeBitmap(imagePath, ScreenUtils.getScreenWidth());
            mEditBitmap = Bitmap.createBitmap(mOriginBitmap);
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
        mBitmapManager = new StateBitmapManager();

        mImageView.setZOrderOnTop(true);
        mImageView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        mImageView.setSurfaceCreatedCallback(new ImageGLSurfaceView.OnSurfaceCreatedCallback() {
            @Override
            public void surfaceCreated() {
                mImageView.setDisplayMode(ImageGLSurfaceView.DisplayMode.DISPLAY_ASPECT_FIT);
                mImageView.setImageBitmap(mEditBitmap);
            }
        });

        mFragmentManager = getSupportFragmentManager();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.bottom_container, ToolsFragment.newInstance())
                .commitAllowingStateLoss();

        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int count = mFragmentManager.getBackStackEntryCount();
                if (count > 0) {
                    // fragment open
                    mLayoutTool.setVisibility(View.VISIBLE);
                    mLayoutEditor.setVisibility(View.GONE);
                } else {
                    mLayoutEditor.setVisibility(View.VISIBLE);
                    mLayoutTool.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    public void updateToolbar(String title, int icon) {
        mTvTitleToolbar.setText(title);
        mImvBack.setImageResource(icon);
    }


    // Call when tool click on ToolsFragment.class
    @Override
    public void openTool(Tool tool) {
        mFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.vertical_enter, R.anim.vertical_exit, R.anim.vertical_pop_enter, R.anim.vertical_pop_exit)
                .replace(R.id.bottom_container, tool.child)
                .addToBackStack(null)
                .commitAllowingStateLoss();

        updateToolbar(tool.name, R.drawable.ic_back);
    }


    @Override
    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() > 0) {
            ((BaseToolFragment) mFragmentManager.findFragmentById(R.id.bottom_container)).onCancel();
            mFragmentManager.popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_editor, menu);
        return true;
    }

    public FrameLayout getContainerSeekBar() {
        return mContainerSeekBar;
    }

    public ImageGLSurfaceView getImageView() {
        return mImageView;
    }

    public Bitmap getBitmapSurface() {
        return mEditBitmap;
    }

    public void setBitmapSurfaceView(Bitmap bitmap) {
        mEditBitmap = bitmap;
        if (mImageView.getVisibility() == View.INVISIBLE || mImageView.getVisibility() == View.GONE){
            mImageView.setVisibility(View.VISIBLE);
        }
        mImageView.setImageBitmap(mEditBitmap);
    }

    @OnClick({R.id.imv_back, R.id.imv_menu_undo, R.id.imv_menu_redo, R.id.imv_menu_done, R.id.tv_fragment_tool_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_back:
                onBackPressed();
                break;
            case R.id.imv_menu_undo:
                break;
            case R.id.imv_menu_redo:
                break;
            case R.id.imv_menu_done:
                break;
            case R.id.tv_fragment_tool_save:
                ((BaseToolFragment) mFragmentManager.findFragmentById(R.id.bottom_container)).onSave();
                onBackPressed();
                break;
        }
    }

}
