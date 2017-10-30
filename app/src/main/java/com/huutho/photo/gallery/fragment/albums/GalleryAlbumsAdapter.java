package com.huutho.photo.gallery.fragment.albums;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huutho.photo.R;
import com.huutho.photo.models.ImageAlbum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NguyenHuuTho on 10/30/2017.
 */

public class GalleryAlbumsAdapter extends RecyclerView.Adapter<GalleryAlbumsAdapter.ViewHolder> {

    private List<ImageAlbum> mAlbums;

    private AlbumEventListener mListener;

    public GalleryAlbumsAdapter() {
        mAlbums = new ArrayList<>();
    }

    public void setData(List<ImageAlbum> albums) {
        if (albums != null && albums.size() != 0) {
            mAlbums = albums;
            notifyDataSetChanged();
        }
    }

    public void setListener(AlbumEventListener listener) {
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_galler_albums, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ImageAlbum album = mAlbums.get(position);
        holder.name.setText(album.mName);
        Glide.with(holder.itemView.getContext())
                .load(album.mImages.get(0).mPath)
                .into(holder.thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(v, holder.getAdapterPosition(), album);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAlbums == null ? 0 : mAlbums.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.album_thumbnail)
        ImageView thumbnail;

        @BindView(R.id.album_name)
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    //==============================================================================================

    public interface AlbumEventListener {
        void onClick(View view, int position, ImageAlbum album);
    }

}
