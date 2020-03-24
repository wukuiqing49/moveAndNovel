package com.wkq.order.modlue.main.ui.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wkq.order.modlue.developer.ui.fragment.DeveloperFragment;
import com.wkq.order.modlue.htmlmove.ui.fragment.HomeHtmlMoveTopFragment;
import com.wkq.order.modlue.main.ui.fragment.MoveComingFragment;
import com.wkq.order.modlue.main.ui.fragment.MoveDbComingFragment;
import com.wkq.order.modlue.main.ui.fragment.MoveHotFragment;
import com.wkq.order.modlue.main.ui.fragment.MoveTopFragment;
import com.wkq.order.modlue.main.ui.fragment.MoviesFragment;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MoveFragmentPagerAdapter extends FragmentPagerAdapter {

    Context context;

    public MoveFragmentPagerAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {

//            case 0:
//                fragment = MoveHotFragment.newInstance(context);
//
//
//                break;
//            case 1:
//                fragment = MoveComingFragment.newInstance(context);
//
//                break;
//            case 2:
//                fragment = MoveTopFragment.newInstance(context);
//
//                break;


            case 0:
                fragment = HomeHtmlMoveTopFragment.newInstance("http://video.mtime.com/search/?h=movie&s=1&p=");


                break;
            case 1:
                fragment = HomeHtmlMoveTopFragment.newInstance("http://video.mtime.com/search/?h=movie&s=2&p=");

                break;
            case 2:
                fragment = HomeHtmlMoveTopFragment.newInstance("http://video.mtime.com/search/?h=movie&s=3&p=");

                break;

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
