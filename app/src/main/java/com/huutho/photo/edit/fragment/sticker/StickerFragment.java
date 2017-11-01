package com.huutho.photo.edit.fragment.sticker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.huutho.photo.R;
import com.huutho.photo.models.StickerCategory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class StickerFragment extends MvpAppCompatFragment implements StickerView {

    @InjectPresenter
    StickerPresenter mPresenter;

    @BindView(R.id.sticker_container)
    LinearLayout mContainer;


    public static StickerFragment newInstance() {
        Bundle args = new Bundle();
        StickerFragment fragment = new StickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sticker, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    public void setUpStickerLst(List<StickerCategory> categories) {

        for (int i = 0; i < categories.size(); i++) {
            final StickerCatItem item = new StickerCatItem(getContext());
            item.setData(categories.get(i));
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.onCategoryClick(item.getData());
                }
            });
            mContainer.addView(item);
        }
    }

    @Override
    public void onCategoryClick(StickerCategory category) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage("Show Sticker Here");
        alertDialog.show();
    }

}
