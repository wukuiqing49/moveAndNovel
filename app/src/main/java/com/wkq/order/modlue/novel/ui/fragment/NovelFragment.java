package com.wkq.order.modlue.novel.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.wkq.base.frame.fragment.MvpBindingFragment;
import com.wkq.order.R;
import com.wkq.order.databinding.FragmentNovelBinding;
import com.wkq.order.modlue.novel.frame.presenter.NobelPresenter;
import com.wkq.order.modlue.novel.frame.view.NovelView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-29
 * <p>
 * 用途:
 */


public class NovelFragment extends MvpBindingFragment<NovelView, NobelPresenter, FragmentNovelBinding> {




    public static NovelFragment newInstance() {
        Bundle args = new Bundle();
        NovelFragment moviesFragment = new NovelFragment();
        moviesFragment.setArguments(args);
        return moviesFragment;

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_novel;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getMvpView()!=null)getMvpView().initView();
        if (getPresenter()!=null)getPresenter().getData(this);

    }
}
