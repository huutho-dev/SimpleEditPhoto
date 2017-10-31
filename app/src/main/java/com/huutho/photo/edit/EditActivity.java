package com.huutho.photo.edit;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.App;
import com.huutho.photo.Constant;
import com.huutho.photo.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        Bitmap bitmap = App.getInstance()
                .getBitmapFromMemoryCache(Constant.KEY_CACHE_BITMAP_CROPPED);

        mImageView.setImageBitmap(bitmap);
    }
}
