package com.huutho.photo.preview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.huutho.photo.R;
import com.huutho.photo.models.Image;

import java.util.List;

/**
 * Created by NguyenHuuTho on 11/5/2017.
 */

public class PreviewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Image> mImages;
    private LayoutInflater mInflater;

    public PreviewPagerAdapter(Context context, List<Image> images) {
        this.mContext = context;
        this.mImages = images;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mImages == null ? 0 : mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mInflater.inflate(R.layout.item_preview_pager, container, false);
        ImageView imageView = itemView.findViewById(R.id.imv_preview);
        Glide.with(mContext).load(mImages.get(position).mPath).into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
