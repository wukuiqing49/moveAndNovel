package com.wkq.order.modlue.htmlmove.frame.presenter;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.order.modlue.htmlmove.frame.view.HomeHtmlMoveTopView;

import java.util.List;

import wkq.com.lib_move.model.MTimeHomeBean;
import wkq.com.lib_move.model.MoveTopInfo;
import wkq.com.lib_move.utlis.MoveDataCallBack;
import wkq.com.lib_move.utlis.MoveHtmlUtlis;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-03
 * <p>
 * 用途:
 */


public class HomeHtmlMoveTopPresenter extends MvpBasePresenter<HomeHtmlMoveTopView> {

    public void getData(String url, int page) {

        MoveHtmlUtlis.getMTimeMoveDetail(url.concat(page+""),new MoveDataCallBack<List<MoveTopInfo>>() {
            @Override
            public void onFail(String message) {
                if (getView() != null) getView().showFail();
                if (getView() != null) getView().showMessage(message);
            }

            @Override
            public void onSuccess(List<MoveTopInfo> moveInfoList) {
                if (getView() != null) getView().setData(moveInfoList);
            }

            @Override
            public void onLoading() {

            }
        });

    }

}
