package com.example.demo.mfragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.demo.R;
import com.example.demo.adapter.TabFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;


/**
 *  原理 ：
 *   碎片 fourFragment 图层上---->新建 TabFragment+viewpage 设配器  又在此图层 增加  ImgFragment RecyclerView 网格图层
 *
 */

public class fourFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_four, container, false);

        initView(view);
        initData();

        return view;
    }

    private void initView(View view)
    {
        tabLayout = view.findViewById(R.id.tablelayout_img);
        viewPager = view.findViewById(R.id.t_page);
    }

    private void initData() {
        // 设置设配器
        TabFragmentPagerAdapter adapter2 = new TabFragmentPagerAdapter(this.getChildFragmentManager(),1);
        viewPager.setAdapter(adapter2);
        tabLayout.setupWithViewPager(viewPager);
        //--------


    }






}