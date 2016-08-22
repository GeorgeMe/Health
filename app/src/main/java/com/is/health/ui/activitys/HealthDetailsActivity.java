package com.is.health.ui.activitys;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.is.health.R;
import com.is.health.SeeConstant;
import com.is.health.base.BaseActivity;
import com.is.health.entity.HealthInfo;
import com.is.health.entity.HealthInfoDetails;
import com.is.health.mvp.presenter.HealthDetailsPresenterImpl;
import com.is.health.mvp.view.HealthDetailsView;
import com.is.ui.eventbus.EventCenter;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;

public class HealthDetailsActivity extends BaseActivity implements HealthDetailsView {

    @Bind(R.id.ll_health_details)
    LinearLayout llHealthDetails;

    @Bind(R.id.img_health_details)
    ImageView imgHealthDetails;
    @Bind(R.id.tv_health_details_title)
    TextView tvHealthDetailsTitle;
    @Bind(R.id.tv_release_time)
    TextView tvReleaseTime;
    @Bind(R.id.tv_reading_quantity)
    TextView tvReadingQuantity;
    @Bind(R.id.tv_health_details_message)
    TextView tvHealthDetailsMessage;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private HealthInfo healthInfo = null;
    private HealthDetailsPresenterImpl healthDetailsPresenter;


    @Override
    protected void getBundleExtras(Bundle extras) {
        healthInfo = (HealthInfo) extras.getSerializable("healthInfo");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_health_details;
    }

    @Override
    protected void initViewsAndEvents() {

        healthDetailsPresenter = new HealthDetailsPresenterImpl(mContext, this);
        healthDetailsPresenter.getHealthInfoDetails(healthInfo.getId());
        setSystemBarTint(llHealthDetails, R.color.colorPrimary);
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    public void onHealthDetails(HealthInfoDetails details) {
        if (details != null) {
            Picasso.with(mContext).load(SeeConstant.BASE_IMG_URL + details.getImg()).into(imgHealthDetails);
            tvHealthDetailsTitle.setText(details.getTitle());
            tvReleaseTime.setText(sdf.format(new Date(details.getTime())));
            tvReadingQuantity.setText(details.getCount() + "");
            tvHealthDetailsMessage.setText(Html.fromHtml(details.getMessage() + ""));
        }
    }

}
