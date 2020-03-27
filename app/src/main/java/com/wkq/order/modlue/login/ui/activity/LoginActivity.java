package com.wkq.order.modlue.login.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;

import com.umeng.analytics.MobclickAgent;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityLoginBinding;
import com.wkq.order.modlue.login.frame.presenter.LoginPresenter;
import com.wkq.order.modlue.login.frame.view.LoginView;


/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-26
 * <p>
 * 用途:
 */


public class LoginActivity extends MvpBindingActivity<LoginView, LoginPresenter, ActivityLoginBinding> {

    public String IMEI;

    public static void startActivity(Context context, String IMEI) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("IMEI", IMEI);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IMEI = getIntent().getStringExtra("IMEI");
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
}
