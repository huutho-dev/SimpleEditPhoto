package com.huutho.photo.edit.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huutho.photo.R;
import com.huutho.photo.models.Tool;

/**
 * Created by ThoNh on 11/8/2017.
 */

public class ToolsItem extends LinearLayout {

    ImageView mImageView;
    TextView mTextView;

    private Tool mTool;

    public ToolsItem(Context context, Tool tool) {
        super(context);

        View view = View.inflate(getContext(), R.layout.item_tool, null);
        addView(view);
        mImageView = view.findViewById(R.id.icon);
        mTextView = view.findViewById(R.id.name);
        mTool = tool;
        mImageView.setImageResource(tool.icon);
        mTextView.setText(tool.name);

    }

    public Tool getData() {
        return mTool;
    }
}

