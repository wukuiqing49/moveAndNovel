package com.wkq.order.modlue.main.frame.view;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.net.BaseInfo;
import com.wkq.net.model.MovieInTheatersBean;
import com.wkq.order.modlue.main.modle.BannerInfo;
import com.wkq.order.modlue.main.modle.VideoWebInfo;
import com.wkq.order.modlue.web.ui.VideoWebListActivity;
import com.wkq.order.modlue.main.ui.adapter.MoviesAdapter;
import com.wkq.order.modlue.main.ui.fragment.MoviesFragment;
import com.wkq.order.utils.BannerImageLoader;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MoviesView implements MvpView {

    MoviesFragment mFragment;
    private MoviesAdapter moviesAdapter;

    List<VideoWebInfo> videoList = new ArrayList<>();

    public MoviesView(MoviesFragment mFragment) {
        this.mFragment = mFragment;
    }

    public void initView() {
        initBanner();


        moviesAdapter = new MoviesAdapter(mFragment.getActivity());

        mFragment.binding.rvMovies.setLayoutManager(new LinearLayoutManager(mFragment.getActivity()));
        mFragment.binding.rvMovies.setAdapter(moviesAdapter);

    }
    private List<BannerInfo> mBannerBeanList=new ArrayList<>();
    private void initBanner() {
        mBannerBeanList = new ArrayList<BannerInfo>();
        //这里手动添加一些测试数据
        BannerInfo bannerBean1 = new BannerInfo();
        bannerBean1.setTitle("免费看电影");
        bannerBean1.setImgUrl("https://b.bdstatic.com/boxlib/20180120/2018012017100383423448679.jpg");
        bannerBean1.setUrlPath("http://pic.chinadaily.com.cn/2018-01/20/content_35544757.htm");

        BannerInfo bannerBean2 = new BannerInfo();
        bannerBean2.setTitle("成都熊猫基地太阳产房全新升级");
        bannerBean2.setImgUrl("https://b.bdstatic.com/boxlib/20180120/2018012017100311270281486.jpg");
        bannerBean2.setUrlPath("http://pic.chinadaily.com.cn/2018-01/20/content_35544758.htm");

        BannerInfo bannerBean3 = new BannerInfo();
        bannerBean3.setTitle("长沙“90后”交警用手绘记录交警故事");
        bannerBean3.setImgUrl("https://b.bdstatic.com/boxlib/20180120/2018012017100392134086973.jpg");
        bannerBean3.setUrlPath("http://pic.chinadaily.com.cn/2018-01/20/content_35544759.htm");

        mBannerBeanList.add(bannerBean1);
        mBannerBeanList.add(bannerBean2);
        mBannerBeanList.add(bannerBean3);

        List<String> images = new ArrayList<String>();
        List<String> titles = new ArrayList<String>();
        for(BannerInfo bannerBean : mBannerBeanList){
            images.add(bannerBean.getImgUrl());
            titles.add(bannerBean.getTitle());
        }


        //轮播图的常规设置
        mFragment.binding.bannerMovies.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器局右显示
        //====加载Banner数据====
        mFragment.binding.bannerMovies.setImageLoader(new BannerImageLoader());//设置图片加载器
        //设置显示圆形指示器和标题（水平显示）
        mFragment.binding.bannerMovies.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mFragment.binding.bannerMovies.setImages(images);
        mFragment.binding.bannerMovies.setBannerTitles(titles);

        //banner设置方法全部调用完毕时最后调用
        mFragment.binding.bannerMovies.setDelayTime(2000);
        mFragment.binding.bannerMovies.start();

        mFragment.binding.bannerMovies.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                startVideoPlay();

            }
        });



    }

    private void startVideoPlay() {

        VideoWebListActivity.startVideoWebList(mFragment.getActivity());
    }

    public void setData(BaseInfo<MovieInTheatersBean> data) {

        if (data.getData() != null && data.getData().getSubjects() != null) {
            moviesAdapter.addItems(data.getData().getSubjects());

        }

    }
}
