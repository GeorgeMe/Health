package com.is.health.mvp.view;

/**
 * Created by Administrator on 2016/3/10.
 */
public interface BaseView {
    /**
     * show loading message
     *
     * @param msg
     */
    void showLoading(String msg);

    /**
     * hide loading
     */
    void hideLoading();

    /**
     * show error message
     */
    public void showError(String msg);

    /**
     * show exception message
     */
    void showException(String msg);
    /**
     * show toast message
     */
    void showToast(String msg);
}
