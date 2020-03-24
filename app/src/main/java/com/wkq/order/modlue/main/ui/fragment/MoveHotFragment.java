package com.wkq.order.modlue.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wkq.base.frame.fragment.MvpBindingFragment;
import com.wkq.order.R;
import com.wkq.order.databinding.FragmentMoveHotBinding;
import com.wkq.order.databinding.FragmentMovedbComingBinding;
import com.wkq.order.modlue.main.frame.presenter.MoveComingPresenter;
import com.wkq.order.modlue.main.frame.presenter.MoveHotPresenter;
import com.wkq.order.modlue.main.frame.view.MoveComingView;
import com.wkq.order.modlue.main.frame.view.MoveHotView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class MoveHotFragment extends MvpBindingFragment<MoveHotView, MoveHotPresenter, FragmentMoveHotBinding> {

    public int page = 1;
    public static MoveHotFragment newInstance(Context context) {

        Bundle args = new Bundle();
        MoveHotFragment moviesFragment = new MoveHotFragment();

        moviesFragment.setArguments(args);
        return moviesFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_move_hot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getMvpView() != null) getMvpView().initView();
        if (getPresenter() != null) getPresenter().getData(getActivity(), page);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter()!=null)getPresenter().cancel();
    }
}
