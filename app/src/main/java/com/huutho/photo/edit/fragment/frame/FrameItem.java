package com.huutho.photo.edit.fragment.frame;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.huutho.photo.R;
import com.huutho.photo.models.Frame;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class FrameItem extends LinearLayout {

    private Frame mFrame;
    private ImageView mImageView;

    public FrameItem(Context context) {
        super(context);
        init();
    }

    public FrameItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.item_frame, null);
        addView(view);
        mImageView = view.findViewById(R.id.icon_frame);
    }

    public void setData(Frame frame) {
        mFrame = frame;
        Glide.with(getContext()).load(frame.mDescription).into(mImageView);
    }

    public Frame getData() {
        return mFrame;
    }
}
