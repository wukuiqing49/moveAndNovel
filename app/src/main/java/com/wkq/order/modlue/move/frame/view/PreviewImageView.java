package com.wkq.order.modlue.move.frame.view;

import android.text.TextUtils;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.modlue.move.ui.PreviewImageActivity;
import com.wkq.order.modlue.move.ui.ProcessImgsActivity;
import com.wkq.order.modlue.move.ui.adapter.PreviewImgsViewPagerAdapter;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class PreviewImageView implements MvpView {

    PreviewImageActivity mActivity;


    public PreviewImageView(PreviewImageActivity mActivity) {
        this.mActivity = mActivity;
    }


    public void initView() {
        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setDarkMode(mActivity);

        PreviewImgsViewPagerAdapter previewImgAdapter = new PreviewImgsViewPagerAdapter(mActivity, mActivity.imgs);

        mActivity.binding.vp.setAdapter(previewImgAdapter);
        mActivity.binding.vp.setOffscreenPageLimit(5);
        mActivity.binding.vp.setCurrentItem(mActivity.position);
        previewImgAdapter.setOnLongClickerListener(new PreviewImgsViewPagerAdapter.OnPicItemClickListener() {
            @Override
            public void onItemLongClicker(String imgUrl) {
                if (!TextUtils.isEmpty(imgUrl))
                    ProcessImgsActivity.startActivity(mActivity, imgUrl);
            }
        });


    }
}
