package com.is.health.mvp.interactor;

import android.content.Context;

import com.is.health.base.Retrofit2See;
import com.is.health.mvp.listeners.BaseMultiLoadedListener;
import com.is.health.mvp.listeners.CommonListInteractor;
import com.is.health.protocol.HealthListResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/8/17.
 */
public class HealthListInteractorImpl   extends Retrofit2See implements CommonListInteractor{

    BaseMultiLoadedListener<HealthListResponse> loadedListener;

    public HealthListInteractorImpl(Context context, BaseMultiLoadedListener<HealthListResponse> loadedListener) {
        super(context);
        this.loadedListener = loadedListener;
    }

    @Override
    public void getCommonListData(final int event, JSONObject json) {
        Call<HealthListResponse> call=seeApi.list(json.optInt("id"),json.optInt("page"),json.optInt("rows"));
        call.enqueue(new Callback<HealthListResponse>() {
            @Override
            public void onResponse(Call<HealthListResponse> call, Response<HealthListResponse> response) {
                loadedListener.onSuccess(event,response.body());
            }

            @Override
            public void onFailure(Call<HealthListResponse> call, Throwable t) {

            }
        });
    }


}
