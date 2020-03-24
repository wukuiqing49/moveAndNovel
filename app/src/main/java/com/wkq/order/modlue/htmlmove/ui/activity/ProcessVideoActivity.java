package com.wkq.order.modlue.htmlmove.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.net.model.MTimeMoveDetailInfo;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityProcessVideoBinding;
import com.wkq.order.modlue.htmlmove.ui.adapter.ProcessVideoAdapter;
import com.wkq.order.modlue.move.ui.ProcessImgsActivity;
import com.wkq.order.modlue.web.ui.EasyWebActivity;
import com.wkq.order.modlue.web.ui.VideoWebviewActivity;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-05
 * <p>
 * 用途:
 */


public class ProcessVideoActivity extends AppCompatActivity {

    private ActivityProcessVideoBinding binding;
    private ArrayList<MTimeMoveDetailInfo.PlaylistBean> sites;


    public static void startActivity(Context context, ArrayList<MTimeMoveDetailInfo.PlaylistBean> sites) {
        Intent intent = new Intent(context, ProcessVideoActivity.class);
        intent.putParcelableArrayListExtra("sites", sites);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.preview_activity_in, R.anim.preview_activity_out);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_process_video);

        sites = getIntent().getParcelableArrayListExtra("sites");

        initView();
    }

    private void initView() {

        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.addTranslucentView(this, 0);
        StatusBarUtil.setDarkMode(this);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        binding.rvContent.setLayoutManager(new LinearLayoutManager(this));

        ProcessVideoAdapter mAdapter = new ProcessVideoAdapter(this);
        binding.rvContent.setAdapter(mAdapter);

        mAdapter.addItems(sites);

        mAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                MTimeMoveDetailInfo.PlaylistBean bean = (MTimeMoveDetailInfo.PlaylistBean) program;
//                VideoWebviewActivity.startActivity(ProcessVideoActivity.this, bean.getPlayUrlH5());
                EasyWebActivity.startActivity(ProcessVideoActivity.this, bean.getPlayUrlH5());
                finish();
            }
        });

        binding.tvCancel.setOnClickListener(view -> finish());


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.preview_activity_in, R.anim.preview_activity_out);
    }
}
