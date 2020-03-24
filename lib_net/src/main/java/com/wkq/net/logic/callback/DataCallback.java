package com.wkq.net.logic.callback;

/**
 * Created by xiansong on 2018/1/8.
 */

public interface DataCallback<T> extends Callback{
    //内部数据传递
    void callback(int code, String message, T data);
}
