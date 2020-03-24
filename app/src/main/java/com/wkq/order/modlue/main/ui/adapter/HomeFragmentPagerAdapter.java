package com.wkq.order.modlue.main.ui.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wkq.order.modlue.developer.ui.fragment.DeveloperFragment;
import com.wkq.order.modlue.htmlmove.ui.fragment.HomeHtmlMoveFragment;
import com.wkq.order.modlue.main.ui.fragment.MoveDbComingFragment;
import com.wkq.order.modlue.main.ui.fragment.MoveInformationFragment;
import com.wkq.order.modlue.main.ui.fragment.MoviesFragment;
import com.wkq.order.modlue.novel.ui.fragment.NovelFragment;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    Context context;

    public HomeFragmentPagerAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override

    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {

            case 0:
//                fragment = MoveDbComingFragment.newInstance(context);
                fragment = HomeHtmlMoveFragment.newInstance();


                break;
            case 1:
                fragment = MoveInformationFragment.newInstance(context);

                break;
            case 2:
                fragment = NovelFragment.newInstance();

                break;

            case 3:
                fragment = DeveloperFragment.newInstance();

                break;

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
