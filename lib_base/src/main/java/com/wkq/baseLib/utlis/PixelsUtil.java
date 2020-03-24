package com.wkq.baseLib.utlis;



import android.content.res.Resources;
import android.util.TypedValue;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-25
 * <p>
 * 用途:
 */


public class PixelsUtil {
    public PixelsUtil() {
    }

    public static int dip2px(float dipValue) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

    public static int px2dip(float pxValue) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static int sp2px(float spVal) {
        return (int)TypedValue.applyDimension(2, spVal, Resources.getSystem().getDisplayMetrics());
    }

    public static float px2sp(float pxVal) {
        return pxVal / Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    public static int dp2px(float dpValue) {
        return (int)(0.5F + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }
}