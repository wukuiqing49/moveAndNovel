package com.wkq.order.utils;

/**
 * Created by xiansong on 2018/4/2.
 */

public class BackTowPressed {
    private static long back_pressed;

    /**
     * 两次点击事件判断
     *
     * @return
     */
    public static boolean onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) return true;
        back_pressed = System.currentTimeMillis();
        return false;
    }

    public static void onRestart() {
        back_pressed = 0;
    }
}
