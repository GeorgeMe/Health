package com.is.health.mvp.presenter;

import android.content.Context;

import com.is.health.entity.BaseResponse;
import com.is.health.mvp.interactor.UpLoadInteractorImpl;
import com.is.health.mvp.listeners.BaseSingleLoadedListener;
import com.is.health.mvp.view.UpLoadView;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2016/8/20.
 */
public class UpLoadPresenterImpl implements BaseSingleLoadedListener<BaseResponse<String>> {
    private Context mContext = null;
    private UpLoadView upLoadView;
    private UpLoadInteractorImpl upLoadInteractor;

    public UpLoadPresenterImpl(Context mContext, UpLoadView upLoadView) {
        this.mContext = mContext;
        this.upLoadView = upLoadView;
        upLoadInteractor=new UpLoadInteractorImpl(mContext,this);
    }

    public void onUpLoad(Map<String, RequestBody> requestBodyMap){
        upLoadInteractor.getCommonSingleData(requestBodyMap);
    }

    @Override
    public void onSuccess(BaseResponse<String> data) {
        upLoadView.onUpLoadSuccess(data);
    }

    @Override
    public void onFailure(String msg) {
        upLoadView.hideLoading();
    }
}
