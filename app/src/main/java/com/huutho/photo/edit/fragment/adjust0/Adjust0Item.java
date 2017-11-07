package com.huutho.photo.edit.fragment.adjust0;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huutho.photo.R;
import com.huutho.photo.models.Adjust0;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class Adjust0Item extends LinearLayout {

    ImageView mImageView;
    TextView mTextView;

    private Adjust0 mAdjust;

    public Adjust0Item(Context context,Adjust0 adjust) {
        super(context);

        View view = View.inflate(getContext(), R.layout.item_adjust, null);
        addView(view);
        mImageView = view.findViewById(R.id.icon_adjust);
        mTextView = view.findViewById(R.id.name_adjust);
        mAdjust = adjust;
        mImageView.setImageResource(adjust.mIconAdjust0);
        mTextView.setText(adjust.mNameAdjust0);

    }

    public Adjust0 getData() {
        return mAdjust;
    }
}
