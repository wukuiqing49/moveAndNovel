package com.wkq.order.application;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.bun.miitmdid.core.ErrorCode;
import com.bun.miitmdid.core.JLibrary;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.supplier.IIdentifierListener;
import com.bun.supplier.IdSupplier;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeng.commonsdk.UMConfigure;
import com.wkq.order.R;
import com.wkq.order.modlue.novel.ui.activity.preview.FileUtil;
import com.wkq.order.utils.DeviceUtlis;
import com.zia.easybookmodule.bean.rule.XpathSiteRule;
import com.zia.easybookmodule.engine.SiteCollection;
import com.zia.easybookmodule.site.CustomXpathSite;
import com.zia.toastex.ToastEx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/11/11
 * <p>
 * 简介:
 */
public class OrderApplication extends MultiDexApplication {
    static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initDb();
        initYm();

        Stetho.initializeWithDefaults(this);
        //设置toast颜色
        ToastEx.Config.getInstance()
                .setInfoColor(getResources().getColor(R.color.colorPrimary))
                .apply();

        //添加自定义书源到easybook单例中
        try {
            List<XpathSiteRule> rules = getXpathRuleFromFile(FileUtil.INSTANCE.getRulePath());
            if (rules != null) {
                for (XpathSiteRule rule : rules) {
                    SiteCollection.getInstance().addSite(new CustomXpathSite(rule));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        initIMEI();


    }


    private void initIMEI() {


            try {
                JLibrary.InitEntry(mContext);

            } catch (Exception e) {
                e.printStackTrace();
            }



    }

    public static Context getContext() {

        return mContext;
    }


    private List<XpathSiteRule> getXpathRuleFromFile(String filePath) throws IOException {
        File ruleFile = new File(filePath);
        if (!ruleFile.exists()) {
            return null;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = in.readLine()) != null) {
            sb.append(str);
        }
        in.close();
        return new Gson().fromJson(sb.toString(), TypeToken.getParameterized(List.class, XpathSiteRule.class).getType());
    }

    private void initYm() {
        UMConfigure.init(this, "5e521df5895cca842f0001b4", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
    }

    /**
     * 初始化数据库
     */
    private void initDb() {
        //强烈建议在应用对应的Application#onCreate()方法中调用，避免出现content为null的异常

    }
}


