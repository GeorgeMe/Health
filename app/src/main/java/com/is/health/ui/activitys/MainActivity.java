package com.is.health.ui.activitys;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.is.health.R;
import com.is.health.base.BaseActivity;
import com.is.health.entity.Classify;
import com.is.health.mvp.presenter.ClassifyPresenterImpl;
import com.is.health.mvp.view.ClassifyView;
import com.is.health.ui.adapters.HealthInfoListContainerPagerAdapter;
import com.is.health.ui.fragments.HealthInfoListFragment;
import com.is.health.util.SortComparator;
import com.is.ui.eventbus.EventCenter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements ClassifyView {

    @Bind(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.rl_main)
    RelativeLayout rlMain;
    private ClassifyPresenterImpl classifyPresenter;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        classifyPresenter = new ClassifyPresenterImpl(mContext, this);
        classifyPresenter.getClassifyList();
        setSystemBarTint(rlMain, R.color.colorPrimary);
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    public void onClassifyList(final List<Classify> tngou) {
        //进行排序
        Comparator comp = new SortComparator();
        Collections.sort(tngou, comp);

        viewpager.setOffscreenPageLimit(tngou.size());
        viewpager.setAdapter(new HealthInfoListContainerPagerAdapter(getSupportFragmentManager(), tngou));
        viewpagertab.setViewPager(viewpager);
        viewpagertab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                HealthInfoListFragment healthInfoListFragment = (HealthInfoListFragment) viewpager.getAdapter().instantiateItem(viewpager, position);
                healthInfoListFragment.onPageSelected(position, "" + tngou.get(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpagertab.setOnTabClickListener(new SmartTabLayout.OnTabClickListener() {
            @Override
            public void onTabClicked(int position) {
                HealthInfoListFragment healthInfoListFragment = (HealthInfoListFragment) viewpager.getAdapter().instantiateItem(viewpager, position);
                healthInfoListFragment.onPageSelected(position, "" + tngou.get(position).getId());
            }
        });
    }

}
