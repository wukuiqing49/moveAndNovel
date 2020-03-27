package com.wkq.order.modlue.main.frame.view;

import android.text.TextUtils;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;


import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.database.utils.DataBaseUtils;
import com.wkq.order.modlue.main.ui.activity.ContactDeveloperActivity;
import com.wkq.order.utils.Constant;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.main.ui.activity.HomeActivity;
import com.wkq.order.modlue.main.ui.adapter.HomeFragmentPagerAdapter;
import com.wkq.order.modlue.main.ui.widget.QMUITabSegment;
import com.wkq.order.modlue.web.ui.VideoWebListActivity;

import static com.wkq.order.utils.Constant.DEBUG_USE_TIME;
import static com.wkq.order.utils.Constant.MOVE_DB_HOME_BANNER_KEY;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class HomeView implements MvpView {

    HomeActivity mActivity;

    public HomeView(HomeActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {


        if (System.currentTimeMillis() >= DEBUG_USE_TIME) {
            showMessage("试用结束请联系开发者!!!");
            ContactDeveloperActivity.startActivity(mActivity);
            mActivity.finish();

        }
        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);

        HomeFragmentPagerAdapter homeFragmentPagerAdapter = new HomeFragmentPagerAdapter(mActivity, mActivity.getSupportFragmentManager());

        QMUITabSegment.Tab component = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(mActivity, R.drawable.ic_index_home_gray),
                ContextCompat.getDrawable(mActivity, R.drawable.ic_index_home),
                "电影", false);

        QMUITabSegment.Tab util = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(mActivity, R.drawable.ic_index_dashboard_gray),
                ContextCompat.getDrawable(mActivity, R.drawable.ic_index_dashboard),
                "资讯", false);
        QMUITabSegment.Tab novel = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(mActivity, R.drawable.ic_index_notifications_gray),
                ContextCompat.getDrawable(mActivity, R.drawable.ic_index_notifications),
                "小说", false);
        QMUITabSegment.Tab lab = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(mActivity, R.drawable.ic_fly_refresh_developer_gray),
                ContextCompat.getDrawable(mActivity, R.drawable.ic_fly_refresh_developer),
                "开发者", false);


//        mActivity.binding.tabs.setHasIndicator(false);

        mActivity.binding.tabs.addTab(component)
                .addTab(util)
                .addTab(novel)
                .addTab(lab);

        mActivity.binding.tabs.setDefaultNormalColor(mActivity.getResources().getColor(R.color.color_n));
        mActivity.binding.tabs.setDefaultSelectedColor(mActivity.getResources().getColor(R.color.color_s));
        mActivity.binding.pager.setAdapter(homeFragmentPagerAdapter);

        mActivity.binding.pager.setOffscreenPageLimit(4);

        mActivity.binding.tabs.setupWithViewPager(mActivity.binding.pager, false);

        mActivity.binding.tabs.setOnTabClickListener(new QMUITabSegment.OnTabClickListener() {
            @Override
            public void onTabClick(int index) {
                switch (index) {

                    case 0:
                        StatusBarUtil.setDarkMode(mActivity);
                        break;
                    case 1:
                        StatusBarUtil.setDarkMode(mActivity);
                        break;
                    case 2:
                        StatusBarUtil.setDarkMode(mActivity);
//

                        break;
                }
            }
        });

        mActivity.binding.pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mActivity.binding.cdPlay.setVisibility(View.GONE);

                } else {
                    mActivity.binding.cdPlay.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mActivity.binding.cdPlay.setOnClickListener(view -> {
            VideoWebListActivity.startVideoWebList(mActivity);
        });


    }

    public void showMessage(String message) {
        if (mActivity == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mActivity, message);
    }
}
