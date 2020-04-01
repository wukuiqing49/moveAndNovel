package com.wkq.order.modlue.htmlmove.frame.view;

import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.binioter.guideview.Component;
import com.binioter.guideview.Guide;
import com.binioter.guideview.GuideBuilder;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.baseLib.utlis.DateTimeUtil;
import com.wkq.baseLib.utlis.SharedPreferencesHelper;
import com.wkq.net.BaseInfo;
import com.wkq.net.model.MTimeMoveDetailInfo;
import com.wkq.order.modlue.htmlmove.ui.activity.MoveHtmlActivity;
import com.wkq.order.modlue.htmlmove.ui.activity.ProcessVideoActivity;
import com.wkq.order.modlue.htmlmove.ui.adapter.MoveHtmlCreditsAdapter;
import com.wkq.order.modlue.htmlmove.ui.adapter.MoveHtmlImgsAdapter;
import com.wkq.order.modlue.htmlmove.ui.adapter.MoveStringAdapter;
import com.wkq.order.modlue.htmlmove.ui.widget.SimpleComponent;
import com.wkq.order.modlue.move.ui.PreviewImageActivity;
import com.wkq.order.modlue.web.ui.FullVideoActivity;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.GlideUtlis;
import com.wkq.order.utils.StatusBarUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-03
 * <p>
 * 用途:
 */


public class MoveHtmlView implements MvpView {
    MoveHtmlActivity mActivity;
    private MoveHtmlImgsAdapter imgsAdapter;
    private MoveStringAdapter awardAdapter;
    private MoveHtmlCreditsAdapter creditsAdapter;

    List<String> wites = new ArrayList<>();

    public MoveHtmlView(MoveHtmlActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        wites.add("腾讯");
        wites.add("爱奇艺");
        wites.add("优酷");
        wites.add("搜狐视频");


        initToolBar();
        mActivity.binding.rvImgs.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        mActivity.binding.rvCredits.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        mActivity.binding.rvAward.setLayoutManager(new LinearLayoutManager(mActivity));
        imgsAdapter = new MoveHtmlImgsAdapter(mActivity);

        awardAdapter = new MoveStringAdapter(mActivity);

        creditsAdapter = new MoveHtmlCreditsAdapter(mActivity);


        mActivity.binding.rvImgs.setAdapter(imgsAdapter);
        mActivity.binding.rvAward.setAdapter(awardAdapter);
        mActivity.binding.rvCredits.setAdapter(creditsAdapter);


        Glide.with(mActivity).load(mActivity.imgUrl).into(mActivity.binding.ivLoading);

        imgsAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                PreviewImageActivity.startPreViewImgs(mActivity, (ArrayList<String>) imgsAdapter.getList(), imgsAdapter.getList().indexOf((String) program));
            }
        });


    }

    private void showGuideView() {


        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(mActivity.binding.cdPlay)
                .setAlpha(150)

                .setHighTargetCorner(20)
                .setHighTargetPadding(10);

        builder.setHighTargetGraphStyle(Component.CIRCLE);

        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {

            }

            @Override
            public void onDismiss() {
                SharedPreferencesHelper.getInstance(mActivity).setValue("movePlayFirst", false);
            }
        });

        builder.addComponent(new SimpleComponent());
        Guide guide = builder.createGuide();
        guide.show(mActivity);
    }

    private void initToolBar() {
        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setDarkMode(mActivity);
        mActivity.binding.appbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (!mActivity.isExpend) {
                        mActivity.binding.tvTitle.setVisibility(View.VISIBLE);
                        mActivity.isExpend = !mActivity.isExpend;
                    }
                } else {
                    if (mActivity.isExpend) {
                        mActivity.binding.tvTitle.setVisibility(View.GONE);
                        mActivity.isExpend = !mActivity.isExpend;
                    }
                }
            }
        });

        mActivity.binding.rlBack.setOnClickListener(view -> {
            mActivity.finish();
        });
    }

    public void showFail() {

        showMessage("数据异常");
        mActivity.finish();
    }


    public void showSuccess(BaseInfo<MTimeMoveDetailInfo> data) {

        if (data == null || data.getData() == null || data.getData().getBasic() == null) {
            showMessage("数据异常");
            mActivity.finish();
        }
        mActivity.binding.setData(data.getData());


        if (data.getData().getBasic().getFestivals() != null && data.getData().getBasic().getFestivals().size() > 0) {
            List<String> awards = new ArrayList<>();
            for (MTimeMoveDetailInfo.BasicBean.FestivalsBean festival : data.getData().getBasic().getFestivals()) {
                awards.add(festival.getNameCn());
            }
            awardAdapter.addItems(awards);

        } else {
            mActivity.binding.llAward.setVisibility(View.GONE);
        }
        if (data.getData().getBasic().getActors() != null && data.getData().getBasic().getActors().size() > 0) {

            creditsAdapter.addItems(data.getData().getBasic().getActors());

        } else {
            mActivity.binding.llAward.setVisibility(View.GONE);
        }

        String genreStr = "";
        List<String> typs = data.getData().getBasic().getType();
        if (typs != null) {
            for (String genre : typs) {
                genreStr = genreStr.concat(genre).concat("/");
            }
        }

        if (TextUtils.isEmpty(genreStr)) {
            genreStr = "未知";
        } else {
            int last = genreStr.lastIndexOf("/");
            genreStr = genreStr.substring(0, last);
        }
        mActivity.binding.tvMoveType.setText(genreStr);
        String time = DateTimeUtil.getYearMouthDay(data.getData().getBasic().getReleaseDate());
        mActivity.binding.tvMoveTime.setText(time);
        Glide.with(mActivity).load(data.getData().getBasic().getImg()).into(mActivity.binding.ivIcon);

        if (TextUtils.isEmpty(data.getData().getBasic().getBigImage())) {
            Glide.with(mActivity).load(data.getData().getBasic().getImg()).into(mActivity.binding.ivDrop);
        } else {
            Glide.with(mActivity).load(data.getData().getBasic().getBigImage()).into(mActivity.binding.ivDrop);

        }

        if (data.getData().getBasic().getVideo() != null) {
            GlideUtlis.loadImg(mActivity, data.getData().getBasic().getVideo().getImg(), mActivity.binding.ivFlower);

            mActivity.binding.rlImg.setOnClickListener(view -> {

                JZVideoPlayerStandard.startFullscreen(mActivity, JZVideoPlayerStandard.class, data.getData().getBasic().getVideo().getHightUrl(), data.getData().getBasic().getVideo().getTitle());

//                FullVideoActivity.startActivity(mActivity,,);
            });
        } else {
            mActivity.binding.llRs.setVisibility(View.GONE);
        }

        mActivity.binding.tvDes.setText(data.getData().getBasic().getStory());

        if (data.getData().getBasic().getStageImg() != null && data.getData().getBasic().getStageImg().getList() != null) {
            List<String> imgs = new ArrayList<>();
            for (MTimeMoveDetailInfo.BasicBean.StageImgBean.ListBean listBean : data.getData().getBasic().getStageImg().getList()) {
                imgs.add(listBean.getImgUrl());
            }
            imgsAdapter.addItems(imgs);
        } else {
            mActivity.binding.llImgs.setVisibility(View.GONE);
        }

        BigDecimal b = new BigDecimal(String.valueOf(data.getData().getBasic().getOverallRating()));
        float star = b.floatValue();
        float starf = star / 10 * 5;
        mActivity.binding.raStar.setStar(starf);

        if (data.getData().getPlaylist() != null && data.getData().getPlaylist().size() > 0) {
            for (MTimeMoveDetailInfo.PlaylistBean playlistBean : data.getData().getPlaylist()) {
                if (wites.indexOf(playlistBean.getPlaySourceName()) >= 0) {
                    mActivity.binding.cdPlay.setVisibility(View.VISIBLE);
                    break;
                }
            }

            boolean isFirst = SharedPreferencesHelper.getInstance(mActivity).getBoolean("movePlayFirst", true);

            if (mActivity.binding.cdPlay.getVisibility() == View.VISIBLE &&isFirst) {
                mActivity.binding.cdPlay.post(new Runnable() {
                    @Override
                    public void run() {
                        showGuideView();
                    }
                });
            }

            mActivity.binding.cdPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProcessVideoActivity.startActivity(mActivity, (ArrayList<MTimeMoveDetailInfo.PlaylistBean>) data.getData().getPlaylist());
                }
            });
        }


    }

    public void showLoading(boolean isShow) {
        if (isShow) {
            mActivity.binding.rlLoading.setVisibility(View.VISIBLE);
        } else {
            mActivity.binding.rlLoading.setVisibility(View.GONE);
        }

    }

    public void showMessage(String message) {
        if (mActivity == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mActivity, message);
    }
}
