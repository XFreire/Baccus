package com.alexandre.baccus.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexandre.baccus.R;
import com.alexandre.baccus.controller.adapter.WineryPagerAdapter;
import com.alexandre.baccus.models.Wine;
import com.alexandre.baccus.models.Winery;

/**
 * Created by alexandre on 4/2/17.
 */

public class WineryFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private ViewPager mPager = null;
    private ActionBar mActionBar;
    private Winery mWinery;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_winery, container, false);

        mPager = (ViewPager) root.findViewById(R.id.pager);
        mPager.setAdapter(new WineryPagerAdapter(getFragmentManager()));

        mWinery = Winery.getInstance();

        mActionBar = (ActionBar) ((AppCompatActivity) getActivity()).getSupportActionBar();
        mPager.addOnPageChangeListener(this);

        updateActionBar(0);
        return root;
    }

    private void updateActionBar(int index) {
        mActionBar.setTitle(mWinery.getWine(index).getName());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mActionBar.setTitle(mWinery.getWine(position).getName());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
