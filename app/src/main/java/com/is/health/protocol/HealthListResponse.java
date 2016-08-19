package com.is.health.protocol;

import com.is.health.entity.HealthInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
public class HealthListResponse {

    private boolean status;
    private int total;

    private List<HealthInfo> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<HealthInfo> getTngou() {
        return tngou;
    }

    public void setTngou(List<HealthInfo> tngou) {
        this.tngou = tngou;
    }

}
