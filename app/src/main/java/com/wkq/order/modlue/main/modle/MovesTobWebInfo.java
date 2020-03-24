package com.wkq.order.modlue.main.modle;

import androidx.databinding.BaseObservable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-07
 * <p>
 * 用途:  顶部网页的数据模型
 */


public class MovesTobWebInfo extends BaseObservable {

    String webUrl;
    String webTitle;
    Integer bgRes;

    public MovesTobWebInfo(String webUrl, String webTitle, Integer bgRes) {
        this.webUrl = webUrl;
        this.webTitle = webTitle;
        this.bgRes = bgRes;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public Integer getBgRes() {
        return bgRes;
    }

    public void setBgRes(Integer bgRes) {
        this.bgRes = bgRes;
    }
}
