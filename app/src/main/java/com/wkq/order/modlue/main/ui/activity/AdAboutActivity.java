package com.wkq.order.modlue.main.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.umeng.analytics.MobclickAgent;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.baseLib.utlis.WebViewUtil;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityAboutBinding;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-30
 * <p>
 * 用途:
 */


public class AdAboutActivity extends AppCompatActivity {


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AdAboutActivity.class);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAboutBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        StatusBarUtil.setStatusBarWrite(this);
        StatusBarUtil.setColor(this, this.getResources().getColor(R.color.color_2b2b2b), 0);
        StatusBarUtil.setDarkMode(this);

        binding.rlBack.setOnClickListener(view -> finish());


        WebViewUtil.setPtoHtmlData(this,
                "<p>本软件纯属个人维护App</p>\n" +
                        "<p>　软件提供了电影资讯信息(无广告)</p>\n" +
                        "<p>　为了维护软件迫不得已追加了广告</p>\n" +
                        "<p>　广告的逻辑是每天需要点击三次广告</p>\n" +
                        "<p>　为了给您更好的服务,建议不要跳过广告</p>\n" +
                        "<p>　本软件仅供学习参考，切勿商业使用。接口数据均</p>\n" +
                        "<p>　　　来源于互联网。如有侵权，请联系删除</p>", binding.web);
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
