package com.is.health.mvp.presenter;

import android.content.Context;

import com.is.health.SeeConstant;
import com.is.health.mvp.interactor.HealthListInteractorImpl;
import com.is.health.mvp.listeners.BaseMultiLoadedListener;
import com.is.health.mvp.view.HealthListView;
import com.is.health.protocol.HealthListResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/8/17.
 */
public class HealthListPresenterImpl implements BaseMultiLoadedListener<HealthListResponse>{
    private Context mContext = null;
    private HealthListView healthListView;
    private HealthListInteractorImpl healthListInteractor;

    public HealthListPresenterImpl(Context mContext, HealthListView healthListView) {
        this.mContext = mContext;
        this.healthListView = healthListView;
        healthListInteractor=new HealthListInteractorImpl(mContext,this);
    }

    public void getHealthList(int event_tag,int classifyID,int page){
        JSONObject json=new JSONObject();
        try {
            json.put("id",classifyID);
            json.put("page",page);
            json.put("rows",SeeConstant.PAGE_ROWS);
            healthListInteractor.getCommonListData(event_tag,json);
        }catch (JSONException j){

        }
    }
    @Override
    public void onSuccess(int event_tag, HealthListResponse data) {
        if (event_tag == SeeConstant.EVENT_REFRESH){
            healthListView.refreshListData(data.getTngou());
        }else if (event_tag == SeeConstant.EVENT_ADDMORE){
            healthListView.addMoreListData(data.getTngou());
        }
    }

    @Override
    public void onFailure(String msg) {

    }
}
