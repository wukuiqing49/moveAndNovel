package com.wkq.net.logic;

/**
 * Created by xiansong on 2018/1/4.
 */

public class Config {

    //成功状态
    public static final int SUCCESS = 200;
    //失败状态
    public static final int FAIL = -1;
    //系统错误
    public static final int ERROR_SYSTEM = 2003;
    //上传/下载错误
    public static final int ERROR_UPLOAD = 2004;
    //空闲状态
    public static final int STATE_IDLE = 3001;
    //加载状态
    public static final int STATE_LOAD = 3002;

    public static final String ERROR_DEF = "%S_ERROR_%s code:%s message:%s";
}
