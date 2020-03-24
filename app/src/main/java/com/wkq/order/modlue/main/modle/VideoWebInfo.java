package com.wkq.order.modlue.main.modle;

import androidx.databinding.BaseObservable;
import androidx.databinding.Observable;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/18
 * <p>
 * 简介:
 */
public class VideoWebInfo extends BaseObservable {

    //网址
    String videoWebAddress;
    //网站名字
    String videoWebsiteName;

    public VideoWebInfo(String videoWebAddress, String videoWebsiteName) {
        this.videoWebAddress = videoWebAddress;
        this.videoWebsiteName = videoWebsiteName;
    }

    public String getVideoWebAddress() {
        return videoWebAddress;
    }

    public void setVideoWebAddress(String videoWebAddress) {
        this.videoWebAddress = videoWebAddress;
    }

    public String getVideoWebsiteName() {
        return videoWebsiteName;
    }

    public void setVideoWebsiteName(String videoWebsiteName) {
        this.videoWebsiteName = videoWebsiteName;
    }

}
