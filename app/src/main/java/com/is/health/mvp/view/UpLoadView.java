package com.is.health.mvp.view;

import com.is.health.entity.BaseResponse;

/**
 * Created by Administrator on 2016/8/20.
 */
public interface UpLoadView extends BaseView{
    void onUpLoadSuccess(BaseResponse<String> data);
}
