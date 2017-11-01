package com.huutho.photo.edit.fragment.drawing;

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
import com.huutho.photo.models.BrushSize;
import com.huutho.photo.models.EditorColor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class DrawingFragment extends MvpAppCompatFragment implements DrawingView {

    @InjectPresenter
    DrawingPresenter mPresenter;

    @BindView(R.id.rv_brush_size)
    RecyclerView mBrushSizeView;

    @BindView(R.id.rv_brush_color)
    RecyclerView mBrushColorView;

    private SizesAdapter adapter ;

    public static DrawingFragment newInstance() {
        Bundle args = new Bundle();
        DrawingFragment fragment = new DrawingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drawing, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    public void setupSizeView(List<BrushSize> sizes) {
        adapter = new SizesAdapter(sizes);
        mBrushSizeView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBrushSizeView.setAdapter(adapter);
        adapter.setOnSizeClickListener(new SizesAdapter.OnSizeClickListener() {
            @Override
            public void onClick(BrushSize size) {

            }
        });
    }

    @Override
    public void setupColorView(List<EditorColor> colors) {
        final ColorAdapter adapter = new ColorAdapter(colors);
        mBrushColorView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBrushColorView.setAdapter(adapter);
        adapter.setOnColorClickListener(new ColorAdapter.OnColorClickListener() {
            @Override
            public void onClick(EditorColor editorColor) {

            }
        });
    }
}
