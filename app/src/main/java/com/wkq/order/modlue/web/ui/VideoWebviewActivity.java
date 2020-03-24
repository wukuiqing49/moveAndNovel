package com.wkq.order.modlue.web.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.just.agentweb.AgentWeb;
import com.umeng.analytics.MobclickAgent;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityVideoWebBinding;
import com.wkq.order.modlue.web.presenter.VideoWebPresenter;
import com.wkq.order.modlue.web.view.VideoWebView;


public class VideoWebviewActivity extends MvpBindingActivity<VideoWebView, VideoWebPresenter, ActivityVideoWebBinding> {

    public AgentWeb mAgentWeb;

    public String url;


    public boolean isErro;


    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, VideoWebviewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_web;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");
        if (getMvpView() != null) {
            getMvpView().initView();
        }

    }


    @Override
    protected void onPause() {
        if (mAgentWeb != null)
            mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null)
            mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
        MobclickAgent.onResume(this);

        if (getMvpView() != null) getMvpView().onResume();
    }

    @Override
    protected void onDestroy() {
        if (mAgentWeb != null)
            mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
        MobclickAgent.onPause(this);
        if (getMvpView() != null) getMvpView().cancelAd();
    }


    @Override
    public void onBackPressed() {
        if (mAgentWeb != null) {
            if (!mAgentWeb.back()) {
                this.finish();
            } else {
                if (isErro) {
                    this.finish();
                } else {
                    mAgentWeb.getWebCreator().getWebView().goBack();
                }

            }

        } else {
            finish();
        }

    }


}
