package com.wkq.order.modlue.move.frame.view;

import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.appbar.AppBarLayout;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.net.BaseInfo;
import com.wkq.net.model.MoveDbMoveDetailInfo;
import com.wkq.order.modlue.move.ui.PreviewImageActivity;
import com.wkq.order.modlue.move.ui.adapter.MoveCreditsAdapter;
import com.wkq.order.modlue.move.ui.MoveDetailActivity;
import com.wkq.order.modlue.move.ui.adapter.MoveImgsAdapter;
import com.wkq.order.modlue.move.ui.adapter.RecommendSimilarMoveAdapter;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.GlideUtlis;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-27
 * <p>
 * 用途:
 */


public class MoveDailView implements MvpView {
    MoveDetailActivity mActivity;
    private MoveCreditsAdapter moveCreditsAdapter;
    private RecommendSimilarMoveAdapter rsAdapter;
    private MoveImgsAdapter imgsAdapter;
    public MoveDailView(MoveDetailActivity activity) {
        mActivity = activity;
    }

    public void initView() {
        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setDarkMode(mActivity);
        mActivity.binding.setOnclick(mActivity);
        mActivity.binding.rvCredits.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));

        moveCreditsAdapter = new MoveCreditsAdapter(mActivity);
        mActivity.binding.rvCredits.setAdapter(moveCreditsAdapter);

        mActivity.binding.rvRs.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));

        rsAdapter = new RecommendSimilarMoveAdapter(mActivity);
        mActivity.binding.rvRs.setAdapter(rsAdapter);

        mActivity.binding.rvImgs.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));

        imgsAdapter = new MoveImgsAdapter(mActivity);
        mActivity.binding.rvImgs.setAdapter(imgsAdapter);


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

        imgsAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                MoveDbMoveDetailInfo.ImagesBean.PostersBean bean = (MoveDbMoveDetailInfo.ImagesBean.PostersBean) program;
                startPreview(bean);
            }
        });

        rsAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {

                if (program != null && program instanceof MoveDbMoveDetailInfo.SimilarMoviesBean.ResultsBean) {
                    MoveDbMoveDetailInfo.SimilarMoviesBean.ResultsBean bean = (MoveDbMoveDetailInfo.SimilarMoviesBean.ResultsBean) program;
                    MoveDetailActivity.startMoveDetail(mActivity, bean.getId() + "");
                }
            }
        });
    }

    int position = 0;

    private void startPreview(MoveDbMoveDetailInfo.ImagesBean.PostersBean bean) {
        String preImg = bean.getFile_path();
        ArrayList<String> imgs = new ArrayList<>();
        for (MoveDbMoveDetailInfo.ImagesBean.PostersBean postersBean : imgsAdapter.getList()) {
            if (!TextUtils.isEmpty(postersBean.getFile_path())) {
                imgs.add(postersBean.getFile_path());
                if (preImg.equals(postersBean.getFile_path())) {
                    position = imgsAdapter.getList().indexOf(postersBean);
                }
            }
        }
        PreviewImageActivity.startPreViewImgs(mActivity, imgs, position);
    }


    public void showMessage(String message) {
        if (mActivity == null || mActivity.isFinishing()) return;
        AlertUtil.showDeftToast(mActivity, message);
    }

    public void setData(BaseInfo<MoveDbMoveDetailInfo> data) {

        if (data == null || mActivity == null) return;
        MoveDbMoveDetailInfo info = data.getData();
        mActivity.binding.setData(info);
        //类型
        String genreStr = "";
        List<MoveDbMoveDetailInfo.GenresBean> genres = info.getGenres();
        if (genres != null) {
            for (MoveDbMoveDetailInfo.GenresBean genre : genres) {
                genreStr = genreStr.concat(genre.getName()).concat("/");
            }
        }

        if (TextUtils.isEmpty(genreStr)) {
            genreStr = "未知";
        } else {
            int last = genreStr.lastIndexOf("/");
            genreStr = genreStr.substring(0, last);
        }
        mActivity.binding.tvMoveType.setText(genreStr);
        GlideUtlis.loadMoveImg200(mActivity, info.getPoster_path(), mActivity.binding.ivIcon);
        GlideUtlis.loadMoveImg500(mActivity, info.getBackdrop_path(), mActivity.binding.ivDrop);
        if (info.getCredits().getCast() == null || info.getCredits().getCast().size() <= 0) {
            mActivity.binding.llCredits.setVisibility(View.GONE);
        } else {
            moveCreditsAdapter.addItems(info.getCredits().getCast());
        }

        if (info.getSimilar_movies().getResults() == null || info.getSimilar_movies().getResults().size() <= 0) {
            mActivity.binding.llRs.setVisibility(View.GONE);
        } else {
            rsAdapter.addItems(info.getSimilar_movies().getResults());
        }

        if (info.getImages().getPosters() == null || info.getImages().getPosters().size() <= 0) {
            mActivity.binding.llImgs.setVisibility(View.GONE);
        } else {
            imgsAdapter.addItems(info.getImages().getPosters());
        }
        BigDecimal b = new BigDecimal(String.valueOf(info.getVote_average()));
        float star = b.floatValue();
        float starf = star / 10 * 5;
        mActivity.binding.raStar.setStar(starf);


    }

    public void showLoading() {
        mActivity.binding.rlLoading.setVisibility(View.VISIBLE);

    }

    public void hideLoading() {
        mActivity.binding.rlLoading.setVisibility(View.GONE);

    }
}
