package com.is.health.protocol;

import com.is.health.entity.Classify;

import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
public class ClassifyResponse {

    private boolean status;

    private List<Classify> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Classify> getTngou() {
        return tngou;
    }

    public void setTngou(List<Classify> tngou) {
        this.tngou = tngou;
    }

}
