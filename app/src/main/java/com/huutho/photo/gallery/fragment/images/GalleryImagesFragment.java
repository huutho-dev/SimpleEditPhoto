package com.huutho.photo.gallery.fragment.images;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huutho.photo.R;
import com.huutho.photo.gallery.GalleryActivity;
import com.huutho.photo.models.Image;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 10/31/2017.
 */

public class GalleryImagesFragment extends Fragment implements GalleryImagesAdapter.GalleryImageEventListener {
    public static final String TAG = GalleryImagesFragment.class.getSimpleName();
    private static final int SPAN_COUNT = 3;
    public static final String EXTRA_IMAGE_ALBUM = "EXTRA_IMAGE_ALBUM";

    @BindView(R.id.gallery_images)
    RecyclerView mGalleryImage;

    private GalleryImagesAdapter mAdapter;


    public static GalleryImagesFragment newInstance(List<Image> images) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(EXTRA_IMAGE_ALBUM, (ArrayList<? extends Parcelable>) images);
        GalleryImagesFragment fragment = new GalleryImagesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_gallery, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mAdapter = new GalleryImagesAdapter();
        mAdapter.setListener(this);
        mGalleryImage.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
        mGalleryImage.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Image> listImage = getArguments().getParcelableArrayList(EXTRA_IMAGE_ALBUM);
        mAdapter.setData(listImage);
    }

    @Override
    public void onClick(View view, int position, Image image) {
        ((GalleryActivity)getActivity()).startEdit(image);
    }
}
