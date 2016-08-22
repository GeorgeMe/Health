package com.is.health.mvp.interactor;

import android.content.Context;

import com.is.health.base.Retrofit2See;
import com.is.health.entity.BaseResponse;
import com.is.health.mvp.listeners.BaseSingleLoadedListener;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/20.
 */
public class UpLoadInteractorImpl extends Retrofit2See{

    BaseSingleLoadedListener<BaseResponse<String>> loadedListener;

    public UpLoadInteractorImpl(Context context, BaseSingleLoadedListener<BaseResponse<String>> loadedListener) {
        super(context);
        this.loadedListener = loadedListener;
    }

    public void getCommonSingleData(Map<String, RequestBody> requestBodyMap) {

/*
        Call<BaseResponse<String>> call= seeApi.uploadFileWithRequestBody(multipartBody);
        call.enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                loadedListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                Log.e("",t.getMessage());
            }
        });*/
        seeApi.uploadFileInfo(requestBodyMap).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loadedListener.onFailure(e.getMessage());
            }

            @Override
            public void onNext(BaseResponse baseResponse) {
                loadedListener.onSuccess(baseResponse);
            }
        });
       // super.setBaseUrl("http://apis.baidu.com/tngou/info/");
    }
}
