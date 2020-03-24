package com.wkq.order.modlue.web.model;

import androidx.databinding.BaseObservable;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/20
 * <p>
 * 简介:
 */
public class CheckLineInfo extends BaseObservable {
    //线路名字
    String lineName;
    //线路地址
    String lineUrl;

    public CheckLineInfo(String lineName, String lineUrl) {
        this.lineName = lineName;
        this.lineUrl = lineUrl;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineUrl() {
        return lineUrl;
    }

    public void setLineUrl(String lineUrl) {
        this.lineUrl = lineUrl;
    }
}
