package com.is.health.mvp.interactor;

import android.content.Context;

import com.is.health.base.Retrofit2See;
import com.is.health.mvp.listeners.BaseSingleLoadedListener;
import com.is.health.mvp.listeners.CommonSingleInteractor;
import com.is.health.protocol.ClassifyResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/8/17.
 */
public class ClassifyInteractorImpl extends Retrofit2See implements CommonSingleInteractor{
    BaseSingleLoadedListener<ClassifyResponse> loadedListener;

    public ClassifyInteractorImpl(Context context, BaseSingleLoadedListener<ClassifyResponse> loadedListener) {
        super(context);
        this.loadedListener = loadedListener;
    }

    @Override
    public void getCommonSingleData(JSONObject json) {
        Call<ClassifyResponse> call=seeApi.classify();
        call.enqueue(new Callback<ClassifyResponse>() {
            @Override
            public void onResponse(Call<ClassifyResponse> call, Response<ClassifyResponse> response) {
                loadedListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ClassifyResponse> call, Throwable t) {

            }
        });
    }
}
