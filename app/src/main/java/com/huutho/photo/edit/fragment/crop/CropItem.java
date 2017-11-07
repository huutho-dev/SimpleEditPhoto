package com.huutho.photo.edit.fragment.crop;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huutho.photo.R;
import com.huutho.photo.models.Crop;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class CropItem extends LinearLayout {

    private ImageView mImageView;
    private TextView mTextView;
    private Crop mCrop;


    public CropItem(Context context, Crop crop) {
        super(context);

        View view = View.inflate(getContext(), R.layout.item_crop, null);
        addView(view);
        mImageView = view.findViewById(R.id.crop_image);
        mTextView = view.findViewById(R.id.crop_name);

        mCrop = crop;
        mImageView.setImageResource(crop.mIcon);
        mTextView.setText(crop.mName);
    }


    public Crop getData() {
        return mCrop;
    }
}