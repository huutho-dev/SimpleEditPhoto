package com.huutho.photo.gallery.fragment.gallery.images;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.huutho.photo.R;
import com.huutho.photo.models.Image;
import com.huutho.photo.models.ImageAlbum;
import com.huutho.photo.preview.PreviewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ThoNh on 10/31/2017.
 */

public class GalleryImagesFragment extends Fragment implements GalleryImagesAdapter.GalleryImageEventListener {
    public static final String TAG = GalleryImagesFragment.class.getSimpleName();
    private static final int SPAN_COUNT = 3;
    public static final String EXTRA_IMAGE_ALBUM = "EXTRA_IMAGE_ALBUM";

    @BindView(R.id.tv_title_toolbar)
    TextView mTitleToolbar;

    @BindView(R.id.gallery_images)
    RecyclerView mGalleryImage;

    private GalleryImagesAdapter mAdapter;

    private ImageAlbum mImageAlbum;

    public static GalleryImagesFragment newInstance(ImageAlbum album) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_IMAGE_ALBUM, album);
        GalleryImagesFragment fragment = new GalleryImagesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_image_gallery, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mImageAlbum = getArguments().getParcelable(EXTRA_IMAGE_ALBUM);
        mTitleToolbar.setText(mImageAlbum.mName);

        mAdapter = new GalleryImagesAdapter();
        mAdapter.setListener(this);
        mGalleryImage.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
        mGalleryImage.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getContext(), R.anim.recyclerview_layout_animation_fall_down));
        mGalleryImage.setAdapter(mAdapter);
        if (mImageAlbum != null)
            mAdapter.setData(mImageAlbum.mImages);

    }


    @OnClick(R.id.imv_back)
    public void onBack(){
        getActivity().onBackPressed();
    }

    @Override
    public void onClick(View view, int position, Image image) {
        PreviewActivity.startActivity(getActivity(), mImageAlbum.mImages, position, view);
    }
}
