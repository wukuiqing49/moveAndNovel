package com.wkq.order.modlue.main.frame.view;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.modlue.move.ui.SearchMTimeActivity;
import com.wkq.order.utils.StatusBarUtil;

import com.wkq.order.R;

import com.wkq.order.modlue.main.ui.activity.SearchActivity;
import com.wkq.order.modlue.main.ui.adapter.MoveFragmentPagerAdapter;
import com.wkq.order.modlue.main.ui.fragment.MoveInformationFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import wkq.com.lib_move.site.MTimeSite;
import wkq.com.lib_move.utlis.MoveDataCallBack;
import wkq.com.lib_move.utlis.MoveHtmlUtlis;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class MoveInformationView implements MvpView {

    List<String> titleTab = new ArrayList<>();

    MoveInformationFragment mFragment;

    public MoveInformationView(MoveInformationFragment mFragment) {
        this.mFragment = mFragment;
        titleTab.add("正在热播");
        titleTab.add("最新电影");
        titleTab.add("TOP排行榜");
    }

    public void initView() {


        StatusBarUtil.setDarkMode(mFragment.getActivity());
        MoveFragmentPagerAdapter fragmentAdapter = new MoveFragmentPagerAdapter(mFragment.getActivity(), mFragment.getChildFragmentManager());
        mFragment.binding.vpInformation.setAdapter(fragmentAdapter);

        mFragment.binding.vpInformation.setCurrentItem(0);
        mFragment.binding.vpInformation.setOffscreenPageLimit(3);


        MagicIndicator magicIndicator = (MagicIndicator) mFragment.binding.magicIndicator;
        CommonNavigator commonNavigator = new CommonNavigator(mFragment.getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titleTab == null ? 0 : titleTab.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(titleTab.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#88ffffff"));
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFragment.binding.vpInformation.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }


            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#40c4ff"));
                return null;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(mFragment.getActivity(), 15));
        titleContainer.setDividerDrawable(mFragment.getResources().getDrawable(R.drawable.simple_splitter));

        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mFragment.binding.vpInformation);

        mFragment.binding.rlSearch.setOnClickListener(view -> {
            SearchMTimeActivity.startSearch(mFragment.getActivity());
        });

    }


}
