package com.is.health.mvp.interactor;

import android.content.Context;

import com.is.health.base.Retrofit2See;
import com.is.health.entity.HealthInfoDetails;
import com.is.health.mvp.listeners.BaseSingleLoadedListener;
import com.is.health.mvp.listeners.CommonSingleInteractor;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/8/17.
 */
public class HealthDetailsInteractorImpl  extends Retrofit2See implements CommonSingleInteractor {
    BaseSingleLoadedListener<HealthInfoDetails> loadedListener;

    public HealthDetailsInteractorImpl(Context context, BaseSingleLoadedListener<HealthInfoDetails> loadedListener) {
        super(context);
        this.loadedListener = loadedListener;
    }

    @Override
    public void getCommonSingleData(JSONObject json) {
        Call<HealthInfoDetails> call=seeApi.show(json.optInt("id"));
        call.enqueue(new Callback<HealthInfoDetails>() {
            @Override
            public void onResponse(Call<HealthInfoDetails> call, Response<HealthInfoDetails> response) {
                loadedListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<HealthInfoDetails> call, Throwable t) {

            }
        });
    }
}
