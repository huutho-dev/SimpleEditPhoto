package com.huutho.photo.gallery.fragment.gallery.images;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huutho.photo.R;
import com.huutho.photo.models.Image;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 10/31/2017.
 */

public class GalleryImagesAdapter extends RecyclerView.Adapter<GalleryImagesAdapter.ViewHolder> {


    private List<Image> mImages;
    private GalleryImageEventListener mListener;


    public GalleryImagesAdapter() {
        mImages = new ArrayList<>();
    }

    public void setListener(GalleryImageEventListener listener) {
        mListener = listener;
    }

    public void setData(List<Image> images) {
        if (images != null && images.size() != 0) {
            mImages.addAll(images);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery_image, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Image image = mImages.get(position);

        Glide.with(holder.itemView.getContext())
                .load(image.mPath)
                .into(holder.mImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(v, holder.getAdapterPosition(), image);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages == null ? 0 : mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    //==============================================================================================

    public interface GalleryImageEventListener {
        void onClick(View view, int position, Image image);
    }
}
