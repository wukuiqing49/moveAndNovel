package com.wkq.order.modlue.novel.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityNovelInfoBinding;
import com.wkq.order.modlue.novel.frame.presenter.NovelInfoPresenter;
import com.wkq.order.modlue.novel.frame.view.NoveInfolView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-22
 * <p>
 * 用途:
 */


public class NovelInfoActivity extends MvpBindingActivity<NoveInfolView, NovelInfoPresenter, ActivityNovelInfoBinding> {

    public String bookName;

    public static void startActivity(Context context, String bookName) {

        Intent intent = new Intent();
        intent.putExtra("bookName", bookName);
        intent.setClass(context, NovelInfoActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_novel_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookName = getIntent().getStringExtra("bookName");
        if (getMvpView() != null) getMvpView().initView();
        if (getPresenter() != null) getPresenter().getNovelInfo(this, bookName);
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null) getPresenter().cancel();
        super.onDestroy();
    }
}
