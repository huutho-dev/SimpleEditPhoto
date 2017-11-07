package com.huutho.photo.edit.fragment.crop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.huutho.photo.App;
import com.huutho.photo.R;
import com.huutho.photo.models.Crop;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class CropFragment extends MvpAppCompatFragment {
    @BindView(R.id.crop_container)
    LinearLayout mCropContainer;

    @Inject
    List<Crop> mCrops;


    public static CropFragment newInstance() {
        Bundle args = new Bundle();
        CropFragment fragment = new CropFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.editorComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crop,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        for (int i = 0; i < mCrops.size(); i++) {
            final Crop crop = mCrops.get(i);
            CropItem mView = new CropItem(getContext(), crop);
            mCropContainer.addView(mView);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBlurClick(crop);
                }
            });
        }
    }

    private void onBlurClick(Crop blur) {

    }
}
