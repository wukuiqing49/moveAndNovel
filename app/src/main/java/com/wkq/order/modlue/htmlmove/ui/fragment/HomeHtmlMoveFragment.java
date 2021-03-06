package com.wkq.order.modlue.htmlmove.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wkq.base.frame.fragment.MvpBindingFragment;
import com.wkq.baseLib.utlis.SharedPreferencesHelper;
import com.wkq.order.R;
import com.wkq.order.databinding.FragmentHomeHtmlMoveBinding;
import com.wkq.order.modlue.htmlmove.frame.presenter.HomeHtmlMovePresenter;
import com.wkq.order.modlue.htmlmove.frame.view.HomeHtmlMoveView;
import com.wkq.order.modlue.main.observable.HomePageChangeObservable;

import java.util.Observable;
import java.util.Observer;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-03
 * <p>
 * 用途:  时光网页抓取数据的首页
 */


public class HomeHtmlMoveFragment extends MvpBindingFragment<HomeHtmlMoveView, HomeHtmlMovePresenter, FragmentHomeHtmlMoveBinding> implements Observer {

    // 是否扩展
    public boolean isExpend;


    public static HomeHtmlMoveFragment newInstance() {

        Bundle args = new Bundle();
        HomeHtmlMoveFragment moviesFragment = new HomeHtmlMoveFragment();

        moviesFragment.setArguments(args);
        return moviesFragment;

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_html_move;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomePageChangeObservable.newInstance().addObserver(this);
        if (getMvpView()!=null)getMvpView().initView();
        if (getPresenter()!=null)getPresenter().getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HomePageChangeObservable.newInstance().deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        int pages = (int) o;
        boolean isFirst = SharedPreferencesHelper.getInstance(getActivity()).getBoolean("isHomeFirst", true);

        if (observable instanceof HomePageChangeObservable && pages == 0&&isFirst ) {

            if (getMvpView() != null) getMvpView().initGuide();
        }

    }
}
