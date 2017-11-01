package com.huutho.photo.edit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @InjectPresenter
    ToolsPresenter mPresenter;

    @BindView(R.id.rv_tools)
    RecyclerView mToolsView;


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
    public void setupToolsView(List<Tool> toolData) {
        ToolsAdapter  mAdapter = new ToolsAdapter();
        mToolsView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mToolsView.setAdapter(mAdapter);
        mAdapter.setData(toolData);
        mAdapter.setListener(new ToolsAdapter.ToolEventListener() {
            @Override
            public void onClick(View view, int position, Tool tool) {
                mPresenter.onToolClick(tool);
            }
        });

    }

    @Override
    public void onToolClick(Tool tool) {
        ((EditActivity) getActivity()).openTool(tool);
    }
}
