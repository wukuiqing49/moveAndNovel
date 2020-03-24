package com.wkq.order.modlue.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wkq.base.frame.fragment.MvpBindingFragment;
import com.wkq.order.R;
import com.wkq.order.databinding.FragmentMoveTopBinding;
import com.wkq.order.databinding.FragmentMovedbComingBinding;
import com.wkq.order.modlue.main.frame.presenter.MoveComingPresenter;
import com.wkq.order.modlue.main.frame.presenter.MoveTopPresenter;
import com.wkq.order.modlue.main.frame.view.MoveComingView;
import com.wkq.order.modlue.main.frame.view.MoveTopView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class MoveTopFragment extends MvpBindingFragment<MoveTopView, MoveTopPresenter, FragmentMoveTopBinding> {

    public int page = 1;

    public static MoveTopFragment newInstance(Context context) {

        Bundle args = new Bundle();
        MoveTopFragment moviesFragment = new MoveTopFragment();

        moviesFragment.setArguments(args);
        return moviesFragment;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_move_top;
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
