package com.wkq.order.modlue.move.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;


import com.umeng.analytics.MobclickAgent;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityMoveDetailBinding;
import com.wkq.order.modlue.move.frame.presenter.MoveDetailPresenter;
import com.wkq.order.modlue.move.frame.view.MoveDailView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-27
 * <p>
 * 用途:
 */


public class MoveDetailActivity extends MvpBindingActivity<MoveDailView, MoveDetailPresenter, ActivityMoveDetailBinding> implements View.OnClickListener {

    public String moveId;

    public boolean isExpend;

    public static void startMoveDetail(Context context, String moveId) {
        Intent intent = new Intent(context, MoveDetailActivity.class);
        intent.putExtra("moveId", moveId);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        binding.collapsingTopbarLayout.scrollTo(0,0);
        binding.rlContent.scrollTo(0, 0);
        binding.appbarLayout.setExpanded(true);
        moveId = intent.getStringExtra("moveId");
        if (getMvpView() != null) getMvpView().initView();
        if (getPresenter() != null) getPresenter().getMoveDetail(this, moveId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_move_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moveId = getIntent().getStringExtra("moveId");
        if (TextUtils.isEmpty(moveId) && getMvpView() != null) {
            getMvpView().showMessage(getResources().getString(R.string.string_data_err));
        }
        if (getMvpView() != null) getMvpView().initView();
        if (getPresenter() != null) getPresenter().getMoveDetail(this, moveId);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.rl_back:
                finish();
                break;

        }
    }

    @Override
    protected void onDestroy() {
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
