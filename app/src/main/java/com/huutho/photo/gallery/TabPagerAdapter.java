package com.huutho.photo.gallery;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.huutho.photo.App;
import com.huutho.photo.R;
import com.huutho.photo.gallery.fragment.app.AppAlbumFragment;
import com.huutho.photo.gallery.fragment.gallery.albums.GalleryAlbumsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenHuuTho on 11/4/2017.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    private int[] iconRes = {R.drawable.ic_image, R.drawable.ic_image};

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);

        mFragments = new ArrayList<>();
        mFragments.add(GalleryAlbumsFragment.newInstance());
        mFragments.add(AppAlbumFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void setIconTab(TabLayout tabLayout) {
        for (int i = 0; i < mFragments.size(); i++) {
           tabLayout.getTabAt(i).setIcon(iconRes[i]);
        }
    }

}
