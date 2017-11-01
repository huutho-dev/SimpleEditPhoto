package com.huutho.photo.edit.fragment.sticker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.huutho.photo.R;
import com.huutho.photo.models.StickerCategory;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class StickerCatItem extends LinearLayout {

    private StickerCategory mStickerCategory;
    private ImageView mImageView;

    public StickerCatItem(Context context) {
        super(context);
        init();
    }

    public StickerCatItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.item_cat_sticker, null);
        addView(view);
        mImageView = view.findViewById(R.id.icon_cat_sticker);
    }

    public void setData(StickerCategory stickerCategory) {
        mStickerCategory = stickerCategory;
        mImageView.setImageResource(stickerCategory.iconCat);
    }

    public StickerCategory getData() {
        return mStickerCategory;
    }
}
