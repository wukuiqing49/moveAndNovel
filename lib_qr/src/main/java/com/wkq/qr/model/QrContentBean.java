package com.wkq.qr.model;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2018/9/17
 * <p>
 * 简介: 二维码识别的内容
 */

public class QrContentBean {
    String type;
    String content;
    Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
