package com.wkq.order.modlue.main.frame.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.net.BaseInfo;
import com.wkq.net.model.MoveDataInfo;
import com.wkq.order.R;
import com.wkq.order.modlue.main.ui.adapter.MoveHotAdapter;
import com.wkq.order.modlue.main.ui.fragment.MoveHotFragment;
import com.wkq.order.modlue.move.ui.MoveDetailActivity;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DynamicTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class MoveHotView implements MvpView {

    MoveHotFragment mFragment;
    private MoveHotAdapter moveTopAdapter;

    public MoveHotView(MoveHotFragment mFragment) {
        this.mFragment = mFragment;
    }



    public void initView() {

        ClassicsHeader header = new ClassicsHeader(mFragment.getActivity());
        ClassicsFooter footer = new ClassicsFooter(mFragment.getActivity());
        header.setProgressDrawable(mFragment.getResources().getDrawable(R.drawable.ic_progress_puzzle));
        header.setBackgroundColor(mFragment.getResources().getColor(R.color.color_f4f4f4));
        header.setSpinnerStyle(SpinnerStyle.Translate);
        int delta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);

        header.setLastUpdateTime(new Date(System.currentTimeMillis() - delta));
        header.setTimeFormat(new SimpleDateFormat("更新于 MM-dd HH:mm", Locale.CHINA));
        header.setTimeFormat(new DynamicTimeFormat("更新于 %s"));

        mFragment.binding.rvSf.setRefreshHeader(header);
        mFragment.binding.rvSf.setRefreshFooter(footer);


        moveTopAdapter = new MoveHotAdapter(mFragment.getActivity());
        mFragment.binding.rvContent.setLayoutManager(new GridLayoutManager(mFragment.getActivity(), 3));
        mFragment.binding.rvContent.setAdapter(moveTopAdapter);

        mFragment.binding.rvSf.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mFragment.getPresenter().getData(mFragment.getActivity(), mFragment.page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mFragment.page = 1;
                mFragment.getPresenter().getData(mFragment.getActivity(), mFragment.page);

            }
        });

        moveTopAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (program!=null&&program instanceof MoveDataInfo.ResultsBean){
                    MoveDataInfo.ResultsBean bean= (MoveDataInfo.ResultsBean) program;
                    MoveDetailActivity.startMoveDetail(mFragment.getActivity(),bean.getId()+"");
                }

            }
        });


    }

    public void setData(BaseInfo<MoveDataInfo> data) {
        if (data == null) return;
        if (data.getData() != null && data.getData().getResults() != null) {

            if (mFragment.page == 1) {
                moveTopAdapter.getList().clear();
                moveTopAdapter.notifyDataSetChanged();
            }
            moveTopAdapter.addItems(data.getData().getResults());
            if (mFragment.page < data.getData().getTotal_pages())
                mFragment.page += 1;
        }

        mFragment.binding.rvSf.finishLoadMore(1000, true, mFragment.page > data.getData().getTotal_pages());

        mFragment.binding.rvSf.finishRefresh(1000, true);
    }


    public void showMessage(String message) {
        mFragment.binding.rvSf.finishLoadMore();
        mFragment.binding.rvSf.finishRefresh();
        if (mFragment == null) return;
        AlertUtil.showDeftToast(mFragment.getActivity(), message);
    }


}
