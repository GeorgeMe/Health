package com.is.health.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.is.health.R;
import com.is.health.SeeConstant;
import com.is.health.base.BaseFragment;
import com.is.health.entity.HealthInfo;
import com.is.health.mvp.presenter.HealthListPresenterImpl;
import com.is.health.mvp.view.HealthListView;
import com.is.health.ui.activitys.HealthDetailsActivity;
import com.is.ui.adapter.ListViewDataAdapter;
import com.is.ui.adapter.ViewHolderBase;
import com.is.ui.adapter.ViewHolderCreator;
import com.is.ui.widget.LoadMoreListView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;

public class HealthInfoListFragment extends BaseFragment implements HealthListView, LoadMoreListView.OnLoadMoreListener, LoadMoreListView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.lv_health_info_list)
    LoadMoreListView lvHealthInfoList;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    private HealthListPresenterImpl healthListPresenter;
    private ListViewDataAdapter<HealthInfo> healthInfoListViewDataAdapter;

    private int mCurrentPage = 1;
    private  int mCurrentImagesCategory=7;

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void onFirstUserVisible() {
        healthListPresenter=new HealthListPresenterImpl(mContext,this);
        healthListPresenter.getHealthList(SeeConstant.EVENT_REFRESH,mCurrentImagesCategory,mCurrentPage);
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_health_info_list;
    }

    @Override
    protected void initViewsAndEvents() {

        healthInfoListViewDataAdapter=new ListViewDataAdapter<>(new ViewHolderCreator<HealthInfo>() {
            @Override
            public ViewHolderBase<HealthInfo> createViewHolder(int position) {
                return new ViewHolderBase<HealthInfo>() {
                    ImageView img_health_info;
                    TextView tv_health_info_title;
                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View view=layoutInflater.inflate(R.layout.item_health_info,null);
                        img_health_info=(ImageView)view.findViewById(R.id.img_health_info);
                        tv_health_info_title=(TextView) view.findViewById(R.id.tv_health_info_title);
                        return view;
                    }

                    @Override
                    public void showData(int position, HealthInfo itemData) {
                        Picasso.with(mContext).load(SeeConstant.BASE_IMG_URL+itemData.getImg()).into(img_health_info);
                        tv_health_info_title.setText(itemData.getTitle());
                    }
                };
            }
        });

        lvHealthInfoList.setAdapter(healthInfoListViewDataAdapter);

        swipeLayout.setOnRefreshListener(this);
        lvHealthInfoList.setOnLoadMoreListener(this);
        lvHealthInfoList.setOnItemClickListener(this);


    }

    @Override
    public void onPageSelected(int position, String keywords) {
        mCurrentImagesCategory=Integer.parseInt(keywords);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HealthInfo healthInfo = (HealthInfo) adapterView.getItemAtPosition(i);
        onHealthInfoItemClick(healthInfo);
    }

    @Override
    public void onHealthInfoItemClick(HealthInfo healthInfo) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("healthInfo",healthInfo);
        readyGo(HealthDetailsActivity.class,bundle);
    }


    @Override
    public void onRefresh() {
        mCurrentPage = 1;
        healthListPresenter.getHealthList(SeeConstant.EVENT_REFRESH,mCurrentImagesCategory,mCurrentPage);
    }

    @Override
    public void onLoadMore() {
        mCurrentPage++;
        healthListPresenter.getHealthList(SeeConstant.EVENT_ADDMORE,mCurrentImagesCategory,mCurrentPage);
    }

    @Override
    public void refreshListData(List<HealthInfo> tngou) {
        if (swipeLayout!=null)
            swipeLayout.setRefreshing(false);
        if (lvHealthInfoList!=null&&tngou!=null){
            healthInfoListViewDataAdapter.getDataList().clear();
            healthInfoListViewDataAdapter.getDataList().addAll(tngou);
            healthInfoListViewDataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addMoreListData(List<HealthInfo> tngou) {
        if (lvHealthInfoList!=null){
            lvHealthInfoList.onLoadMoreComplete();
        }
        if (tngou!=null){
            if (healthInfoListViewDataAdapter!=null){
                healthInfoListViewDataAdapter.getDataList().addAll(tngou);
                healthInfoListViewDataAdapter.notifyDataSetChanged();
            }
            if (lvHealthInfoList!=null){
                if (mCurrentPage>10){
                    lvHealthInfoList.setCanLoadMore(false);
                }else {
                    lvHealthInfoList.setCanLoadMore(true);
                }
            }
        }
    }
}