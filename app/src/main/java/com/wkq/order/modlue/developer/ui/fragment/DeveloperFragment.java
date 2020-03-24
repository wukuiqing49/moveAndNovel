package com.wkq.order.modlue.developer.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wkq.base.frame.fragment.MvpBindingFragment;
import com.wkq.order.R;
import com.wkq.order.databinding.FragmentDeveloperBinding;
import com.wkq.order.modlue.developer.frame.presenter.DeveloperPresenter;
import com.wkq.order.modlue.developer.frame.view.DeveloperView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-24
 * <p>
 * 用途:
 */


public class DeveloperFragment extends MvpBindingFragment<DeveloperView, DeveloperPresenter, FragmentDeveloperBinding> {

    public static DeveloperFragment newInstance() {

        DeveloperFragment fragment = new DeveloperFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_developer;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getMvpView() != null) getMvpView().initView();
    }
}
