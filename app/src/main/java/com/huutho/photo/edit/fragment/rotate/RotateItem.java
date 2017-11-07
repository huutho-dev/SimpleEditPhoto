package com.huutho.photo.edit.fragment.rotate;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huutho.photo.R;
import com.huutho.photo.models.Rotate;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class RotateItem extends LinearLayout {

    private ImageView mImageView;
    private TextView mTextView;
    private Rotate mRotate;


    public RotateItem(Context context, Rotate rotate) {
        super(context);

        View view = View.inflate(getContext(), R.layout.item_rotate, null);
        addView(view);
        mImageView = view.findViewById(R.id.rotate_image);
        mTextView = view.findViewById(R.id.rotate_name);

        mRotate = rotate;
        mImageView.setImageResource(rotate.mIcon);
        mTextView.setText(rotate.mName);
    }


    public Rotate getData() {
        return mRotate;
    }
}