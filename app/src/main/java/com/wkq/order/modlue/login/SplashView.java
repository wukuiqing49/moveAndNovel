package com.wkq.order.modlue.login;


import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.database.utils.DataBaseUtils;
import com.wkq.order.modlue.main.ui.activity.HomeActivity;
import com.wkq.order.utils.Constant;
import com.wkq.order.utils.MoveDbDataSaveUtlis;
import com.wkq.order.utils.StatusBarUtil;

import cdc.sed.yff.AdManager;
import cdc.sed.yff.nm.sp.SplashViewSettings;
import cdc.sed.yff.nm.sp.SpotListener;
import cdc.sed.yff.nm.sp.SpotManager;
import cdc.sed.yff.nm.sp.SpotRequestListener;

import static com.wkq.order.utils.Constant.MOVE_DB_HOME_BANNER_KEY;
import static com.wkq.order.utils.Constant.MOVE_DB_HOME_DATA_KEY;
import static com.wkq.order.utils.Constant.MOVE_DB_HOME_HTML_MTIME;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class SplashView implements MvpView, SpotRequestListener {
    SplashActivity mActivity;

    String TAG = "广告";


    public SplashView(SplashActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        initLoad();

        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setLightMode(mActivity);

        initData();




    }

    private void initData() {
        MoveDbDataSaveUtlis.putType(mActivity);
        //banner
        if (DataBaseUtils.getHomeTopData(mActivity, MOVE_DB_HOME_BANNER_KEY) == null) {
            DataBaseUtils.insertHomeTopData(mActivity, MOVE_DB_HOME_BANNER_KEY, Constant.MOVE_INIT_BANNER);
        }
        //banner  MT  时光网的初始化数据
        if (DataBaseUtils.getMoveHtmlHome(mActivity, MOVE_DB_HOME_HTML_MTIME) == null) {
            DataBaseUtils.insertMoveHtmlHome(mActivity, MOVE_DB_HOME_HTML_MTIME, Constant.MOVE_INIT_HOME_HTML);
        }
        //初始化欢迎页面
        if (DataBaseUtils.getMoveDbHistoryData(mActivity, MOVE_DB_HOME_DATA_KEY) == null) {
            DataBaseUtils.insertMoveDbHistoryData(mActivity, MOVE_DB_HOME_DATA_KEY, Constant.MOVE_INIT_UP_COMING);
        }
    }


    private void initLoad() {
    }

    public void jumpHomeActivity() {
        HomeActivity.startPlayHelperActivity(mActivity);
        mActivity.finish();
    }


    public void initUMIAd() {
        AdManager.getInstance(mActivity).init(Constant.AD_UMI_APPID, Constant.AD_UMI_PASSWORLD, true);
        SplashViewSettings splashViewSettings = new SplashViewSettings();
        SpotManager.getInstance(mActivity).requestSpot(this);

        splashViewSettings.setAutoJumpToTargetWhenShowFailed(true);
        splashViewSettings.setTargetClass(HomeActivity.class);

        splashViewSettings.setSplashViewContainer(mActivity.binding.splashContainer);
// 使用自定义布局参数
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        splashViewSettings.setSplashViewContainerAndLayoutParams(mActivity.binding.splashContainer, params);

        SpotManager.getInstance(mActivity).showSplash(mActivity,
                splashViewSettings, new SpotListener() {
                    @Override
                    public void onShowSuccess() {
                        Log.e("", "");
                    }

                    @Override
                    public void onShowFailed(int i) {
                        Log.e("", "");
//                        showMessage("广告加载失败");
                        jumpHomeActivity();
                    }

                    @Override
                    public void onSpotClosed() {
                        Log.e("", "");
                    }

                    @Override
                    public void onSpotClicked(boolean b) {
                        Log.e("", "");
                    }
                });


    }

    @Override
    public void onRequestSuccess() {
        Log.e("", "");
    }

    @Override
    public void onRequestFailed(int i) {
        Log.e("", "");
//        showMessage("预加载失败");
    }

    public void showMessage(String message) {
        if (mActivity == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mActivity, message);
    }
}
