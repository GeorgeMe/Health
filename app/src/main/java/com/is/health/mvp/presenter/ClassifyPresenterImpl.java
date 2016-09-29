package com.is.health.mvp.presenter;

import android.content.Context;

import com.is.health.mvp.interactor.ClassifyInteractorImpl;
import com.is.health.mvp.listeners.BaseSingleLoadedListener;
import com.is.health.mvp.view.ClassifyView;
import com.is.health.protocol.ClassifyResponse;

/**
 * Created by Administrator on 2016/8/17.
 */
public class ClassifyPresenterImpl implements BaseSingleLoadedListener<ClassifyResponse> {
    private Context mContext = null;
    private ClassifyView classifyView;
    private ClassifyInteractorImpl classifyInteractor;

    public ClassifyPresenterImpl(Context mContext, ClassifyView classifyView) {
        this.mContext = mContext;
        this.classifyView = classifyView;
        classifyInteractor=new ClassifyInteractorImpl(mContext,this);
    }

    public void getClassifyList(){
        classifyInteractor.getCommonSingleData(null);
    }
    @Override
    public void onSuccess(ClassifyResponse data) {
        if(data.getTngou()!=null){
            classifyView.onClassifyList(data.getTngou());
        }

    }

    @Override
    public void onFailure(String msg) {

    }
}
