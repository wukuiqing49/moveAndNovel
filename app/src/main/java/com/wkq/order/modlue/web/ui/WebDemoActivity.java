package com.wkq.order.modlue.web.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.just.agentweb.WebViewClient;
import com.umeng.analytics.MobclickAgent;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityWebBinding;


/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-13
 * <p>
 * 用途:
 */


public class WebDemoActivity extends AppCompatActivity {

    private ActivityWebBinding binding;


    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, WebDemoActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    private final WebViewClient webViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            binding.adb.loadUrl("javascript:(function() {document.getElementsByTagName(\"iframe\")[0].src})()");

            Log.e("","");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url=request.getUrl().toString();
            binding.adb.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(final WebView view, final String url) {

            binding.adb.loadUrl("javascript:(function() {var a=document.getElementById('player').parentNode;var b=a.children; for(var i =b.length-1; i>=0;i--){ if(b[i].id!='player'){a.removeChild(b[i]);}}})()");
            Log.e("","");
        }

        @Override
        public void onReceivedError(final WebView view, final int errorCode,
                                    final String description, final String failingUrl) {
            Log.e("","");
        }


    };


    private final WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(final WebView view, final int newProgress) {
Log.e("","");
        }


    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     String url=   getIntent().getStringExtra("url");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        WebSettings webSettings = binding.adb.getSettings();
        webSettings.setBlockNetworkImage(false);//是否阻塞加载网络图片  协议http or https
        webSettings.setAllowFileAccess(false); //允许加载本地文件html  file协议, 这可能会造成不安全 , 建议重写关闭
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            webSettings.setAllowFileAccessFromFileURLs(false); //通过 file mUrl 加载的 Javascript 读取其他的本地文件 .建议关闭
//            webSettings.setAllowUniversalAccessFromFileURLs(false);//允许通过 file mUrl 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
//        }
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webSettings.setDisplayZoomControls(false);
            webSettings.setAllowUniversalAccessFromFileURLs(false);//允许通过 file mUrl 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源

        }
        webSettings.setDefaultTextEncodingName("gb2312");//设置编码格式
        webSettings.setDefaultFontSize(16);
        webSettings.setMinimumFontSize(12);//设置 WebView 支持的最小字体大小，默认为 8
        webSettings.setGeolocationEnabled(true);

        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setDomStorageEnabled(true);

//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//        webSettings.setSupportZoom(false); //支持缩放
//        webSettings.setBuiltInZoomControls(false); //支持手势缩放
//        webSettings.setDisplayZoomControls(false); //是否显示缩放按钮
//        webSettings.setDefaultTextEncodingName("utf-8"); //设置文本编码
//        webSettings.setUseWideViewPort(false); //将图片调整到适合WebView的大小
//        webSettings.setLoadWithOverviewMode(true); //自适应屏幕
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setSaveFormData(true);
//        webSettings.setSupportMultipleWindows(true);
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setPluginState(WebSettings.PluginState.ON);//支持插件
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);


        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //不适用缓存
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局
//        webView.setOverScrollMode(View.OVER_SCROLL_NEVER); // 取消WebView中滚动或拖动到顶部、底部时的阴影
        binding.adb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 取消滚动条白边效果
//        // >= 19(SDK4.4)启动硬件加速，否则启动软件加速
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            binding.adb.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        } else {
            binding.adb.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            webSettings.setLoadsImagesAutomatically(false);
        }

        binding.adb.setWebViewClient(webViewClient);
        binding.adb.setWebChromeClient(webChromeClient);

        binding.adb.loadUrl(url);



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
