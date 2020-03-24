package com.wkq.order.modlue.htmlmove.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;

import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityMoveHtmlBinding;
import com.wkq.order.modlue.htmlmove.frame.presenter.MoveHtmlPresenter;
import com.wkq.order.modlue.htmlmove.frame.view.MoveHtmlView;
import com.wkq.order.modlue.main.ui.activity.AboutActivity;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-03
 * <p>
 * 用途:
 */


public class MoveHtmlActivity extends MvpBindingActivity<MoveHtmlView, MoveHtmlPresenter, ActivityMoveHtmlBinding> {


    public String moveUrl;
    public String imgUrl;

    public boolean isExpend;

    public static void startActivity(Context context, String moveUrl, String imgUrl) {
        Intent intent = new Intent(context, MoveHtmlActivity.class);
        intent.putExtra("moveUrl", moveUrl);
        intent.putExtra("imgUrl", imgUrl);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }

    public static void startActivity(Context context, String moveUrl) {
        Intent intent = new Intent(context, MoveHtmlActivity.class);
        intent.putExtra("moveUrl", moveUrl);

        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_move_html;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moveUrl = getIntent().getStringExtra("moveUrl");
        imgUrl = getIntent().getStringExtra("imgUrl");

        if (getMvpView() != null) getMvpView().initView();
        if (getPresenter() != null) getPresenter().initData(this, moveUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) getPresenter().cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayerStandard.backPress()) {
            return;
        }
        super.onBackPressed();
    }


}
