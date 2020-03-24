package com.wkq.order.modlue.web.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;

import com.umeng.analytics.MobclickAgent;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityCheckLineBinding;
import com.wkq.order.modlue.web.presenter.CheckLinePresenter;
import com.wkq.order.modlue.web.view.CheckLineView;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/20
 * <p>
 * 简介:
 */
public class CheckLineActivity extends MvpBindingActivity<CheckLineView, CheckLinePresenter, ActivityCheckLineBinding> {

    public String videoUrl;

    public static void startActivity(Context context, String videoUrl) {
        Intent intent = new Intent(context, CheckLineActivity.class);
        intent.putExtra("url", videoUrl);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.preview_activity_in, R.anim.preview_activity_out);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_line;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        videoUrl = getIntent().getStringExtra("url");

        if (getMvpView() != null) getMvpView().initView();

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.preview_activity_in, R.anim.preview_activity_out);
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
