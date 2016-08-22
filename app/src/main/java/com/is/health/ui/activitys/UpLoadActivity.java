package com.is.health.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.is.health.R;
import com.is.health.base.BaseActivity;
import com.is.health.entity.BaseResponse;
import com.is.health.mvp.presenter.UpLoadPresenterImpl;
import com.is.health.mvp.view.UpLoadView;
import com.is.health.upload.MultipartBuilder;
import com.is.ui.eventbus.EventCenter;
import com.squareup.picasso.Picasso;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class UpLoadActivity extends BaseActivity implements UpLoadView{

    @Bind(R.id.btn_choose)
    Button btnChoose;
    @Bind(R.id.img_choose)
    ImageView imgChoose;
    @Bind(R.id.btn_upload)
    Button btnUpload;

    private List<String> pathList;
    private UpLoadPresenterImpl upLoadPresenter;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_up_load;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected void initViewsAndEvents() {

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

    @OnClick({R.id.btn_choose, R.id.btn_upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_choose:
                ImgSelActivity.startActivity(UpLoadActivity.this, config,1);
                break;
            case R.id.btn_upload:
                upLoadPresenter=new UpLoadPresenterImpl(mContext,this);
                upLoadPresenter.onUpLoad(MultipartBuilder.pathsToMapRequestBody(pathList,mHandler));
                break;
        }
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1) {

                case 0:
                    if (msg.what > 0) {
                        Log.e("110",msg.what+"");
                    }
                    break;
                case 1:
                    if (msg.what > 0) {
                        Log.e("110",msg.what+"");
                    }
                    break;
                case 2:
                    if (msg.what > 0) {
                        Log.e("110",msg.what+"");
                    }
                    break;
                case 3:
                    if (msg.what > 0) {
                        Log.e("110",msg.what+"");
                    }
                    break;
                case 4:
                    if (msg.what > 0) {
                        Log.e("110",msg.what+"");
                    }
                    break;
                case 5:
                    if (msg.what > 0) {
                        Log.e("110",msg.what+"");
                    }
                    break;
            }
        }
    };
    @Override
    public void onUpLoadSuccess(BaseResponse<String> data) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            Picasso.with(mContext).load(Uri.fromFile(new File(pathList.get(0)))).into(imgChoose);
        }

    }
    // 自定义图片加载器
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            // TODO 在这边可以自定义图片加载库来加载 ImageView，例如 Glide、Picasso、ImageLoader 等
            Picasso.with(mContext).load(Uri.fromFile(new File(path))).into(imageView);
        }
    };
    // 自由配置选项
    ImgSelConfig config = new ImgSelConfig.Builder(loader)
            // 是否多选
            .multiSelect(true)
            // “确定”按钮背景色
            .btnBgColor(Color.GRAY)
            // “确定”按钮文字颜色
            .btnTextColor(Color.BLUE)
            // 使用沉浸式状态栏
            .statusBarColor(Color.parseColor("#3F51B5"))
            // 返回图标 ResId
            .backResId(android.support.v7.appcompat.R.drawable.abc_cab_background_internal_bg)
            // 标题
            .title("图片")
            // 标题文字颜色
            .titleColor(Color.WHITE)
            // TitleBar 背景色
            .titleBgColor(Color.parseColor("#3F51B5"))
            // 裁剪大小。needCrop 为 true 的时候配置
            .cropSize(1, 1, 200, 200)
            .needCrop(false)
            // 第一个是否显示相机
            .needCamera(true)
            // 最大选择图片数量
            .maxNum(9)
            .build();
}
