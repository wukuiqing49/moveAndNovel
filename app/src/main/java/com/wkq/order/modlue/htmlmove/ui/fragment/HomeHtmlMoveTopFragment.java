package com.wkq.order.modlue.htmlmove.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wkq.base.frame.fragment.MvpBindingFragment;
import com.wkq.order.R;
import com.wkq.order.databinding.FragmentHomeHtmlMoveBinding;
import com.wkq.order.databinding.FragmentMoveHtmlTopBinding;
import com.wkq.order.modlue.htmlmove.frame.presenter.HomeHtmlMoveTopPresenter;
import com.wkq.order.modlue.htmlmove.frame.view.HomeHtmlMoveTopView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-03
 * <p>
 * 用途:  时光网页抓取数据的首页
 */


public class HomeHtmlMoveTopFragment extends MvpBindingFragment<HomeHtmlMoveTopView, HomeHtmlMoveTopPresenter, FragmentMoveHtmlTopBinding> {

    // 是否扩展
    public boolean isExpend;
    public int page=1;
    public int pageNum=1;
    public String url;


    public static HomeHtmlMoveTopFragment newInstance(String url) {

        Bundle args = new Bundle();
        HomeHtmlMoveTopFragment moviesFragment = new HomeHtmlMoveTopFragment();
        args.putString("url", url);
        moviesFragment.setArguments(args);
        return moviesFragment;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_move_html_top;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        url = getArguments().getString("url");

        if (getMvpView() != null) getMvpView().initView();
        if (getPresenter() != null) getPresenter().getData(url,page);
    }
}
