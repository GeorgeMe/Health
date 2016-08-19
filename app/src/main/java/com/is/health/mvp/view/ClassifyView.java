package com.is.health.mvp.view;

import com.is.health.entity.Classify;

import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface ClassifyView extends BaseView{
    void onClassifyList(List<Classify> tngou);
}
