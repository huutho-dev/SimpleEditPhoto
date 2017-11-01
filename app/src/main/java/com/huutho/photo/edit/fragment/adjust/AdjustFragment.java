package com.huutho.photo.edit.fragment.adjust;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.models.Adjust;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class AdjustFragment extends MvpAppCompatFragment implements AdjustView {
    private static final String TAG = AdjustFragment.class.getSimpleName();

    @InjectPresenter
    AdjustPresenter mPresenter;

    @BindView(R.id.scroll_adjust)
    HorizontalScrollView mAdjustView;

    @BindView(R.id.container_adjust)
    LinearLayout container;


    public static AdjustFragment newInstance() {
        Bundle args = new Bundle();
        AdjustFragment fragment = new AdjustFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_adjust, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    public void setupAdjustView(List<Adjust> adjustList) {
        for (int i = 0; i < adjustList.size(); i++) {
            final AdjustItem adjustItem = new AdjustItem(getContext());
            adjustItem.setData(adjustList.get(i));
            container.addView(adjustItem);
            adjustItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.onAdjustClick(adjustItem.getData());
                }
            });
        }
    }

    @Override
    public void onAdjustClick(Adjust adjust) {

    }
}
