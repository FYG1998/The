package com.example.demo.mfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.demo.R;
import com.google.android.material.tabs.TabLayout;

public class fourFragment extends Fragment {

    private TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_four, container, false);

        tabLayout = view.findViewById(R.id.tablelayout_img);
        ViewPager viewPager = view.findViewById(R.id.t_page);


        TabFragmentPagerAdapter adapter2 = new TabFragmentPagerAdapter(this.getChildFragmentManager(),1);
        viewPager.setAdapter(adapter2);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    //ViewPager适配器
    private class TabFragmentPagerAdapter extends FragmentPagerAdapter {


        private final String[] mTitles = {"page1","page2"};

        public TabFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

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




}