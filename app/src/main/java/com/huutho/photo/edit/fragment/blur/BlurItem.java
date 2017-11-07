package com.huutho.photo.edit.fragment.blur;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huutho.photo.R;
import com.huutho.photo.models.Blur;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class BlurItem extends LinearLayout {

    private ImageView mImageView;
    private TextView mTextView;
    private Blur mBlur;


    public BlurItem(Context context, Blur blur) {
        super(context);

        View view = View.inflate(getContext(), R.layout.item_blur, null);
        addView(view);
        mImageView = view.findViewById(R.id.blur_image);
        mTextView = view.findViewById(R.id.blur_name);

        mBlur = blur;
        mImageView.setImageResource(blur.mIconBlur);
        mTextView.setText(blur.mNameMosaic);
    }


    public Blur getData() {
        return mBlur;
    }
}