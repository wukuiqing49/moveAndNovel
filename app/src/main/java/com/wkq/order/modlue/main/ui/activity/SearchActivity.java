package com.wkq.order.modlue.main.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivitySearchBinding;
import com.wkq.order.modlue.main.frame.presenter.SearchPresenter;
import com.wkq.order.modlue.main.frame.view.SearchView;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/26
 * <p>
 * 简介:
 */
public class SearchActivity extends MvpBindingActivity<SearchView, SearchPresenter, ActivitySearchBinding> {

    public static void startSearch(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getMvpView() != null) getMvpView().initView();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) getPresenter().cancel();
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
