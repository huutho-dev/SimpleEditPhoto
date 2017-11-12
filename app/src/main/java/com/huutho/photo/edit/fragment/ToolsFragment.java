package com.huutho.photo.edit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.edit.EditActivity;
import com.huutho.photo.models.Tool;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NguyenHuuTho on 10/31/2017.
 */

public class ToolsFragment extends MvpAppCompatFragment implements ToolsView {
    private static final String TAG = ToolsFragment.class.getSimpleName();

    @InjectPresenter
    ToolsPresenter mPresenter;

    @BindView(R.id.container_tools)
    LinearLayout mContainer;


    public static ToolsFragment newInstance() {
        Bundle args = new Bundle();
        ToolsFragment fragment = new ToolsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tools, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((EditActivity) getActivity()).updateToolbar(getString(R.string.edit_activity), R.drawable.ic_close);
    }

    @Override
    public void setupToolsView(List<Tool> tools) {
        for (int i = 0; i < tools.size(); i++) {
            final ToolsItem toolItem = new ToolsItem(getContext(), tools.get(i));
            mContainer.addView(toolItem);
            toolItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onToolClick(toolItem.getData());
                }
            });
        }
    }

    @Override
    public void onToolClick(Tool tool) {
        ((EditActivity) getActivity()).openTool(tool);
    }
}
