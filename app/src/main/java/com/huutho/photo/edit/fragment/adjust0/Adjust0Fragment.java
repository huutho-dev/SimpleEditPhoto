package com.huutho.photo.edit.fragment.adjust0;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.huutho.photo.App;
import com.huutho.photo.R;
import com.huutho.photo.models.Adjust0;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/7/2017.
 */

public class Adjust0Fragment extends MvpAppCompatFragment {

    @BindView(R.id.container_adjust)
    LinearLayout mAdjustContainer;

    @Inject
    List<Adjust0> mAdjust0s;

    public static Adjust0Fragment newInstance() {
        Bundle args = new Bundle();
        Adjust0Fragment fragment = new Adjust0Fragment();
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
        return inflater.inflate(R.layout.fragment_adjust,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        for (int i = 0; i < mAdjust0s.size(); i++) {
            final Adjust0Item adjustItem = new Adjust0Item(getContext(), mAdjust0s.get(i));
            mAdjustContainer.addView(adjustItem);
            adjustItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAdjustClick(adjustItem.getData());
                }
            });
        }
    }

    private void onAdjustClick(Adjust0 data) {

    }
}
