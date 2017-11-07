package com.huutho.photo.edit.fragment.blur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.huutho.photo.App;
import com.huutho.photo.R;
import com.huutho.photo.models.Blur;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class BlurFragment extends MvpAppCompatFragment{


    @BindView(R.id.blur_container)
    LinearLayout mBlurContainer;

    @Inject
    List<Blur> mBlurs;


    public static BlurFragment newInstance() {
        Bundle args = new Bundle();
        BlurFragment fragment = new BlurFragment();
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
        return inflater.inflate(R.layout.fragment_blur,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        for (int i = 0; i < mBlurs.size(); i++) {
            final Blur blur = mBlurs.get(i);
            BlurItem mView = new BlurItem(getContext(), blur);
            mBlurContainer.addView(mView);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBlurClick(blur);
                }
            });
        }
    }

    private void onBlurClick(Blur blur) {

    }
}
