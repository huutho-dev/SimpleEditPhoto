package com.huutho.photo.edit.fragment.mosaic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.huutho.photo.App;
import com.huutho.photo.R;
import com.huutho.photo.models.Mosaic;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class MosaicFragment extends MvpAppCompatFragment {

    @BindView(R.id.mosaic_container)
    LinearLayout mMosaicContainer;

    @Inject
    List<Mosaic> mMosaics;


    public static MosaicFragment newInstance() {
        Bundle args = new Bundle();
        MosaicFragment fragment = new MosaicFragment();
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
        return inflater.inflate(R.layout.fragment_mosaic, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        for (int i = 0; i < mMosaics.size(); i++) {
            final Mosaic mosaic = mMosaics.get(i);
            MosaicItem mView = new MosaicItem(getContext(), mosaic);
            mMosaicContainer.addView(mView);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMosaicOnClick(mosaic);
                }
            });
        }

    }

    private void onMosaicOnClick(Mosaic mosaic) {

    }
}
