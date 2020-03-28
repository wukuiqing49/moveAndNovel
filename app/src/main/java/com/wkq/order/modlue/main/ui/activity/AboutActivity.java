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


public class AboutActivity extends AppCompatActivity {


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
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
                "<p>本软件只用于学习</p>\n" +
                        "<p>本软件所有数据均来源于互联网</p>\n" +
                        "<p>本软件有部分广告</p>\n" +
                        "<p>本软件仅供学习参考，切勿商业使用。接口数据均</p>\n" +
                        "<p>来源于互联网。如有侵权，请联系删除</p>\n"+
                        "<p>请记录好账号,密码一个手机只能绑定一个账号/p>", binding.web);
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
