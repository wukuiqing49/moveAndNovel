package com.wkq.order.modlue.web.view;

import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.utils.CustomSettings;
import com.wkq.order.modlue.web.ui.VideoSiteActivity;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-18
 * <p>
 * 用途:
 */


public class VideoSiteView implements MvpView {

    VideoSiteActivity mActivity;

    public VideoSiteView(VideoSiteActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        StatusBarUtil.setStatusBarWrite(mActivity);
        StatusBarUtil.setColor(mActivity, mActivity.getResources().getColor(R.color.white), 0);
        StatusBarUtil.setLightMode(mActivity);
        WebChromeClient mWebChromeClient = new WebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress > 80 && mActivity.binding.cdPlay.getVisibility() == View.GONE) {
                    mActivity.binding.cdPlay.setVisibility(View.VISIBLE);
                }
            }
        };



        mActivity.mAgentWeb = AgentWeb.with(mActivity)
                .setAgentWebParent(mActivity.binding.llRoot, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(mActivity.getResources().getColor(R.color.color_23d41e))
                .setAgentWebWebSettings(new CustomSettings())
                .setWebChromeClient(mWebChromeClient)
//                .setWebViewClient(webChromeClient)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .createAgentWeb()
                .ready()
                .go(mActivity.url);

//        mActivity.mAgentWeb.getJsAccessEntrace().callJs("document.getElementsByTagName(\"body\")[0].removeChild(document.getElementsByTagName(\"iframe\")[0])");

    }

    public String getWebUrl() {
        return mActivity.mAgentWeb == null ? "" : mActivity.mAgentWeb.getWebCreator().getWebView().getUrl();
    }
}
