package com.wkq.order.modlue.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wkq.base.frame.fragment.MvpBindingFragment;
import com.wkq.order.R;
import com.wkq.order.databinding.FragmentMovedbComingBinding;
import com.wkq.order.modlue.main.frame.presenter.MoveDbComingPresenter;
import com.wkq.order.modlue.main.frame.view.MoveDbComingView;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class MoveDbComingFragment extends MvpBindingFragment<MoveDbComingView, MoveDbComingPresenter, FragmentMovedbComingBinding> {

    // 是否扩展
    public boolean isExpend;

    public int page = 1;

    public static MoveDbComingFragment newInstance(Context context) {

        Bundle args = new Bundle();
        MoveDbComingFragment moviesFragment = new MoveDbComingFragment();

        moviesFragment.setArguments(args);
        return moviesFragment;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movedb_coming;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getMvpView()!=null)getMvpView().initView();
        if (getPresenter() != null) getPresenter().getBannerData(getActivity());
        if (getPresenter() != null) getPresenter().getData(getActivity(),page);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter()!=null)getPresenter().cancel();
    }
}
