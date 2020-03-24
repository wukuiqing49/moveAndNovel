package com.wkq.order.modlue.main.modle;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-23
 * <p>
 * 用途:
 */


public class BannerInfo {
    private String title;
    private String imgUrl;
    private String urlPath;

    public BannerInfo() {

    }

    public BannerInfo(String title, String imgUrl, String urlPath) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.urlPath = urlPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
