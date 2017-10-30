package com.huutho.photo.gallery.fragment.albums;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.gallery.GalleryActivity;
import com.huutho.photo.models.ImageAlbum;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NguyenHuuTho on 10/30/2017.
 */

public class GalleryAlbumsFragment extends MvpAppCompatFragment implements GalleryAlbumsView, GalleryAlbumsAdapter.AlbumEventListener {
    private static final String TAG = GalleryAlbumsFragment.class.getSimpleName();
    private static final int SPAN_COUNT = 2;

    public static GalleryAlbumsFragment newInstance() {
        Bundle args = new Bundle();
        GalleryAlbumsFragment fragment = new GalleryAlbumsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
    GalleryAlbumsPresenter mPresenter;

    @BindView(R.id.gallery_albums)
    RecyclerView mAlbums;

    @BindView(R.id.gallery_empty)
    TextView mEmpty;

    @BindView(R.id.loading)
    ProgressBar mLoading;

    private GalleryAlbumsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_albums_gallery, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mAdapter = new GalleryAlbumsAdapter();
        mAdapter.setListener(this);
        mAlbums.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
        mAlbums.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.fetchAlbums();
        mLoading.setVisibility(View.VISIBLE);
    }


    // RecyclerView Item Click
    @Override
    public void onClick(View view, int position, ImageAlbum album) {
        ((GalleryActivity)getActivity()).openAlbum(album);
    }

    @Override
    public void fetchImageSuccess(List<ImageAlbum> imageAlbums) {
        mEmpty.setVisibility(View.GONE);
        mAdapter.setData(imageAlbums);
    }

    @Override
    public void fetchImageComplete() {
        mLoading.setVisibility(View.GONE);
    }

    @Override
    public void fetchImageError() {

    }

    @Override
    public void fetchImageEmpty() {
        mEmpty.setVisibility(View.VISIBLE);
    }
}
