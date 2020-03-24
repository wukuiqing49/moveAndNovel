package com.wkq.order.modlue.web.view;

import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.web.model.CheckLineInfo;
import com.wkq.order.modlue.web.ui.CheckLineActivity;
import com.wkq.order.modlue.web.ui.VideoWebviewActivity;
import com.wkq.order.modlue.web.ui.adapter.CheckLineAdapter;
import com.wkq.order.utils.DataBindingAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/20
 * <p>
 * 简介:
 */
public class CheckLineView implements MvpView {

    CheckLineActivity mActivity;

    public CheckLineView(CheckLineActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {

        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setDarkMode(mActivity);

        List<CheckLineInfo> checkLines = new ArrayList<>();

        checkLines.add(new CheckLineInfo("VIP无广告", "https://jiexi.bm6ig.cn/?url="));
        checkLines.add(new CheckLineInfo("VIP无广告2", "http://demo.hao0606.com/?url="));
//        checkLines.add(new CheckLineInfo("VIP无广告3", "http://jx.1ff1.cn/?url="));
        checkLines.add(new CheckLineInfo("vip无广告初心", "http://jx.bwcxy.com/?v="));
        checkLines.add(new CheckLineInfo("Vip无广告116", "https://vip.116kan.com/?url="));
        checkLines.add(new CheckLineInfo("vip知网(搜索)", "http://www.xyyh.xyz/zwjx/?url=url="));
        checkLines.add(new CheckLineInfo("Vip一路发(无广告)", "https://www.cuan.la/?url="));
        checkLines.add(new CheckLineInfo("vip出言", "http://jx.wodym.cn/?url="));


        checkLines.add(new CheckLineInfo("大亨解析(全屏去广告)", "http://jx.cesms.cn/?url="));
        checkLines.add(new CheckLineInfo("vip星空(全屏去广告)", "https://play.fo97.cn/?url="));
//        checkLines.add(new CheckLineInfo("Vip8090(全屏去广告", "https://play.fo97.cn/?url="));
        checkLines.add(new CheckLineInfo("云解析(全屏去广告)", "http://jx.rdhk.net/?v="));
        checkLines.add(new CheckLineInfo("待定速度慢(全屏去广告)", "https://www.administratorw.com/admin.php?url="));
        checkLines.add(new CheckLineInfo("上滑动全屏去广告备用(有广告)", "http://jx.aeidu.cn/index.php?url="));







//        checkLines.add(new CheckLineInfo("重点(有广告)", "http://jx.du2.cc/?url="));
//        checkLines.add(new CheckLineInfo("重点(有广告", "http://jx.jx.jx1jx1.drgxj.com/jxjxjx1jx1/598ASJoihjUY1_d256F15.php?url=url="));
//        checkLines.add(new CheckLineInfo("速度1(有广告)", "http://jx.drgxj.com/?url="));


        CheckLineAdapter mAdapter = new CheckLineAdapter(mActivity);
        mActivity.binding.rvLine.setLayoutManager(new LinearLayoutManager(mActivity));
        mActivity.binding.rvLine.setAdapter(mAdapter);
        mAdapter.addItems(checkLines);

        mAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (v.getId() == R.id.ll_root) {
                    CheckLineInfo info = (CheckLineInfo) program;
                    if (info != null && !TextUtils.isEmpty(mActivity.videoUrl) && !TextUtils.isEmpty(info.getLineUrl())) {

                        checkAdTime(info);
//                       VideoWebviewActivity.startActivity(mActivity,info.getLineUrl().concat("http://v.qq.com/x/cover/uzuqdig87eggmiw.html?ptag=douban.movie"));

//                       WebDemoActivity.startActivity(mActivity,info.getLineUrl().concat(mActivity.videoUrl));
                    } else {
                        showMessage("无效视频地址");
                    }
                }
            }
        });
        mActivity.binding.tvCancel.setOnClickListener(v -> {
            mActivity.finish();
        });

    }

    private void checkAdTime(CheckLineInfo info) {

        VideoWebviewActivity.startActivity(mActivity, info.getLineUrl().concat(mActivity.videoUrl));
        mActivity.finish();
//        String time = DateTimeUtil.getCurrentTime();
//        DataBaseUtils.updateAdTimeInfo(mActivity, BuildConfig.APP_ID, time);
//        AdTimeInfo adTimeInfo = DataBaseUtils.getAdTimeInfo(mActivity, BuildConfig.APP_ID);
//        if (adTimeInfo != null && adTimeInfo.getAdTime().equals(time) && adTimeInfo.getAdClickCount() >= BuildConfig.APP_AD_COUNT) {
//            VideoWebviewActivity.startActivity(mActivity, info.getLineUrl().concat(mActivity.videoUrl));
//            mActivity.finish();
//        } else {
//            showMessage("点击广告");
//        }
    }

    public void showMessage(String message) {

        if (mActivity == null || mActivity.isFinishing()) return;
        AlertUtil.showDeftToast(mActivity, message);

    }
}
