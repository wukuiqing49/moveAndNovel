package com.wkq.order.modlue.login.frame.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.baseLib.utlis.KeyboardUtils;
import com.wkq.baseLib.utlis.RegexUtils;
import com.wkq.database.utils.DataBaseUtils;
import com.wkq.net.BaseInfo;
import com.wkq.net.model.VUserInfo;
import com.wkq.order.R;
import com.wkq.order.modlue.login.ui.activity.LoginActivity;
import com.wkq.order.modlue.main.ui.activity.ContactDeveloperActivity;
import com.wkq.order.modlue.main.ui.activity.HomeActivity;
import com.wkq.order.utils.StatusBarUtil;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-26
 * <p>
 * 用途:
 */


public class LoginView implements MvpView {
    LoginActivity mActivity;

    public LoginView(LoginActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        StatusBarUtil.setStatusBarWrite(mActivity);
        StatusBarUtil.setColor(mActivity, mActivity.getResources().getColor(R.color.color_2b2b2b), 0);
        StatusBarUtil.setDarkMode(mActivity);

        mActivity.binding.btLogin.setOnClickListener(view -> {
            KeyboardUtils.hideSoftInput(mActivity);
            commitUser();

        });


    }

    private void commitUser() {


        String phoneNum = mActivity.binding.etPhone.getText().toString();
        String pwd = mActivity.binding.etPwd.getText().toString();
        boolean isPhoneNum = RegexUtils.isMobileSimple(phoneNum);
        if (TextUtils.isEmpty(phoneNum) || !isPhoneNum) {
            showMessage("请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showMessage("请输入六位密码");
            return;
        }

        if (mActivity.getPresenter() != null)
            mActivity.getPresenter().registerUSer(mActivity, phoneNum, pwd, mActivity.IMEI);
    }


    public void showLoading() {
        mActivity.binding.rlLoading.setVisibility(View.VISIBLE);

    }

    public void hideLoading() {
        mActivity.binding.rlLoading.setVisibility(View.GONE);

    }

    public void setData(BaseInfo<VUserInfo> data) {
        if (data != null) {
            if (data.getCode().equals("200")) {
                //注册成功
                VUserInfo info = data.getData();
                DataBaseUtils.insertUser(mActivity, info.getUserPhoneNum(), info.getUserPwd(), info.getUserId() + "", info.getUserState() + "", info.getUserIMEI());
                if (info.getUserState() == 1) {
                    jumpHomeActivity();
                } else {
                    ContactDeveloperActivity.startActivity(mActivity);
                }

            } else if ("100".equals(data.getCode())) {
                showMessage(data.getMessage());
                ContactDeveloperActivity.startActivity(mActivity);
            } else {
                showMessage(data.getMessage());

            }

        }

    }

    public void jumpHomeActivity() {
        HomeActivity.startPlayHelperActivity(mActivity);
        mActivity.finish();
    }


    public void showMessage(String message) {
        if (mActivity == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mActivity, message);
    }
}
