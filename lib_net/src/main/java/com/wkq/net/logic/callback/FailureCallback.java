package com.wkq.net.logic.callback;

public interface FailureCallback extends Callback{
    //逻辑操作错误事件 ,不影响逻辑代码. 如:Load_xxx_error 或 Input_xxx_empty
    void onFailure(int state, String message);
}
