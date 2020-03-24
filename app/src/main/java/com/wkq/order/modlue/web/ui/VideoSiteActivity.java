package com.wkq.order.modlue.web.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;


import com.just.agentweb.AgentWeb;
import com.umeng.analytics.MobclickAgent;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityVideoSiteBinding;
import com.wkq.order.modlue.web.presenter.VideoSitePresenter;
import com.wkq.order.modlue.web.view.VideoSiteView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-18
 * <p>
 * 用途:
 */


public class VideoSiteActivity extends MvpBindingActivity<VideoSiteView, VideoSitePresenter, ActivityVideoSiteBinding> implements View.OnClickListener {

    public AgentWeb mAgentWeb;

    public String url;

    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, VideoSiteActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_site;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");
        binding.setListener(this);
        binding.cdPlay.setOnClickListener(this);

        if (getMvpView() != null) getMvpView().initView();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.cd_play:

                CheckLineActivity.startActivity(this, getMvpView().getWebUrl());

                break;

        }
    }


    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
        MobclickAgent.onPause(this);
    }




    @Override
    public void onBackPressed() {

        if (!mAgentWeb.back()) {
            this.finish();
        } else {
            mAgentWeb.getWebCreator().getWebView().goBack();
        }

    }

}
