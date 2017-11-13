package com.huutho.photo.edit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import com.huutho.photo.edit.fragment.ToolsFragment;
import com.huutho.photo.models.Tool;
import com.huutho.photo.utils.LogUtils;
import com.huutho.photo.utils.ScreenUtils;
import com.isseiaoki.simplecropview.CropImageView;

import org.wysaid.view.ImageGLSurfaceView;

import java.util.ArrayList;
import java.util.List;

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

    private List<Bitmap> mBitmapForUndoRedo;

    private FragmentManager mFragmentManager;
    public Bitmap mOriginBitmap;
    public Bitmap mEditBitmap;


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
        mBitmapForUndoRedo = new ArrayList<>();

        mImageView.setZOrderOnTop(true);
        mImageView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        mImageView.setSurfaceCreatedCallback(new ImageGLSurfaceView.OnSurfaceCreatedCallback() {
            @Override
            public void surfaceCreated() {
                mImageView.setDisplayMode(ImageGLSurfaceView.DisplayMode.DISPLAY_ASPECT_FIT);
                mImageView.setImageBitmap(mEditBitmap);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addToUndoList(mEditBitmap);
                    }
                });

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
    protected void onDestroy() {
        super.onDestroy();
        recycleBitmapList();
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
        if (mImageView.getVisibility() == View.INVISIBLE || mImageView.getVisibility() == View.GONE) {
            mImageView.setVisibility(View.VISIBLE);
        }
        mImageView.setImageBitmap(mEditBitmap);
        addToUndoList(mEditBitmap);
    }

    @OnClick({R.id.imv_back, R.id.imv_menu_undo, R.id.imv_menu_redo, R.id.imv_menu_done, R.id.tv_fragment_tool_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_back:
                onBackPressed();
                break;
            case R.id.imv_menu_undo:
                mEditBitmap.recycle();
                mEditBitmap = getUndoBitmap();
                mImageView.setImageBitmap(mEditBitmap);
                setButtonVisibility();
                break;
            case R.id.imv_menu_redo:
                mEditBitmap.recycle();
                mEditBitmap = getRedoBitmap();
                mImageView.setImageBitmap(mEditBitmap);
                setButtonVisibility();
                break;
            case R.id.imv_menu_done:
                break;
            case R.id.tv_fragment_tool_save:
                ((BaseToolFragment) mFragmentManager.findFragmentById(R.id.bottom_container)).onSave();
                if (mFragmentManager.getBackStackEntryCount() > 0) {
                    mFragmentManager.popBackStack();
                } else {
                    finish();
                }
                break;
        }
    }


    /***************************************UNDO - REDO BITMAP************************************/

    private int mCurrentShowIndex = -1;

    private void addToUndoList(Bitmap bitmap) {
        try {
            recycleBitmapList(++mCurrentShowIndex);
            mBitmapForUndoRedo.add(bitmap.copy(bitmap.getConfig(), true));
            LogUtils.e(TAG, "addToUndoList ----> try() size:" + mBitmapForUndoRedo.size() + "----> currentIndex:" + mCurrentShowIndex);
        } catch (OutOfMemoryError error) {
            mBitmapForUndoRedo.get(1).recycle();
            mBitmapForUndoRedo.remove(1);
            mBitmapForUndoRedo.add(mEditBitmap.copy(mEditBitmap.getConfig(), true));
            LogUtils.e(TAG, "addToUndoList ----> catch() size:" + mBitmapForUndoRedo.size() + "----> currentIndex:" + mCurrentShowIndex);
            error.printStackTrace();
        }
        setButtonVisibility();
    }

    // When i have 6 bitmap in arrayList
    // I undo to position 4 then i added one bitmap after position 4 --> this function remove bitmap 5old and 6
    private void recycleBitmapList(int fromIndex) {
        while (fromIndex < mBitmapForUndoRedo.size()) {
            mBitmapForUndoRedo.get(fromIndex).recycle();
            mBitmapForUndoRedo.remove(fromIndex);
            LogUtils.e(TAG, "recycleBitmapList ----> white() size:" + mBitmapForUndoRedo.size() + "----> fromIndex:" + fromIndex);
        }
    }

    // remove all bitmap in ArrayList
    // should call onDestroy
    private void recycleBitmapList() {
        recycleBitmapList(0);
    }

    private Bitmap getUndoBitmap() {
        if (mCurrentShowIndex - 1 >= 0) {
            mCurrentShowIndex -= 1;
            LogUtils.e(TAG, "getUndoBitmap ----> true() size:" + mBitmapForUndoRedo.size() + "----> mCurrentShowIndex:" + mCurrentShowIndex);
        } else {
            mCurrentShowIndex = 0;
            LogUtils.e(TAG, "getUndoBitmap ----> false() size:" + mBitmapForUndoRedo.size() + "----> mCurrentShowIndex:" + mCurrentShowIndex);
        }
        return mBitmapForUndoRedo.get(mCurrentShowIndex)
                .copy(mBitmapForUndoRedo.get(mCurrentShowIndex).getConfig(), true);
    }

    private Bitmap getRedoBitmap() {
        if (mCurrentShowIndex < mBitmapForUndoRedo.size() - 1) {
            mCurrentShowIndex += 1;
            LogUtils.e(TAG, "getRedoBitmap ----> true() size:" + mBitmapForUndoRedo.size() + "----> mCurrentShowIndex:" + mCurrentShowIndex);
        } else {
            mCurrentShowIndex = mBitmapForUndoRedo.size() - 1;
            LogUtils.e(TAG, "getRedoBitmap ----> false() size:" + mBitmapForUndoRedo.size() + "----> mCurrentShowIndex:" + mCurrentShowIndex);
        }

        return mBitmapForUndoRedo.get(mCurrentShowIndex)
                .copy(mBitmapForUndoRedo.get(mCurrentShowIndex).getConfig(), true);
    }

    private void setButtonVisibility() {
        if (mCurrentShowIndex > 0) {
            mImvMenuUndo.setColorFilter(Color.BLACK);
            mImvMenuUndo.setEnabled(true);
        } else {
            mImvMenuUndo.setColorFilter(Color.GRAY);
            mImvMenuUndo.setEnabled(false);
        }

        if (mCurrentShowIndex < mBitmapForUndoRedo.size() - 1) {
            mImvMenuRedo.setColorFilter(Color.BLACK);
            mImvMenuRedo.setEnabled(true);
        } else {
            mImvMenuRedo.setColorFilter(Color.GRAY);
            mImvMenuRedo.setEnabled(false);
        }
    }
}
