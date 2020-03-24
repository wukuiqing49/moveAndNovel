package com.wkq.net.model;

/**
 * Created by xiansong on 2018/4/24.
 */


import java.io.Serializable;

public interface IBaseInfo<T> extends Serializable {
    String getCode();

    String getMessage();

    T getData();
}
