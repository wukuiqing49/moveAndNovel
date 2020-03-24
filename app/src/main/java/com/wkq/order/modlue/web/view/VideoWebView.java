package com.wkq.order.modlue.web.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebViewClient;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.web.ui.VideoWebviewActivity;
import com.wkq.order.utils.CustomSettings;
import com.wkq.order.utils.ImagesBgUtlis;


/**
 *
 */

public class VideoWebView implements MvpView {

    VideoWebviewActivity mActivity;
    private boolean isOclick = false;

    public VideoWebView(VideoWebviewActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        initAd();
        StatusBarUtil.setTransparentForWindow(mActivity);
//        StatusBarUtil.addTranslucentView(mActivity, 0);
//        StatusBarUtil.setDarkMode(mActivity);

        StatusBarUtil.hideFakeStatusBarView(mActivity);

        initWaitingBg();
        initWebView();
    }


    private void initAd() {


    }

    private void initWaitingBg() {

        Glide.with(mActivity).load(ImagesBgUtlis.getImg()).into(mActivity.binding.ivBg);

    }

    public void initWebView() {
//        WebChromeClient mWebChromeClient = new WebChromeClient() {
//
//            @Override
//            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
//
//
//                WebView newWebView = new WebView(mActivity);
//                view.addView(newWebView);
//
//                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
//                transport.setWebView(newWebView);
//                resultMsg.sendToTarget();
//
//                newWebView.setWebViewClient(new WebViewClient(){
//
//                    @Override
//                    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
//                        //TODO 在这里实现你的拦截方法
//                        webView.loadUrl(url);
//                        return true;
//                    }
//
//                    @Override
//                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                        super.onPageStarted(view, url, favicon);
//                        mActivity.mAgentWeb.getJsAccessEntrace().callJs("document.getElementsByTagName(\"iframe\")[0].src");
//
//
//                    }
//
//                    @Override
//                    public void onPageFinished(WebView view, String url) {
//                        super.onPageFinished(view, url);
//                        mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementById('player').parentNode;var b=a.children; for(var i =b.length-1; i>=0;i--){ if(b[i].id!='player'){a.removeChild(b[i]);}}");
//                        mActivity.binding.ivLoading.setVisibility(View.GONE);
//                    }
//                });
//                return true;
//            }


//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress>80){
////                    mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementById('player').parentNode;var b=a.children; for(var i =b.length-1; i>=0;i--){ if(b[i].id!='player'){a.removeChild(b[i]);}}");
//                }
//                super.onProgressChanged(view, newProgress);
//
//                Log.e("","");
//
//
//            }
//
//            @Override
//            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
//
//                if (!TextUtils.isEmpty(message)) {
//
//                    Log.e("url:", url);
////                    mActivity.mAgentWeb.getUrlLoader().loadUrl(message);
//                    return true;
//                } else {
//                    return super.onJsPrompt(view, url, message, defaultValue, result);
//                }
//            }
//        };

        WebViewClient webChromeClient = new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) return false;
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    return false;
                } else {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        mActivity.startActivity(intent);
                    } catch (Exception e) {
//                    ToastUtils.showShort("暂无应用打开此链接");
                    }
                    return true;
                }

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e("", "");
                mActivity.isErro = true;
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mActivity.binding.ivLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementById('player').parentNode;var b=a.children; for(var i =b.length-1; i>=0;i--){ if(b[i].id!='player'){a.removeChild(b[i]);}}");
                mActivity.mAgentWeb.getJsAccessEntrace().callJs("var elements = document.getElementsByClassName('bottom_fixed');\n" +
                        "while(elements.length > 0){\n" +
                        "elements[0].parentNode.removeChild(elements[0]);\n" +
                        "}");
                mActivity.binding.ivLoading.setVisibility(View.GONE);
                mActivity.binding.ivLoading.setVisibility(View.GONE);
//                mActivity.mAgentWeb.getJsAccessEntrace().callJs("var src=document.getElementsByTagName(\"iframe\")[0].src;var result=prompt(src)");
//                mActivity.mAgentWeb.getJsAccessEntrace().callJs("var c=document.getElementsByTagName('div');  for(var i =c.length-1; i>=0;i--){if(c[i].id.match(/sjdb/gi)!=null){c[i].style.display='none';}  }");
            }
        };


        mActivity.mAgentWeb = AgentWeb.with(mActivity)
                .setAgentWebParent(mActivity.binding.llRoot, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(mActivity.getResources().getColor(R.color.color_1aad19))
                .closeWebViewClientHelper()
                .setAgentWebWebSettings(new CustomSettings())
//                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(webChromeClient)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready().go(mActivity.url);
        mActivity.mAgentWeb.getJsAccessEntrace().callJs("document.getElementsByTagName(\"iframe\")[0].src");
    }

    public void startTimer() {
        mActivity.mAgentWeb.getJsAccessEntrace().callJs("var src=document.getElementsByTagName(\"iframe\")[0].src;var result=prompt(src)");
    }

    public void showMessage(String message) {
        if (mActivity == null) return;
        AlertUtil.showDeftToast(mActivity, message);
    }

    public void loadUrl(String url) {
        if (mActivity.mAgentWeb != null) mActivity.mAgentWeb.getUrlLoader().loadUrl(url);
    }

    public String getWebUrl() {
        return mActivity.mAgentWeb.getWebCreator().getWebView().getUrl();
    }


    public void cancelAd() {

    }

    public void onResume() {
//        if (mActivity.mAgentWeb != null && isOclick)
//            mActivity.mAgentWeb.getUrlLoader().loadUrl(mActivity.url);
    }
}
