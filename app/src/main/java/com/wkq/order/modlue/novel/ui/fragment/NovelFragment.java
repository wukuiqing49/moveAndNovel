package com.wkq.order.modlue.novel.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.wkq.base.frame.fragment.MvpBindingFragment;
import com.wkq.baseLib.utlis.SharedPreferencesHelper;
import com.wkq.order.R;
import com.wkq.order.databinding.FragmentNovelBinding;
import com.wkq.order.modlue.main.observable.HomePageChangeObservable;
import com.wkq.order.modlue.novel.frame.presenter.NobelPresenter;
import com.wkq.order.modlue.novel.frame.view.NovelView;

import java.util.Observable;
import java.util.Observer;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-29
 * <p>
 * 用途:
 */


public class NovelFragment extends MvpBindingFragment<NovelView, NobelPresenter, FragmentNovelBinding> implements Observer {




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
        HomePageChangeObservable.newInstance().addObserver(this);
        if (getMvpView()!=null)getMvpView().initView();
        if (getPresenter()!=null)getPresenter().getData(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HomePageChangeObservable.newInstance().deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        int pages = (int) o;
        boolean isFirst = SharedPreferencesHelper.getInstance(getActivity()).getBoolean("isSearchNovelFirst", true);

        if (observable instanceof HomePageChangeObservable && pages == 2&&isFirst ) {

            if (getMvpView() != null) getMvpView().initGuide();
        }

    }
}
