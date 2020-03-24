package com.wkq.baseLib.utlis;


import android.content.Context;

import com.wkq.baseLib.R;


public class AlertUtil {
    private static final ToastUtil.Build mainToast;

    public AlertUtil() {
    }

    public static void showDeftToast(Context context, int messageRes) {
        mainToast.build(context).showToast(context.getString(messageRes));
    }

    public static void showDeftToast(Context context, String message) {
        mainToast.build(context).showToast(message);
    }

    public static void showLongToast(Context context, String message, int duration) {
        mainToast.build(context).showToast(message, duration);
    }

    public static void showSuccessToast(Context context, String message) {
        ToastUtil.Build successToast = ToastUtil.newBuild().setGravity(17, 0, 0).setView(R.layout.layout_toast_success, R.id.text);
        successToast.build(context).showToast(message);
    }

    public static void showFailedToast(Context context, String message) {
        ToastUtil.Build failedToast = ToastUtil.newBuild().setGravity(17, 0, 0).setView(R.layout.layout_toast_failed, R.id.text);
        failedToast.build(context).showToast(message);
    }

    public static void showNoEqulesToast(Context context, String message) {
        ToastUtil.Build failedToast = ToastUtil.newBuild().setGravity(17, 0, 0).setView(R.layout.layout_toast_1, R.id.text);
        failedToast.build(context).showNoEqulesToast(message);
    }

    static {
        mainToast = ToastUtil.newBuild().setGravity(17, 0, 0).setView(R.layout.layout_toast_1, R.id.text);
    }
}
