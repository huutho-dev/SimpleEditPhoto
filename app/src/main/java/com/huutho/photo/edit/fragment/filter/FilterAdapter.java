package com.huutho.photo.edit.fragment.filter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huutho.photo.R;
import com.huutho.photo.models.Filter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

    private List<Filter> mFilterList;
    private FilterEventListener mListener;

    public FilterAdapter() {
        mFilterList = new ArrayList<>();
    }

    public void setFilterList(List<Filter> filterList) {
        if (filterList != null && filterList.size() != 0) {
            mFilterList.addAll(filterList);
            notifyDataSetChanged();
        }

    }

    public void setListener(FilterEventListener listener) {
        mListener = listener;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_filter, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Filter filter = mFilterList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(v, holder.getAdapterPosition(), filter);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFilterList == null ? 0 : mFilterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.filter)
        ImageView mImvFilter;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface FilterEventListener {
        void onClick(View view, int position, Filter filter);
    }
}
