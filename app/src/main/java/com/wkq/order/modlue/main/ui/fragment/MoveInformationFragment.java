package com.wkq.order.modlue.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.wkq.base.frame.fragment.MvpBindingFragment;
import com.wkq.order.R;
import com.wkq.order.databinding.FragmentMoveInformationBinding;
import com.wkq.order.modlue.main.frame.presenter.MoveInformationPresenter;
import com.wkq.order.modlue.main.frame.view.MoveInformationView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class MoveInformationFragment extends MvpBindingFragment<MoveInformationView, MoveInformationPresenter, FragmentMoveInformationBinding> {


    public static MoveInformationFragment newInstance(Context context) {

        Bundle args = new Bundle();
        MoveInformationFragment moviesFragment = new MoveInformationFragment();

        moviesFragment.setArguments(args);
        return moviesFragment;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_move_information;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getMvpView()!=null)getMvpView().initView();

    }
}
