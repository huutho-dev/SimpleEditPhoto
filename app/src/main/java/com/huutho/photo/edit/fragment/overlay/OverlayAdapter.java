package com.huutho.photo.edit.fragment.overlay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huutho.photo.R;
import com.huutho.photo.models.Overlay;
import com.huutho.photo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class OverlayAdapter extends RecyclerView.Adapter<OverlayAdapter.ViewHolder> {
    private static final String TAG = OverlayAdapter.class.getSimpleName();

    private List<Overlay> mOverlayList;
    private OverlayEventListener mListener;

    public OverlayAdapter() {
        mOverlayList = new ArrayList<>();
    }

    public void setData(List<Overlay> overlayList) {
        if (overlayList != null && overlayList.size() != 0) {
            mOverlayList.addAll(overlayList);
            notifyDataSetChanged();
        }
    }

    public void setListener(OverlayEventListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_overlay, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext())
                .load(mOverlayList.get(holder.getAdapterPosition()).mPathImageOverlay)
                .into(holder.mImageView);

        LogUtils.e(TAG, "path: " + mOverlayList.get(position).mPathImageOverlay);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v, position, mOverlayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOverlayList == null ? 0 : mOverlayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imv_overlay)
        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OverlayEventListener {
        void onClick(View view, int position, Overlay overlay);
    }
}
