package com.is.health.base;

import android.support.v7.widget.Toolbar;

import com.is.common.StringUtils;
import com.is.health.R;
import com.is.health.mvp.view.BaseView;
import com.is.health.util.ProgressDialog;
import com.is.ui.base.BaseAppCompatActivity;
import com.is.ui.netstatus.NetUtils;

import butterknife.ButterKnife;

/**
 *
 */
public abstract class BaseActivity extends BaseAppCompatActivity implements BaseView {

    protected Toolbar mToolbar;
    private ProgressDialog progressDialog;
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {
        if (type.name().equals("WIFI")){

        }else if (type.name().equals("CMNET")){
        }else if (type.name().equals("CMWAP")){
        }else if (type.name().equals("NONE")){

        }
        showToast("正在连接"+type.name()+"网络");
    }

    @Override
    protected void onNetworkDisConnected() {
        showToast("网络连接断开");
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showException(String msg) {

    }

    @Override
    public void showLoading(String msg) {
        if (progressDialog==null) {
            if(StringUtils.StringIsEmpty(msg)){
                progressDialog=new ProgressDialog(mContext,getString(R.string.please_later_on));
                progressDialog.show();
            }else {
                progressDialog=new ProgressDialog(mContext,msg);
                progressDialog.show();
            }
        }else {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog!=null) {
            progressDialog.dismiss();
            progressDialog=null;
        }
    }

    @Override
    public void showToast(String msg) {

    }
}
