package com.wkq.qr.model;

/**
 * 创建：wukuiqing
 * <p>
 * 时间：2018/4/25
 * <p>
 * 描述： 二维码生成的数据结构
 */

public class QrInfo {

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String sid;
    private String token;
    //二维码的类型 type user 表示 个人名片
    private String type;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    private String nick;

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    private String face;


}
