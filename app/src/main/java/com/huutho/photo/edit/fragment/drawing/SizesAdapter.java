package com.huutho.photo.edit.fragment.drawing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huutho.photo.R;
import com.huutho.photo.custom.CircleSizeDrawable;
import com.huutho.photo.models.BrushSize;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SizesAdapter extends RecyclerView.Adapter<SizesAdapter.ViewHolder> {
    private int mSelectedColorPosition = 0;

    private List<BrushSize> mSizes;

    private OnSizeClickListener mOnSizeClickListener;

    public interface OnSizeClickListener {
        void onClick(BrushSize size);
    }

    public void setOnSizeClickListener(OnSizeClickListener onSizeClickListener) {
        mOnSizeClickListener = onSizeClickListener;
    }

    public SizesAdapter(List<BrushSize> sizes) {
        mSizes = sizes;
        mSelectedColorPosition = sizes.size() / 2;
    }

    @Override
    public SizesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_brush_size, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SizesAdapter.ViewHolder holder, int position) {
        final BrushSize size = mSizes.get(position);

        holder.sizeImageView.setImageDrawable(new CircleSizeDrawable(size.getSize()));

        if (mSelectedColorPosition == holder.getAdapterPosition()) {
            ((CircleSizeDrawable) holder.sizeImageView.getDrawable()).setSelected(true);
            if (mOnSizeClickListener != null) {
                mOnSizeClickListener.onClick(size);
            }
        } else {
            ((CircleSizeDrawable) holder.sizeImageView.getDrawable()).setSelected(false);
        }

        holder.sizeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(mSelectedColorPosition);
                mSelectedColorPosition = holder.getAdapterPosition();
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

    }


    public void setColorSelected(){

    }

    @Override
    public int getItemCount() {
        return mSizes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sizeImageView)
        ImageView sizeImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}