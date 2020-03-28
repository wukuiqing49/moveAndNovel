package com.wkq.order.modlue.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.wkq.base.frame.fragment.MvpBindingFragment;
import com.wkq.baseLib.utlis.SharedPreferencesHelper;
import com.wkq.order.R;
import com.wkq.order.databinding.FragmentMoveInformationBinding;
import com.wkq.order.modlue.main.frame.presenter.MoveInformationPresenter;
import com.wkq.order.modlue.main.frame.view.MoveInformationView;
import com.wkq.order.modlue.main.observable.HomePageChangeObservable;

import java.util.Observable;
import java.util.Observer;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class MoveInformationFragment extends MvpBindingFragment<MoveInformationView, MoveInformationPresenter, FragmentMoveInformationBinding> implements Observer {

        boolean isShow=false;
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
        HomePageChangeObservable.newInstance().addObserver(this);
        if (getMvpView() != null) getMvpView().initView();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HomePageChangeObservable.newInstance().deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        int pages = (int) o;
        boolean isFirst = SharedPreferencesHelper.getInstance(getActivity()).getBoolean("isSearchFirst", true);

        if (observable instanceof HomePageChangeObservable && pages == 1 && isFirst) {
           isShow=true;
            if (getMvpView() != null) getMvpView().initGuide();
        }


    }
}
