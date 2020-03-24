package com.wkq.baseLib.utlis;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;



import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-30
 * <p>
 * 用途:WebView中直接将p标签转为html代码
 */


public class WebViewUtil {
    private static WebEleClickListener webEleListener;
    private static Activity context;

    /**
     * 将<p>标签转为html同时有回调
     *
     * @param pData
     * @param webView
     * @param webEleClickListener
     */
    public static void setPtoHtmlData(String pData, final WebView webView, WebEleClickListener webEleClickListener) {
        if (null != webView) {
            webEleListener = webEleClickListener;
            String html = "<html><head><meta charset=\"UTF-8\"><style type=\"text/css\">html,body{padding:0px;margin:0px;} img{background-size:contain|cover;width:100%;height: auto;margin:15px 0;} p{margin:0px;line-height:24px;}</style></head><body>" + pData + "</body></html>";            // 设置支持javascript脚本
            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(new JsObject(), "injectedObject");
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    String js = "javascript:(function(){" +
                            "var images = document.getElementsByTagName(\"img\");" +
                            "var imageUrls = new Array(); " +
                            "for(var i=0; i<images.length; i++) {" +
                            "   imageUrls[i] = images[i].src; " +
                            "   images[i].pos = i; " +
                            "   images[i].onclick = function(){" +
                            "       window.injectedObject.openImage(this.src, this.pos);" +
                            "   }" +
                            "}" +
                            "window.injectedObject.setImageUrls(imageUrls);  " +
                            "})()";
                    webView.loadUrl(js);
                }
            });
            webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        }
    }

    /**
     * 将<p>标签转为html没有回调,直接跳转
     *
     * @param activity
     * @param pData
     * @param webView
     */
    public static void setPtoHtmlData(Activity activity, String pData, final WebView webView) {
        if (null != webView) {
            context = activity;
            String html = "<html><head><meta charset=\"UTF-8\"><style type=\"text/css\">html,body{padding:0px;margin:0px;font-size:15px} img{background-size:contain|cover;width:100%;height: auto;} p{margin:0px;font-size:15px}</style></head><body>" + pData + "</body></html>";
            // 设置支持javascript脚本
            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(new JsObject(), "injectedObject");
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    String js = "javascript:(function(){" +
                            "var images = document.getElementsByTagName(\"img\");" +
                            "var imageUrls = new Array(); " +
                            "for(var i=0; i<images.length; i++) {" +
                            "   imageUrls[i] = images[i].src; " +
                            "   images[i].pos = i; " +
                            "   images[i].onclick = function(){" +
                            "       window.injectedObject.openImage(this.src, this.pos);" +
                            "   }" +
                            "}" +
                            "window.injectedObject.setImageUrls(imageUrls);  " +
                            "})()";
                    webView.loadUrl(js);
                }
            });
            webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        }
    }

    /**
     * 将<p>标签转为html没有回调,直接跳转,可指定文字大小
     *
     * @param activity
     * @param pData
     * @param webView
     * @param fontSize 字体大小
     */
    public static void setPtoHtmlData(Activity activity, String pData, final WebView webView, int fontSize) {
        if (null != webView) {
            context = activity;

            String html = "<html><head><meta charset=\"UTF-8\"><style type=\"text/css\">html,body{padding:0px;margin:0px;font-size:" + fontSize + "px} img{background-size:contain|cover;width:100%;height: auto;} p{margin:0px;font-size:" + fontSize + "px}</style></head><body>" + pData + "</body></html>";
            // 设置支持javascript脚本
            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(new JsObject(), "injectedObject");
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    int w = View.MeasureSpec.makeMeasureSpec(0,
                            View.MeasureSpec.UNSPECIFIED);
                    int h = View.MeasureSpec.makeMeasureSpec(0,
                            View.MeasureSpec.UNSPECIFIED);
                    // 重新测量
                    view.measure(w, h);
                    String js = "javascript:(function(){" +
                            "var images = document.getElementsByTagName(\"img\");" +
                            "var imageUrls = new Array(); " +
                            "for(var i=0; i<images.length; i++) {" +
                            "   imageUrls[i] = images[i].src; " +
                            "   images[i].pos = i; " +
                            "   images[i].onclick = function(){" +
                            "       window.injectedObject.openImage(this.src, this.pos);" +
                            "   }" +
                            "}" +
                            "window.injectedObject.setImageUrls(imageUrls);  " +
                            "})()";
                    webView.loadUrl(js);
                }

            });
            webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        }
    }

    public interface WebEleClickListener {
        void onImageClick(String src, int pos, String[] mImageUrls);
    }

    /**
     * 处理p 标签中图片预览问题
     */
    private static class JsObject {

        private String[] mImageUrls;

        @JavascriptInterface
        public void setImageUrls(String[] imageUrls) {
            this.mImageUrls = imageUrls;
        }

        @JavascriptInterface
        public void openImage(String src, int pos) {
            if (null != webEleListener) {
                webEleListener.onImageClick(src, pos, this.mImageUrls);
            }
            if (null != context) {
                if (null != src && !TextUtils.isEmpty(src) && null != mImageUrls) {
                    ArrayList<String> imgs = new ArrayList<>();
                    for (String imageUrl : mImageUrls) {
                        imgs.add(imageUrl);
                    }
                    if (null != imgs && imgs.size() > 0) {
//                        previewImg(context, src, imgs);
                    }
                }
            }
        }
    }

//    /**
//     * 跳转到图片预览页面
//     *
//     * @param activity
//     * @param showImg
//     * @param imgs
//     */
//    public static void previewImg(Activity activity, String showImg, List<String> imgs) {
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("imgPath", showImg);
//        jsonObject.addProperty("previewPath", "");
//        String previewUrl = jsonObject.toString();
//        ArrayList<String> list = new ArrayList<>();
//        if (imgs != null) {
//            for (int i = 0; i < imgs.size(); i++) {
//                JsonObject jo = new JsonObject();
//                jo.addProperty("imgPath", imgs.get(i));
//                jo.addProperty("previewPath", "");
//                String ins = jo.toString();
//                list.add(ins);
//            }
//        }
//        ARouter.getInstance().build("/preview/PreviewImageViewActivity")
//                .withString("type", "htmlImg")
//                .withTransition(android.R.anim.fade_in, android.R.anim.fade_out)
//                .withString("collectionType", "moment")
//                .withString("previewUrl", previewUrl)
//                .withStringArrayList("preImgList", list)
//                .withBoolean("isFriend", false)
//                .withBoolean("isHide", false)
//                .withBoolean("isLife", false)
//                .withBoolean("isCollection", false)
//                .navigation(activity);
//    }
}