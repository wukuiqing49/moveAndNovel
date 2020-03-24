package com.wkq.order.modlue.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wkq.base.frame.fragment.MvpBindingFragment;
import com.wkq.order.R;
import com.wkq.order.databinding.FragmentMoviesBinding;
import com.wkq.order.modlue.main.frame.presenter.MoviesPresenter;
import com.wkq.order.modlue.main.frame.view.MoviesView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MoviesFragment extends MvpBindingFragment<MoviesView, MoviesPresenter, FragmentMoviesBinding> {

    public static MoviesFragment newInstance(Context context, String xx) {
        Bundle args = new Bundle();
        args.putString("test", xx);
        MoviesFragment moviesFragment = new MoviesFragment();
        moviesFragment.setArguments(args);
        return moviesFragment;

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movies;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        StatusBarUtil.setDarkMode(getActivity());
//        StatusBarUtil.setColor(getActivity(), getActivity().getResources().getColor(R.color.white));
        if (getMvpView() != null) getMvpView().initView();
        if (getPresenter() != null) getPresenter().initData(getActivity());


    }
}
