package com.is.health.mvp.view;

import com.is.health.entity.HealthInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface HealthListView extends BaseView{

    void onHealthInfoItemClick(HealthInfo healthInfo);

    void refreshListData(List<HealthInfo> tngou);

    void addMoreListData(List<HealthInfo> tngou);
}
