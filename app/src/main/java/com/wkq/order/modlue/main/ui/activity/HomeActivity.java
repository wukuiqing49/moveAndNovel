package com.wkq.order.modlue.main.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityHomeBinding;
import com.wkq.order.modlue.main.frame.presenter.HomePresenter;
import com.wkq.order.modlue.main.frame.view.HomeView;
import com.wkq.order.utils.BackTowPressed;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class HomeActivity  extends MvpBindingActivity<HomeView, HomePresenter, ActivityHomeBinding> {

    public static void startPlayHelperActivity(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getMvpView()!=null)getMvpView().initView();
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

    @Override
    public void onBackPressed() {
        if (BackTowPressed.onBackPressed()) {
            super.onBackPressed();


        } else {
            AlertUtil.showDeftToast(this, "再次点击退出,影讯");
        }
    }
}
