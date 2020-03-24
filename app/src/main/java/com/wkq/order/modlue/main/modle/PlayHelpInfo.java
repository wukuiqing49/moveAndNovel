package com.wkq.order.modlue.main.modle;

import androidx.databinding.BaseObservable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-23
 * <p>
 * 用途:
 */


public class PlayHelpInfo  extends BaseObservable {


    Integer srcInteger;
    String stepString;

    public Integer getSrcInteger() {
        return srcInteger;
    }

    public void setSrcInteger(Integer srcInteger) {
        this.srcInteger = srcInteger;
    }

    public String getStepString() {
        return stepString;
    }

    public void setStepString(String stepString) {
        this.stepString = stepString;
    }

    public PlayHelpInfo(Integer srcInteger, String stepString) {
        this.srcInteger = srcInteger;
        this.stepString = stepString;
    }
}
