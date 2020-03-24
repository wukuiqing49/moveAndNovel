package com.wkq.order.modlue.novel.ui.activity.preview;

import android.widget.Toast;

import androidx.annotation.Nullable;


import com.wkq.order.application.OrderApplication;
import com.zia.toastex.ToastEx;

import java.lang.ref.WeakReference;


public class ToastUtil {

    private static WeakReference<Toast> toast;

    public static void onSuccess(@Nullable String content) {
        if (content == null) return;
        if (toast != null && toast.get() != null) toast.get().cancel();
        toast = new WeakReference<>(ToastEx.success(OrderApplication.getContext(), content, Toast.LENGTH_SHORT));
        toast.get().show();
    }

    public static void onError(@Nullable String content) {
        if (content == null) return;
        if (toast != null && toast.get() != null) toast.get().cancel();
        toast = new WeakReference<>(ToastEx.error(OrderApplication.getContext(), content, Toast.LENGTH_SHORT));
        toast.get().show();
    }

    public static void onInfo(@Nullable String content) {
        if (content == null) return;
        if (toast != null && toast.get() != null) toast.get().cancel();
        toast = new WeakReference<>(ToastEx.info(OrderApplication.getContext(), content, Toast.LENGTH_SHORT));
        toast.get().show();
    }

    public static void onNormal(@Nullable String content) {
        if (content == null) return;
        if (toast != null && toast.get() != null) toast.get().cancel();
        toast = new WeakReference<>(ToastEx.normal(OrderApplication.getContext(), content, Toast.LENGTH_SHORT));
        toast.get().show();
    }

    public static void onWarning(@Nullable String content) {
        if (content == null) return;
        if (toast != null && toast.get() != null) toast.get().cancel();
        toast = new WeakReference<>(ToastEx.warning(OrderApplication.getContext(), content, Toast.LENGTH_SHORT));
        toast.get().show();
    }
}