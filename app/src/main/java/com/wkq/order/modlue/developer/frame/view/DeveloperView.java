package com.wkq.order.modlue.developer.frame.view;

import android.text.TextUtils;
import android.view.View;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.baseLib.utlis.AppUtil;
import com.wkq.baseLib.utlis.PixelsUtil;
import com.wkq.baseLib.utlis.SharedPreferencesHelper;
import com.wkq.order.BuildConfig;
import com.wkq.order.modlue.developer.model.DeveloperInfo;
import com.wkq.order.modlue.developer.ui.activity.ApiTestActivity;
import com.wkq.order.modlue.developer.ui.activity.NovelDownLoadActivity;
import com.wkq.order.modlue.developer.ui.activity.NovelSubscriptionActivity;
import com.wkq.order.modlue.developer.ui.adapter.DeveloperAdapter;
import com.wkq.order.modlue.developer.ui.fragment.DeveloperFragment;
import com.wkq.order.modlue.main.ui.activity.AboutActivity;
import com.wkq.order.modlue.main.ui.activity.AdAboutActivity;
import com.wkq.order.modlue.main.ui.activity.ContactDeveloperActivity;
import com.wkq.order.modlue.main.ui.activity.NovelHelperActivity;
import com.wkq.order.modlue.main.ui.activity.PlayHelperActivity;
import com.wkq.order.modlue.web.ui.VideoWebListActivity;
import com.wkq.order.utils.Constant;
import com.wkq.order.utils.DataBindingAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-24
 * <p>
 * 用途:
 */


public class DeveloperView implements MvpView {

    DeveloperFragment mFragment;
    private DeveloperAdapter moviesAdapter;

    public DeveloperView(DeveloperFragment fragment) {
        mFragment = fragment;
    }

    public void initView() {

        initTobBar();


        moviesAdapter = new DeveloperAdapter(mFragment.getActivity());
        mFragment.binding.rvDeveloper.setLayoutManager(new LinearLayoutManager(mFragment.getActivity()));
        mFragment.binding.rvDeveloper.setAdapter(moviesAdapter);

        List<DeveloperInfo> list = new ArrayList<>();
        list.add(new DeveloperInfo(6, "小说订阅"));
        list.add(new DeveloperInfo(7, "小说历史"));

//        list.add(new DeveloperInfo(0, "电影播放"));
        list.add(new DeveloperInfo(1, "播放电影帮助"));
        list.add(new DeveloperInfo(8, "小说阅读帮助"));
//        list.add(new DeveloperInfo(2, "广告介绍"));

        list.add(new DeveloperInfo(3, "联系开发者"));
        list.add(new DeveloperInfo(4, "关于我们"));
//        list.add(new DeveloperInfo(5, "Api测试"));


        moviesAdapter.addItems(list);

        moviesAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                DeveloperInfo info = (DeveloperInfo) program;
                switch (info.getId()) {
                    //播放视频
                    case 0:
                        VideoWebListActivity.startVideoWebList(mFragment.getActivity());
                        break;
                    case 1:
                        PlayHelperActivity.startPlayHelperActivity(mFragment.getActivity());
                        break;
                    case 2:
                        AdAboutActivity.startActivity(mFragment.getActivity());
                        break;
                    case 3:
                        ContactDeveloperActivity.startActivity(mFragment.getActivity());
                        break;
                    case 4:
                        AboutActivity.startActivity(mFragment.getActivity());
                        break;
                    case 5:
                        ApiTestActivity.startActivity(mFragment.getActivity());
                        break;
                    case 6:
                        NovelSubscriptionActivity.startActivity(mFragment.getActivity());
                        break;
                    case 7:
                        NovelDownLoadActivity.startActivity(mFragment.getActivity());
                        break;
                    case 8:
                        NovelHelperActivity.startPlayHelperActivity(mFragment.getActivity());
                        break;

                }
            }
        });

        long times = Constant.DEBUG_USE_TIME - System.currentTimeMillis();
        mFragment.binding.cDv.start(times);
        String version = AppUtil.getVersionName(mFragment.getActivity());

        mFragment.binding.tvVersion.setText("内侧测版本: " + version);

    }

    private void initTobBar() {

        mFragment.binding.refreshLayout.setEnableRefresh(false);
        mFragment.binding.refreshLayout.setEnableOverScrollDrag(true);
        mFragment.binding.refreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        mFragment.binding.refreshLayout.setEnablePureScrollMode(true);


        mFragment.binding.scrool.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            int offset = mFragment.binding.scrool.getChildAt(0).getHeight() - mFragment.binding.scrool.getHeight();
            if (offset < PixelsUtil.dip2px(120)) return;
            int topViewHeight = mFragment.binding.topViewTest.getHeight();
            mFragment.binding.topViewTest.setAlpha(Math.min((Math.max(0, (float) scrollY - 8)) / topViewHeight, 1f));
//            binding.topLayout.headImg.setAlpha(topViewHeight / Math.min((Math.max(1, (float) scrollY - 8)), 1f));
            mFragment.binding.topViewTest.setVisibility(scrollY > 8 ? View.VISIBLE : View.GONE);
        });


    }

    public void showMessage(String message) {

        if (mFragment == null || TextUtils.isEmpty(message)) return;

        AlertUtil.showDeftToast(mFragment.getActivity(), message);
    }

    public void initGuide() {

        boolean isFirst = SharedPreferencesHelper.getInstance(mFragment.getActivity()).getBoolean("isDevelopeFirst", true);
        moviesAdapter.showGuide(isFirst);
    }
}
