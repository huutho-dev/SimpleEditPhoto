package com.huutho.photo.edit.fragment.adjust;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huutho.photo.R;
import com.huutho.photo.models.Adjust;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class AdjustItem extends LinearLayout {

    ImageView mImageView;
    TextView mTextView;

    private Adjust mAdjust;

    public AdjustItem(Context context) {
        super(context);
        init();
    }

    public AdjustItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.item_adjust, null);
        addView(view);
        mImageView = view.findViewById(R.id.icon_adjust);
        mTextView = view.findViewById(R.id.name_adjust);

    }

    public void setData(Adjust adjust) {
        mAdjust = adjust;
        setTag(adjust.tag);
        mImageView.setImageResource(adjust.icon);
        mTextView.setText(adjust.name);
    }

    public Adjust getData() {
        return mAdjust;
    }
}
