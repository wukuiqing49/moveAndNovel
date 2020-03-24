package com.wkq.order.modlue.move.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityPreviewImgBinding;
import com.wkq.order.modlue.move.frame.presenter.PreviewImagePresenter;
import com.wkq.order.modlue.move.frame.view.PreviewImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class PreviewImageActivity extends MvpBindingActivity<PreviewImageView, PreviewImagePresenter, ActivityPreviewImgBinding> {


    public static void startPreViewImgs(Context context, ArrayList<String> imgs, int posotion) {
        Intent intent = new Intent(context, PreviewImageActivity.class);
        intent.putStringArrayListExtra("imgs", imgs);
        intent.putExtra("position", posotion);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }


    public List<String> imgs = new ArrayList<>();

    public int position = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preview_img;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgs = getIntent().getStringArrayListExtra("imgs");
        position = getIntent().getIntExtra("position", 0);

        if (imgs.size() <= 0) {
            AlertUtil.showDeftToast(this, "数据异常");
            finish();
        }
        if (getMvpView() != null) getMvpView().initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
