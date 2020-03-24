package com.wkq.baseLib.utlis;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/21
 * <p>
 * 简介:
 */
public class TimerHelper {


    private String TAG = "TollTimerHelper";

    private OnTimerListener helperListener;
    private int mSchedule = 0;
    private Timer mTimer;

    public TimerHelper() {
    }

    /**
     * 回到UI线程
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSchedule = msg.what;
            helperListener.onSchedule(mSchedule);
        }
    };

    /**
     * 开始定时
     */
    public void startTiming() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                mSchedule++;
                handler.sendEmptyMessage(mSchedule);
            }
        }, mSchedule);
    }

    /**
     * 定时器销毁
     */
    public void onTimerCancel() {
        if (mTimer != null) {
            mTimer.purge();
            mTimer = null;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                handler = null;
            }
        }
    }

    /**
     * 时间清零
     *
     * @param schedule
     */
    public void setSchedule(int schedule) {
        this.mSchedule = schedule;
    }


    public void setOnTimerListener(OnTimerListener helperListener) {

        this.helperListener = helperListener;
    }

    public interface OnTimerListener {
        void onSchedule(int schedule);
    }

}
