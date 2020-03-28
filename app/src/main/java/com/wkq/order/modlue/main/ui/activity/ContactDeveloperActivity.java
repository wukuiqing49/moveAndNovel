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
        binding.llQqGroup.setOnClickListener(view -> {

            joinQQGroup("mdSrZGBrHPBX6dJjlJ7zKGi-MThOac9N");


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


    /****************
     *
     * 发起添加群流程。群号：VIP电影小说(688644721) 的 key 为： mdSrZGBrHPBX6dJjlJ7zKGi-MThOac9N
     * 调用 joinQQGroup(mdSrZGBrHPBX6dJjlJ7zKGi-MThOac9N) 即可发起手Q客户端申请加群 VIP电影小说(688644721)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/
    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
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
