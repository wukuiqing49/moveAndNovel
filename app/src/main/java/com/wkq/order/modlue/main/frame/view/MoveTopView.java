package com.wkq.order.modlue.main.frame.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
import com.wkq.order.modlue.main.ui.adapter.MoveTopAdapter;
import com.wkq.order.modlue.main.ui.fragment.MoveTopFragment;
import com.wkq.order.modlue.main.ui.widget.StaggeredDividerItemDecoration;
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


public class MoveTopView implements MvpView {

    MoveTopFragment mFragment;
    private MoveTopAdapter moveTopAdapter;

    public MoveTopView(MoveTopFragment mFragment) {
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


        moveTopAdapter = new MoveTopAdapter(mFragment.getActivity());

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//防止Item切换
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        final int spanCount = 2;
        mFragment.binding.rvContent.addItemDecoration(new StaggeredDividerItemDecoration(mFragment.getActivity(),10,spanCount));

        mFragment.binding.rvContent.setLayoutManager(layoutManager);


        mFragment.binding.rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int[] first = new int[spanCount];
                layoutManager.findFirstCompletelyVisibleItemPositions(first);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (first[0] == 1 || first[1] == 1)) {
                    layoutManager.invalidateSpanAssignments();
                }
            }
        });


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
                if (program != null && program instanceof MoveDataInfo.ResultsBean) {
                    MoveDataInfo.ResultsBean bean = (MoveDataInfo.ResultsBean) program;
                    MoveDetailActivity.startMoveDetail(mFragment.getActivity(), bean.getId() + "");
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
