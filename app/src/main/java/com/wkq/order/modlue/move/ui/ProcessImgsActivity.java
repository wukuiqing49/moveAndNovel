package com.wkq.order.modlue.move.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;


import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.baseLib.utlis.PermissionChecker;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityProcessImgsBinding;
import com.wkq.order.modlue.move.frame.presenter.ProcessImgsPresenter;
import com.wkq.order.modlue.move.frame.view.ProcessImgsView;
import com.wkq.order.utils.Constant;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-03
 * <p>
 * 用途:
 */


public class ProcessImgsActivity extends MvpBindingActivity<ProcessImgsView, ProcessImgsPresenter, ActivityProcessImgsBinding> {

    public String imgUrl;

    public int REQUEST_CODE_PERMISSION_CODE = 10010;

    public static void startActivity(Context context, String imgUrl) {
        Intent intent = new Intent(context, ProcessImgsActivity.class);
        intent.putExtra("imgUrl", imgUrl);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.preview_activity_in, R.anim.preview_activity_out);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_process_imgs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgUrl = getIntent().getStringExtra("imgUrl");
        if (!TextUtils.isEmpty(imgUrl)&&!imgUrl.startsWith("http")) imgUrl = Constant.MOVE_DB_IMG_BASE_500.concat(imgUrl);
        if (getMvpView() != null) getMvpView().initView();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION_CODE) {
            boolean hasPermission = PermissionChecker.onRequestPermissionsResult(this, requestCode, permissions, grantResults,
                    false, R.string.string_permission_save_pic)[0];
            if (hasPermission) {
                if (getMvpView() != null)
                    getMvpView().saveImg();
            }

        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.preview_activity_in, R.anim.preview_activity_out);
    }

}
