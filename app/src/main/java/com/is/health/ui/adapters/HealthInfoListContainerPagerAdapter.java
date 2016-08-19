package com.is.health.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.is.health.entity.Classify;
import com.is.health.ui.fragments.HealthInfoListFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
public class HealthInfoListContainerPagerAdapter extends FragmentPagerAdapter {

    private List<Classify> mCategoryList = null;

    public HealthInfoListContainerPagerAdapter(FragmentManager fm, List<Classify> mCategoryList) {
        super(fm);
        this.mCategoryList = mCategoryList;
    }

    @Override
    public Fragment getItem(int position) {
        return new HealthInfoListFragment();
    }

    @Override
    public int getCount() {
        return null != mCategoryList ? mCategoryList.size() : 0;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return null != mCategoryList ? mCategoryList.get(position).getName() : null;
    }
}
