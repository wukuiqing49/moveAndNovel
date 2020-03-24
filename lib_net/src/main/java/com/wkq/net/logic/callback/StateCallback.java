package com.wkq.net.logic.callback;

public interface StateCallback extends Callback {
    //逻辑操作中的状态更新事件.
    void onState(int state);
}