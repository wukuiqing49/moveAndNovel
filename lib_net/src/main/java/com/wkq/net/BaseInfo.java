package com.wkq.net;


import com.wkq.net.model.IBaseInfo;

public class BaseInfo<T> implements IBaseInfo {
    //TODO 接口错误信息 需要自定义
    private String errorCode = ApiSubscriber.getSuccessCode();
    private String errorMessage = "";

    //TODO 接口内容数据 需要自定义
    private T data;

    public void setCode(String status_code) {
        this.errorCode = status_code;
    }

    @Override
    public String getCode() {
        return errorCode;
    }

    public void setMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
