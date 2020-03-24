package com.wkq.order.modlue.htmlmove.frame.view;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.database.dao.MoveDbDataHitory;
import com.wkq.database.utils.DataBaseUtils;
import com.wkq.net.model.MoveDataInfo;
import com.wkq.order.R;
import com.wkq.order.modlue.htmlmove.ui.activity.MoveHtmlActivity;
import com.wkq.order.modlue.htmlmove.ui.fragment.HomeHtmlMoveFragment;
import com.wkq.order.modlue.main.modle.MovesTobWebInfo;
import com.wkq.order.modlue.htmlmove.ui.adapter.MoveHomeHtmlMTimeAdapter;
import com.wkq.order.modlue.main.ui.adapter.MovesTopWebAdapter;
import com.wkq.order.modlue.move.ui.MoveDetailActivity;
import com.wkq.order.modlue.web.ui.EasyWebActivity;
import com.wkq.order.modlue.web.ui.VideoWebListActivity;
import com.wkq.order.utils.BannerImageLoader;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DynamicTimeFormat;
import com.wkq.order.utils.MoveDbDataSaveUtlis;
import com.wkq.order.utils.StatusBarUtil;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import wkq.com.lib_move.model.MTimeHomeBean;
import wkq.com.lib_move.model.MoveInfo;
import wkq.com.lib_move.site.MTimeSite;

import static com.wkq.order.utils.Constant.MOVE_DB_HOME_HTML_MTIME;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-03
 * <p>
 * 用途:
 */


public class HomeHtmlMoveView implements MvpView {
    HomeHtmlMoveFragment mFragment;

    Gson gson = new Gson();

    private List<MoveInfo> mTopList = new ArrayList<>();
    private MoveHomeHtmlMTimeAdapter moviesAdapter;


    public HomeHtmlMoveView(HomeHtmlMoveFragment mFragment) {
        this.mFragment = mFragment;
    }


    public void initView() {

        initToolBar();
        initRefush();
        initBanner();
        initTopWeb();
        moviesAdapter = new MoveHomeHtmlMTimeAdapter(mFragment.getActivity());
        mFragment.binding.rvMovies.setLayoutManager(new LinearLayoutManager(mFragment.getActivity()));

        mFragment.binding.rvMovies.setAdapter(moviesAdapter);

        moviesAdapter.setItemClickListener(new MoveHomeHtmlMTimeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MoveInfo info) {

                MoveHtmlActivity.startActivity(mFragment.getActivity(), MTimeSite.getMoveId(info.getMoveHref()), info.getMoveCover());

            }
        });

        MoveDbDataHitory resultsBeans = DataBaseUtils.getMoveHtmlHome(mFragment.getActivity(), MOVE_DB_HOME_HTML_MTIME);
        if (MoveDbDataSaveUtlis.mtJson2list(resultsBeans.getData()) != null && MoveDbDataSaveUtlis.mtJson2list(resultsBeans.getData()).size() > 0) {
            moviesAdapter.addItems(MoveDbDataSaveUtlis.mtJson2list(resultsBeans.getData()));
        } else {
            showFail();
        }


    }

    /**
     * 初始化顶部 网址数据
     */
    private void initTopWeb() {


        LinearLayoutManager layoutManager2 = new LinearLayoutManager(mFragment.getActivity(), LinearLayoutManager.HORIZONTAL, false);

        //初始化网页点击的布局
        mFragment.binding.rvWeb.setLayoutManager(layoutManager2);
        MovesTopWebAdapter movesTopWebAdapter = new MovesTopWebAdapter(mFragment.getActivity());
        mFragment.binding.rvWeb.setAdapter(movesTopWebAdapter);

        List<MovesTobWebInfo> webInfos = new ArrayList<>();
        webInfos.add(new MovesTobWebInfo("https://v.qq.com/", "腾讯视频", R.mipmap.movie_2));

        webInfos.add(new MovesTobWebInfo("https://www.iqiyi.com/", "爱奇艺", R.mipmap.movie_3));
        webInfos.add(new MovesTobWebInfo("https://www.youku.com/", "优酷视频", R.mipmap.movie_4));
        webInfos.add(new MovesTobWebInfo("https://tv.sohu.com/", "搜狐视频", R.mipmap.movie_6));
        webInfos.add(new MovesTobWebInfo("http://www.pptv.com/", "PPTV视频", R.mipmap.movie_7));
        webInfos.add(new MovesTobWebInfo("http://www.le.com/", "乐视视频", R.mipmap.movie_8));

        movesTopWebAdapter.addItems(webInfos);

        movesTopWebAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (program != null && program instanceof MovesTobWebInfo) {
                    MovesTobWebInfo info = (MovesTobWebInfo) program;
                    EasyWebActivity.startActivity(mFragment.getActivity(), info.getWebUrl());
                }

            }
        });
    }


    private void initToolBar() {


        mFragment.binding.appbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (!mFragment.isExpend) {
                        StatusBarUtil.setDarkMode(mFragment.getActivity());
                        mFragment.isExpend = !mFragment.isExpend;

                        mFragment.binding.toolbar.setVisibility(View.VISIBLE);
                        mFragment.binding.tvTitle.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (mFragment.isExpend) {
                        StatusBarUtil.setDarkMode(mFragment.getActivity());
                        mFragment.isExpend = !mFragment.isExpend;
                        mFragment.binding.toolbar.setVisibility(View.GONE);

                        mFragment.binding.tvTitle.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void initRefush() {
        mFragment.binding.rvSf.setEnableRefresh(false);
        mFragment.binding.rvSf.setEnableLoadMore(false);
    }


    private void initBanner() {
        //轮播图的常规设置
        mFragment.binding.bannerMovies.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器局右显示
        //====加载Banner数据====
        mFragment.binding.bannerMovies.setImageLoader(new BannerImageLoader());//设置图片加载器
        //设置显示圆形指示器和标题（水平显示）
        mFragment.binding.bannerMovies.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);


        if (DataBaseUtils.getMoveHtmlHome(mFragment.getActivity(), MOVE_DB_HOME_HTML_MTIME) != null) {
            MoveDbDataHitory info = DataBaseUtils.getMoveHtmlHome(mFragment.getActivity(), MOVE_DB_HOME_HTML_MTIME);
            if (MoveDbDataSaveUtlis.mtJson2list(info.getData()) != null && MoveDbDataSaveUtlis.mtJson2list(info.getData()).size() > 0) {
                mTopList = MoveDbDataSaveUtlis.mtJson2list(info.getData()).get(0).getMoveInfos();
                playBaner();
            }

        }

        mFragment.binding.bannerMovies.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

                MoveHtmlActivity.startActivity(mFragment.getActivity(),

                        MTimeSite.getMoveId(mTopList.get(position).getMoveHref())

                        , mTopList.get(position).getMoveCover());

            }
        });

    }

    private void playBaner() {
        List<String> images = new ArrayList<String>();
        List<String> titles = new ArrayList<String>();
        for (MoveInfo bannerBean : mTopList) {
            images.add(bannerBean.getMoveCover());
            titles.add(bannerBean.getMoveName());
        }
        mFragment.binding.bannerMovies.setDelayTime(2000);
        mFragment.binding.bannerMovies.setImages(images);
        mFragment.binding.bannerMovies.setBannerTitles(titles);
        mFragment.binding.bannerMovies.start();
    }


    public void setData(List<MTimeHomeBean> moveInfoList) {

        if (moveInfoList != null && moveInfoList.size() > 0) {
            String dataStr = gson.toJson(moveInfoList);
            DataBaseUtils.insertMoveHtmlHome(mFragment.getActivity(), MOVE_DB_HOME_HTML_MTIME, dataStr);
//            moveInfoList.remove(0);
//            moviesAdapter.addItems(moveInfoList);


        } else {
            MoveDbDataHitory resultsBeans = DataBaseUtils.getMoveHtmlHome(mFragment.getActivity(), MOVE_DB_HOME_HTML_MTIME);
            if (MoveDbDataSaveUtlis.mtJson2list(resultsBeans.getData()) != null && MoveDbDataSaveUtlis.mtJson2list(resultsBeans.getData()).size() > 0) {
                moviesAdapter.addItems(MoveDbDataSaveUtlis.mtJson2list(resultsBeans.getData()));
            } else {
                showFail();
            }
        }

    }

    public void showFail() {
        mFragment.binding.rvSf.finishLoadMore();
        mFragment.binding.rvSf.finishRefresh();
        mFragment.binding.llEmpty.setVisibility(View.VISIBLE);
        mFragment.binding.rvSf.setVisibility(View.GONE);
    }

    public void showMessage(String message) {
        if (mFragment == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mFragment.getActivity(), message);

    }


}
