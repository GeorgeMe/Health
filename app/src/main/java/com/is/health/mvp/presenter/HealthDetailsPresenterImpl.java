package com.is.health.mvp.presenter;

import android.content.Context;

import com.is.health.entity.HealthInfoDetails;
import com.is.health.mvp.interactor.HealthDetailsInteractorImpl;
import com.is.health.mvp.listeners.BaseSingleLoadedListener;
import com.is.health.mvp.view.HealthDetailsView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/8/17.
 */
public class HealthDetailsPresenterImpl implements BaseSingleLoadedListener<HealthInfoDetails> {
    private Context mContext = null;
    private HealthDetailsView healthDetailsView;
    private HealthDetailsInteractorImpl detailsInteractor;

    public HealthDetailsPresenterImpl(Context mContext, HealthDetailsView healthDetailsView) {
        this.mContext = mContext;
        this.healthDetailsView = healthDetailsView;
        detailsInteractor=new HealthDetailsInteractorImpl(mContext,this);
    }

    public void getHealthInfoDetails(int id){
        JSONObject json=new JSONObject();
        try {
            json.put("id",id);
            detailsInteractor.getCommonSingleData(json);
        }catch (JSONException j){

        }

    }
    @Override
    public void onSuccess(HealthInfoDetails data) {
        healthDetailsView.onHealthDetails(data);
    }

    @Override
    public void onFailure(String msg) {

    }
}
