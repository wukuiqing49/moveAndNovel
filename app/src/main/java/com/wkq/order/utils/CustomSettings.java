package com.wkq.order.utils;


import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.just.agentweb.AbsAgentWebSettings;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultDownloadImpl;
import com.just.agentweb.IAgentWebSettings;
import com.just.agentweb.WebListenerManager;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-12
 * <p>
 * 用途:
 */
public class CustomSettings extends AbsAgentWebSettings {
    public CustomSettings() {
        super();
    }

    @Override
    protected void bindAgentWebSupport(AgentWeb agentWeb) {

    }


    @Override
    public IAgentWebSettings toSetting(WebView webView) {
        super.toSetting(webView);

        WebSettings settings = getWebSettings();
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        settings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//        settings.setAllowFileAccess(false);  //设置可以访问文件
//        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        settings.setBlockNetworkImage(false);
//        settings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
//        settings.setDatabaseEnabled(false);   //开启 database storage API 功能
//        settings.setAppCacheEnabled(false);//开启 Application Caches 功能
//        settings.setDefaultTextEncodingName("utf-8");//设置编码格式
        settings.setJavaScriptEnabled(true);//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
//        settings.setUserAgentString(settings.getUserAgentString() + "; app/plat_a");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
//        settings.setSupportMultipleWindows(true);//代表支持多窗口打开
//        settings.setSupportMultipleWindows(true);

        return this;
    }

    @Override
    public WebListenerManager setDownloader(WebView webView, DownloadListener downloadListener) {
        return super.setDownloader(webView,
                DefaultDownloadImpl.create((Activity) webView.getContext()
                        , webView, mAgentWeb.getPermissionInterceptor()));
    }
}