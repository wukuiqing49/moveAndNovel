package com.wkq.order.modlue.htmlmove.frame.view;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.htmlmove.ui.activity.MoveHtmlActivity;
import com.wkq.order.modlue.htmlmove.ui.adapter.MoveHomeHtmlMTimeAdapter;
import com.wkq.order.modlue.htmlmove.ui.adapter.MoveHtmlMTimeTopAdapter;
import com.wkq.order.modlue.htmlmove.ui.fragment.HomeHtmlMoveTopFragment;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DynamicTimeFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import wkq.com.lib_move.model.MoveInfo;
import wkq.com.lib_move.model.MoveTopInfo;
import wkq.com.lib_move.site.MTimeSite;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-03
 * <p>
 * 用途:
 */


public class HomeHtmlMoveTopView implements MvpView {
    HomeHtmlMoveTopFragment mFragment;

    private MoveHtmlMTimeTopAdapter moviesAdapter;


    public HomeHtmlMoveTopView(HomeHtmlMoveTopFragment mFragment) {
        this.mFragment = mFragment;
    }


    public void initView() {
        mFragment.binding.rvContent.setLayoutManager(new LinearLayoutManager(mFragment.getActivity()));
        moviesAdapter = new MoveHtmlMTimeTopAdapter(mFragment.getActivity());
        mFragment.binding.rvContent.setAdapter(moviesAdapter);

        moviesAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                MoveTopInfo info = (MoveTopInfo) program;
                MoveHtmlActivity.startActivity(mFragment.getActivity(), MTimeSite.getMoveId(info.getMoveHref()),info.getMoveCover());
            }
        });
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

        mFragment.binding.rvSf.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mFragment.getPresenter().getData(mFragment.url, mFragment.page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mFragment.page = 1;
                mFragment.getPresenter().getData(mFragment.url, mFragment.page);

            }
        });

    }


    public void setData(List<MoveTopInfo> moveInfoList) {
        if (moveInfoList != null && moveInfoList.size() > 0) {
            moviesAdapter.addItems(moveInfoList);
            mFragment.pageNum = moveInfoList.get(0).getPageNum();
            mFragment.page++;

        }

        mFragment.binding.rvSf.finishLoadMore(1000, true, mFragment.page > mFragment.pageNum);
        mFragment.binding.rvSf.finishRefresh(1000, true);

    }

    public void showFail() {
        mFragment.binding.rvSf.finishLoadMore();
        mFragment.binding.rvSf.finishRefresh();
//        mFragment.binding.llEmpty.setVisibility(View.VISIBLE);
        mFragment.binding.rvSf.setVisibility(View.GONE);
    }

    public void showMessage(String message) {
        if (mFragment == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mFragment.getActivity(), message);

    }


}
