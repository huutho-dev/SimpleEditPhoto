package com.huutho.photo.edit.fragment.mosaic;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huutho.photo.R;
import com.huutho.photo.models.Mosaic;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class MosaicItem extends LinearLayout {

    private ImageView mImageView;
    private TextView mTextView;
    private Mosaic mMosaic;


    public MosaicItem(Context context, Mosaic mosaic ) {
        super(context);

        View view = View.inflate(getContext(), R.layout.item_mosaic, null);
        addView(view);
        mImageView = view.findViewById(R.id.mosaic_image);
        mTextView = view.findViewById(R.id.mosaic_name);

        mMosaic = mosaic;
        mImageView.setImageResource(mosaic.mIconMosaic);
        mTextView.setText(mosaic.mNameMosaic);
    }


    public Mosaic getData() {
        return mMosaic;
    }
}
