package com.wkq.order.modlue.web.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityMainTestBinding;
import com.wkq.order.modlue.main.frame.presenter.VideoWebPresenter;
import com.wkq.order.modlue.main.frame.view.VideoWebView;
import com.wkq.order.modlue.main.ui.activity.PlayHelperActivity;

public class VideoWebListActivity extends MvpBindingActivity<VideoWebView, VideoWebPresenter, ActivityMainTestBinding> implements View.OnClickListener {


    public static void startVideoWebList(Context context) {
        Intent intent = new Intent(context, VideoWebListActivity.class);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setOnClicker(this);

        if (getMvpView()!=null)getMvpView().initData();
        if (getMvpView()!=null)getMvpView().initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_test;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//
            case R.id.tv_play_help:

                PlayHelperActivity.startPlayHelperActivity(this);
                break;
//            case R.id.tv_scan:
//                ScanActivity.startScanActivity(this);
//
//                break;
//
//            case R.id.tv_web:
//                VideoWebviewActivity.startActivity(this);
//                break;
//
//            case R.id.tv_data:
////                if (getPresenter() != null) getPresenter().getData(this);
//                AdbWebActivity.startActivity(this,"");
//                break;

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
