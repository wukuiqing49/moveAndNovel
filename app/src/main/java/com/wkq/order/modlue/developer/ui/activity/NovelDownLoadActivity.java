package com.wkq.order.modlue.developer.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityNovelSubscribeBinding;
import com.wkq.order.modlue.developer.frame.presenter.NovelDownLoadPresenter;
import com.wkq.order.modlue.developer.frame.presenter.NovelSubscriptionPresenter;
import com.wkq.order.modlue.developer.frame.view.NovelDownLoadView;
import com.wkq.order.modlue.developer.frame.view.NovelSubscriptionView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-02-09
 * <p>
 * 用途:
 */


public class NovelDownLoadActivity extends MvpBindingActivity<NovelDownLoadView , NovelDownLoadPresenter, ActivityNovelSubscribeBinding> {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NovelDownLoadActivity.class);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_novel_subscribe;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getMvpView()!=null)getMvpView().initView();
    }
}
