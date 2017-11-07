package com.huutho.photo.edit.fragment.rotate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.huutho.photo.App;
import com.huutho.photo.R;
import com.huutho.photo.models.Rotate;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class RotateFragment extends MvpAppCompatFragment {
    @BindView(R.id.rotate_container)
    LinearLayout mContainer;

    @Inject
    List<Rotate> mRotates;


    public static RotateFragment newInstance() {
        Bundle args = new Bundle();
        RotateFragment fragment = new RotateFragment();
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
        return inflater.inflate(R.layout.fragment_rotate, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        for (int i = 0; i < mRotates.size(); i++) {
            final Rotate rotate = mRotates.get(i);
            RotateItem mView = new RotateItem(getContext(), rotate);
            mContainer.addView(mView);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRotateClick(rotate);
                }
            });
        }
    }

    private void onRotateClick(Rotate blur) {

    }
}
