package com.wkq.order.modlue.main.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.umeng.analytics.MobclickAgent;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityContactDeveloperBinding;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-30
 * <p>
 * 用途:
 */


public class ContactDeveloperActivity extends AppCompatActivity {


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ContactDeveloperActivity.class);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityContactDeveloperBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_developer);
        StatusBarUtil.setStatusBarWrite(this);
        StatusBarUtil.setColor(this, this.getResources().getColor(R.color.color_2b2b2b), 0);
        StatusBarUtil.setDarkMode(this);

        binding.rlBack.setOnClickListener(view -> finish());
        binding.llQq.setOnClickListener(view -> {

            try {
                String url = "mqqwpa://im/chat?chat_type=wpa&uin=1571478933";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } catch (Exception e) {
                AlertUtil.showDeftToast(this, "系统异常,请手动添加好友联系!");
            }


        });


        binding.llEmail.setOnClickListener(view -> {

            try {
                Uri uri = Uri.parse("mailto:1571478933@qq.com");
                String[] email = {"1571478933@qq.com"};
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra(Intent.EXTRA_CC, email); // 抄送人
                intent.putExtra(Intent.EXTRA_SUBJECT, "联系开发者"); // 主题
                intent.putExtra(Intent.EXTRA_TEXT, "反馈内容"); // 正文
                startActivity(Intent.createChooser(intent, "请选择应用"));
            } catch (Exception e) {
                AlertUtil.showDeftToast(this, "系统异常,请手动发送邮件联系!");
            }


        });

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
