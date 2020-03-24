package com.wkq.order.modlue.main.frame.view;

import android.util.Log;
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
import com.wkq.baseLib.utlis.RandomUtil;
import com.wkq.order.modlue.web.ui.EasyWebActivity;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.database.dao.HomeTopBannerInfo;
import com.wkq.database.dao.MoveDbDataHitory;
import com.wkq.database.utils.DataBaseUtils;
import com.wkq.net.BaseInfo;
import com.wkq.net.model.MoveDataInfo;
import com.wkq.order.R;
import com.wkq.order.modlue.main.modle.BannerInfo;
import com.wkq.order.modlue.main.modle.MovesTobWebInfo;
import com.wkq.order.modlue.main.ui.adapter.MoveDbComingAdapter;
import com.wkq.order.modlue.main.ui.adapter.MovesTopWebAdapter;
import com.wkq.order.modlue.main.ui.fragment.MoveDbComingFragment;
import com.wkq.order.modlue.move.ui.MoveDetailActivity;
import com.wkq.order.modlue.web.ui.VideoSiteActivity;
import com.wkq.order.modlue.web.ui.VideoWebListActivity;
import com.wkq.order.utils.BannerImageLoader;
import com.wkq.order.utils.Constant;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DynamicTimeFormat;
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

import static com.wkq.order.utils.Constant.MOVE_DB_HOME_BANNER_KEY;
import static com.wkq.order.utils.Constant.MOVE_DB_HOME_DATA_KEY;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class MoveDbComingView implements MvpView {
    MoveDbComingFragment mFragment;
    private MoveDbComingAdapter moviesAdapter;
    private List<BannerInfo> mBannerBeanList = new ArrayList<>();

    public MoveDbComingView(MoveDbComingFragment moveDbComingFragment) {
        mFragment = moveDbComingFragment;
    }


    public void initView() {
        initBanner();
        initToolBar();

        initRefush();

        moviesAdapter = new MoveDbComingAdapter(mFragment.getActivity());

        mFragment.binding.rvMovies.setLayoutManager(new LinearLayoutManager(mFragment.getActivity()));


        mFragment.binding.rvMovies.setAdapter(moviesAdapter);

        moviesAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                MoveDataInfo.ResultsBean bean = (MoveDataInfo.ResultsBean) program;
                MoveDetailActivity.startMoveDetail(mFragment.getActivity(), bean.getId() + "");


            }
        });

        if (DataBaseUtils.getMoveDbHistoryData(mFragment.getActivity(), MOVE_DB_HOME_DATA_KEY) != null) {
            MoveDbDataHitory info = DataBaseUtils.getMoveDbHistoryData(mFragment.getActivity(), MOVE_DB_HOME_DATA_KEY);
            Gson gson = new Gson();
            MoveDataInfo historyData = gson.fromJson(info.getData(), MoveDataInfo.class);
            moviesAdapter.addItems(historyData.getResults());

        }
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(mFragment.getActivity(), LinearLayoutManager.HORIZONTAL, false);

        //初始化网页点击的布局
        mFragment.binding.rvWeb.setLayoutManager(layoutManager2);

        MovesTopWebAdapter movesTopWebAdapter = new MovesTopWebAdapter(mFragment.getActivity());

        mFragment.binding.rvWeb.setAdapter(movesTopWebAdapter);

        List<Integer> pics = new ArrayList<>();

        pics.add(R.mipmap.movie_2);
        pics.add(R.mipmap.movie_3);
        pics.add(R.mipmap.movie_4);

        pics.add(R.mipmap.movie_6);
        pics.add(R.mipmap.movie_7);
        pics.add(R.mipmap.movie_8);
        pics.add(R.mipmap.movie_9);
        long seed1 = System.nanoTime();
        int END = pics.size() - 1;

        SecureRandom sr = new SecureRandom();
        List<MovesTobWebInfo> webInfos = new ArrayList<>();
        webInfos.add(new MovesTobWebInfo("https://v.qq.com/", "腾讯视频", pics.get(sr.nextInt(END))));

        webInfos.add(new MovesTobWebInfo("https://www.iqiyi.com/", "爱奇艺", pics.get(sr.nextInt(END))));
        webInfos.add(new MovesTobWebInfo("https://www.youku.com/", "优酷视频", pics.get(sr.nextInt(END))));
        webInfos.add(new MovesTobWebInfo("https://tv.sohu.com/", "搜狐视频", pics.get(sr.nextInt(END))));
        webInfos.add(new MovesTobWebInfo("http://www.pptv.com/", "PPTV视频", pics.get(sr.nextInt(END))));
        webInfos.add(new MovesTobWebInfo("http://www.le.com/", "乐视视频", pics.get(sr.nextInt(END))));

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

    private void initRefush() {

        ClassicsHeader header = new ClassicsHeader(mFragment.getActivity());
        ClassicsFooter footer = new ClassicsFooter(mFragment.getActivity());
        header.setProgressDrawable(mFragment.getResources().getDrawable(R.drawable.ic_progress_puzzle));
        header.setBackgroundColor(mFragment.getResources().getColor(R.color.color_f4f4f4));
        header.setSpinnerStyle(SpinnerStyle.Translate);
        int delta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);

        header.setLastUpdateTime(new Date(System.currentTimeMillis() - delta));
        header.setTimeFormat(new SimpleDateFormat("更新于 MM-dd HH:mm", Locale.CHINA));
        header.setTimeFormat(new DynamicTimeFormat("更新于 %s"));

        mFragment.binding.rvSf.setEnableRefresh(false);
        mFragment.binding.rvSf.setRefreshHeader(header);
        mFragment.binding.rvSf.setRefreshFooter(footer);

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


    private void initBanner() {
        //轮播图的常规设置
        mFragment.binding.bannerMovies.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器局右显示
        //====加载Banner数据====
        mFragment.binding.bannerMovies.setImageLoader(new BannerImageLoader());//设置图片加载器
        //设置显示圆形指示器和标题（水平显示）
        mFragment.binding.bannerMovies.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);


        if (DataBaseUtils.getHomeTopData(mFragment.getActivity(), MOVE_DB_HOME_BANNER_KEY) != null) {
            HomeTopBannerInfo info = DataBaseUtils.getHomeTopData(mFragment.getActivity(), MOVE_DB_HOME_BANNER_KEY);
            Gson gson = new Gson();
            Type type = new TypeToken<List<BannerInfo>>() {
            }.getType();
            mBannerBeanList = gson.fromJson(info.getData(), type);
            playBaner();
        }

        mFragment.binding.bannerMovies.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                BannerInfo bannerInfo = mBannerBeanList.get(position);
                if (bannerInfo.getUrlPath().equals("1008611"))
                    startVideoPlay();
                else
                    MoveDetailActivity.startMoveDetail(mFragment.getActivity(), mBannerBeanList.get(position).getUrlPath());
            }
        });

    }

    private void playBaner() {
        List<String> images = new ArrayList<String>();
        List<String> titles = new ArrayList<String>();
        for (BannerInfo bannerBean : mBannerBeanList) {
            images.add(bannerBean.getImgUrl());
            titles.add(bannerBean.getTitle());
        }
        mFragment.binding.bannerMovies.setDelayTime(2000);
        mFragment.binding.bannerMovies.setImages(images);
        mFragment.binding.bannerMovies.setBannerTitles(titles);
        mFragment.binding.bannerMovies.start();
    }

    private void startVideoPlay() {

        VideoWebListActivity.startVideoWebList(mFragment.getActivity());
    }

    public void setData(BaseInfo<MoveDataInfo> data) {

        if (data.getData() != null && data.getData().getResults() != null) {
            if (mFragment.page == 1) {
                MoveDbDataHitory resultsBeans = DataBaseUtils.getMoveDbHistoryData(mFragment.getActivity(), MOVE_DB_HOME_DATA_KEY);
                if (resultsBeans == null) moviesAdapter.addItems(data.getData().getResults());

                Gson gson = new Gson();
                String dataStr = gson.toJson(data.getData());
                Log.e("banner欢迎:" ,dataStr);
                DataBaseUtils.insertMoveDbHistoryData(mFragment.getActivity(), MOVE_DB_HOME_DATA_KEY, dataStr);
            } else {
                moviesAdapter.addItems(data.getData().getResults());
            }
            mFragment.page += 1;

        }

        mFragment.binding.rvSf.finishLoadMore(1000, true, mFragment.page > data.getData().getTotal_pages());

        mFragment.binding.rvSf.finishRefresh(1000, true);


    }

    public void showMessage(String message) {
        if (mFragment == null && mFragment.getActivity() == null) return;
        AlertUtil.showDeftToast(mFragment.getActivity(), message);
    }

    public void setBanner(BaseInfo<MoveDataInfo> data) {

        if (data != null && data.getData() != null && data.getData().getResults() != null && data.getData().getResults().size() > 0) {
            int size = data.getData().getResults().size();
            List<MoveDataInfo.ResultsBean> list = data.getData().getResults();
            if (mBannerBeanList != null && mBannerBeanList.size() > 0) mBannerBeanList.clear();
            for (int i = 0; i < 5; i++) {
                int one = RandomUtil.getRandomForIntegerBounded(0, size);
                MoveDataInfo.ResultsBean info = list.get(one);
                BannerInfo bannerBean = new BannerInfo(info.getTitle(), Constant.MOVE_DB_IMG_BASE_500.concat(info.getPoster_path()), info.getId() + "");
                mBannerBeanList.add(bannerBean);
            }

            Gson gson2 = new Gson();
            String bannerStr = gson2.toJson(mBannerBeanList);

            Log.e("banner数据:" ,bannerStr);
            if (DataBaseUtils.getHomeTopData(mFragment.getActivity(), MOVE_DB_HOME_BANNER_KEY) == null) {
                playBaner();
            }
            DataBaseUtils.insertHomeTopData(mFragment.getActivity(), MOVE_DB_HOME_BANNER_KEY, bannerStr);
        }
    }

    public void showFail(String message) {
        mFragment.binding.rvSf.finishLoadMore();
        mFragment.binding.rvSf.finishRefresh();
        if (mFragment.page == 1) {
            mFragment.binding.llEmpty.setVisibility(View.VISIBLE);
            mFragment.binding.rvSf.setVisibility(View.GONE);
        } else {
            showMessage(message);
        }
    }
}
