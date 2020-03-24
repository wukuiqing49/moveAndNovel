package com.wkq.order.modlue.web.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityFullVideoBinding;
import com.wkq.order.modlue.web.presenter.FullVideoPresenter;
import com.wkq.order.modlue.web.view.FullVideoView;
import com.wkq.order.utils.StatusBarUtil;

import cn.jzvd.JZVideoPlayerStandard;


/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-12
 * <p>
 * 用途:
 */


public class FullVideoActivity extends MvpBindingActivity<FullVideoView, FullVideoPresenter, ActivityFullVideoBinding> implements JZVideoPlayerStandard.OnClickListener {

    private String url;
    private String title;
    public static void startActivity(Context context, String url, String title) {
        Intent intent = new Intent(context, FullVideoActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_full_video;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarWrite(this);
        StatusBarUtil.setColor(this, this.getResources().getColor(R.color.black), 0);
        StatusBarUtil.setDarkMode(this);
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
    }


    @Override
    public void onResume() {
        // TODO Auto-generated method stub
//        getdata();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        JZVideoPlayerStandard.releaseAllVideos();
    }




    @Override
    public void onDestroy() {
        super.onDestroy();

        JZVideoPlayerStandard.releaseAllVideos();
    }



    @Override
    public void onClick(View view) {

        if (view.getId()==cn.jzvd.R.id.back){
            finish();

        }else if (view.getId()==cn.jzvd.R.id.fullscreen){
//            JZVideoPlayerStandard.f(this, JZVideoPlayerStandard.class, url, title);

        }

    }
}
