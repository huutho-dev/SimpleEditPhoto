package com.huutho.photo.edit.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huutho.photo.R;
import com.huutho.photo.models.Tool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NguyenHuuTho on 10/31/2017.
 */


public class ToolsAdapter extends RecyclerView.Adapter<ToolsAdapter.ViewHolder> {

    private List<Tool> mTools;
    private ToolEventListener mListener;

    public ToolsAdapter() {
        mTools = new ArrayList<>();
    }

    public void setData(List<Tool> tools) {
        if (tools != null && tools.size() != 0) {
            mTools.addAll(tools);
            notifyDataSetChanged();
        }
    }

    public void setListener(ToolEventListener listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_tool, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Tool tool = mTools.get(position);
        holder.icon.setImageResource(tool.icon);
        holder.name.setText(tool.name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onClick(v, holder.getAdapterPosition(), tool);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mTools == null ? 0 : mTools.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imv_tool_icon)
        ImageView icon;

        @BindView(R.id.tv_tool_name)
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    //==============================================================================================

    public interface ToolEventListener {
        void onClick(View view, int position, Tool tool);
    }
}
