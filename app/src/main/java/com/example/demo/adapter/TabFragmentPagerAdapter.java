package com.example.demo.adapter;

import com.example.demo.mfragment.ImgFragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 *
 *ViewPager+FragmentPagerAdapter实现Tab  适配器
 * @author Fyg add 2021.4
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private final String[] mTitles = {"page1"};  // Tab 集合

    public TabFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ImgFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
