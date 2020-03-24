package com.wkq.order.modlue.move.frame.view;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.baseLib.utlis.DoublePressed;
import com.wkq.baseLib.utlis.KeyboardUtils;
import com.wkq.database.dao.MoveSearchHistory;
import com.wkq.database.utils.DataBaseUtils;
import com.wkq.net.BaseInfo;
import com.wkq.net.model.MoveMTimeSearchInfo;
import com.wkq.order.R;
import com.wkq.order.modlue.htmlmove.ui.activity.MoveHtmlActivity;
import com.wkq.order.modlue.main.ui.adapter.MoveMTimeSearchAdapter;
import com.wkq.order.modlue.main.ui.adapter.MoveSearchHistoryAdapter;
import com.wkq.order.modlue.main.ui.widget.LoadingDialog;
import com.wkq.order.modlue.move.ui.SearchMTimeActivity;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DynamicTimeFormat;
import com.wkq.order.utils.StatusBarUtil;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/26
 * <p>
 * 简介:
 */
public class SearchMTiemView implements MvpView {

    SearchMTimeActivity mActivity;

    MoveSearchHistoryAdapter moveSearchHistoryAdapter;

    MoveMTimeSearchAdapter moveDbComingAdapter;
    private LoadingDialog loadingDialog;


    public SearchMTiemView(SearchMTimeActivity activity) {
        mActivity = activity;
    }

    public void initView() {

        StatusBarUtil.setStatusBarWrite(mActivity);
        StatusBarUtil.setColor(mActivity, mActivity.getResources().getColor(R.color.color_2b2b2b), 0);
        StatusBarUtil.setDarkMode(mActivity);

        mActivity.binding.rvSf.setVisibility(View.GONE);
        mActivity.binding.rlSearchHisory.setVisibility(View.VISIBLE);

        ClassicsHeader header = new ClassicsHeader(mActivity);
        ClassicsFooter footer = new ClassicsFooter(mActivity);
        header.setProgressDrawable(mActivity.getResources().getDrawable(R.drawable.ic_progress_puzzle));
        header.setBackgroundColor(mActivity.getResources().getColor(R.color.color_f4f4f4));
        header.setSpinnerStyle(SpinnerStyle.Translate);
        int delta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);

        header.setLastUpdateTime(new Date(System.currentTimeMillis() - delta));
        header.setTimeFormat(new SimpleDateFormat("更新于 MM-dd HH:mm", Locale.CHINA));
        header.setTimeFormat(new DynamicTimeFormat("更新于 %s"));

        mActivity.binding.rvSf.setEnableRefresh(false);
        mActivity.binding.rvSf.setEnableLoadMore(false);
        mActivity.binding.rvSf.setRefreshHeader(header);
        mActivity.binding.rvSf.setRefreshFooter(footer);

        moveSearchHistoryAdapter = new MoveSearchHistoryAdapter(mActivity);
        mActivity.binding.rlBack.setOnClickListener(view -> mActivity.finish());
        mActivity.binding.rlSearch.setOnClickListener(view -> {
            if (mActivity != null && mActivity.getPresenter() != null) {
                KeyboardUtils.hideSoftInput(mActivity);
                if (DoublePressed.onDoublePressed()) return;
                if (mActivity.binding.etSearch.getText() != null) {
                    showLoading();
                    mActivity.getPresenter().searchData(mActivity, mActivity.binding.etSearch.getText().toString());

                    DataBaseUtils.insertHistoryData(mActivity, mActivity.binding.etSearch.getText().toString());
                    mActivity.getPresenter().searchData(mActivity, mActivity.binding.etSearch.getText().toString());
                } else {
                    showMessage("请输入查询的电影名称");
                }
            }

        });

        mActivity.binding.rvSearchHistory.setLayoutManager(new LinearLayoutManager(mActivity));
        mActivity.binding.rvSearchHistory.setAdapter(moveSearchHistoryAdapter);
        List<MoveSearchHistory> historyList = DataBaseUtils.getMoveHistoryData(mActivity);
        if (historyList == null || historyList.size() == 0) {
            mActivity.binding.rlEmpty.setVisibility(View.VISIBLE);
            mActivity.binding.rvSearchHistory.setVisibility(View.GONE);
        } else {
            mActivity.binding.rlEmpty.setVisibility(View.GONE);
            mActivity.binding.rvSearchHistory.setVisibility(View.VISIBLE);
            Collections.reverse(historyList);
            moveSearchHistoryAdapter.addItems(historyList);
        }

        mActivity.binding.rvSearch.setLayoutManager(new LinearLayoutManager(mActivity));
        moveDbComingAdapter = new MoveMTimeSearchAdapter(mActivity);
        mActivity.binding.rvSearch.setAdapter(moveDbComingAdapter);

        moveDbComingAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (program != null && program instanceof MoveMTimeSearchInfo.ValueBean.MovieResultBean.MoreMoviesBean) {
                    if (DoublePressed.onDoublePressed()) return;
                    MoveMTimeSearchInfo.ValueBean.MovieResultBean.MoreMoviesBean bean = (MoveMTimeSearchInfo.ValueBean.MovieResultBean.MoreMoviesBean) program;
                    if (bean != null)
                        MoveHtmlActivity.startActivity(mActivity, bean.getMovieId() + "", bean.getCover());

                }
            }
        });
        moveSearchHistoryAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (program != null && program instanceof MoveSearchHistory) {
                    if (DoublePressed.onDoublePressed()) return;
                    MoveSearchHistory bean = (MoveSearchHistory) program;
                    if (bean != null && bean.getMoveName() != null) {
                        mActivity.getPresenter().searchData(mActivity, bean.getMoveName());
                    }

                }
            }
        });
    }

    public void setSearchData(BaseInfo<MoveMTimeSearchInfo> data) {
        if (data != null && data.getData() != null) {
            mActivity.binding.rvSf.setVisibility(View.VISIBLE);
            mActivity.binding.rlSearchHisory.setVisibility(View.GONE);
            mActivity.binding.rlEmpty.setVisibility(View.GONE);
            if (data.getData() != null && data.getData().getValue() != null && data.getData().getValue().getMovieResult() != null && data.getData().getValue().getMovieResult().getMoreMovies() != null) {
                moveDbComingAdapter.addItems(data.getData().getValue().getMovieResult().getMoreMovies());
            } else {
                mActivity.binding.rvSf.setVisibility(View.GONE);
                mActivity.binding.rlSearchHisory.setVisibility(View.GONE);
                mActivity.binding.rlEmpty.setVisibility(View.VISIBLE);
            }

        }

    }

    public void showLoading() {
        if (loadingDialog == null)
            loadingDialog = new LoadingDialog(mActivity);
        loadingDialog.show();
    }

    public void hindLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    public void showMessage(String message) {
        if (mActivity == null) return;
        AlertUtil.showDeftToast(mActivity, message);

    }
}
