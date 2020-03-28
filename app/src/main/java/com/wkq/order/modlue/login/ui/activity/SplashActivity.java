package com.wkq.order.modlue.login.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.umeng.analytics.MobclickAgent;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivitySplashBinding;
import com.wkq.order.modlue.login.frame.presenter.SplashPresenter;
import com.wkq.order.modlue.login.frame.view.SplashView;
import com.wkq.order.modlue.main.ui.activity.ContactDeveloperActivity;

import static com.wkq.order.utils.Constant.DEBUG_USE_TIME;


/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class SplashActivity extends MvpBindingActivity<SplashView, SplashPresenter, ActivitySplashBinding> {

    public Dialog dialog;
    public int REQUEST_CODE_LAUNCH = 10011;

    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (System.currentTimeMillis() < DEBUG_USE_TIME) {
            if (getMvpView() != null) getMvpView().initView();
            if (Build.VERSION.SDK_INT >= 23) {
                if (getMvpView() != null) getMvpView().checkPermissions();
            } else {
                if (getMvpView() != null) getMvpView().initImei();
            }

        } else {
            getMvpView().showMessage("已过期,请联系开发者");
            ContactDeveloperActivity.startActivity(this);
            finish();
            return;
        }


    }

    public static String[] permissionsREAD = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION};


    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LAUNCH) {
            boolean hasPermissions[] = getPresenter().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
            if (hasPermissions[0]) {

                if (getMvpView() != null) getMvpView().checkPermissions();
            } else {
                if (hasPermissions[1]) {
                    getMvpView().showPermissionPerpetual(requestCode);
                } else {
                    if (getMvpView() != null) getMvpView().checkPermissions();
                }
            }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
