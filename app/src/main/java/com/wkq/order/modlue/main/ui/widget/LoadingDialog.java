package com.wkq.order.modlue.main.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.wkq.order.R;


/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-09
 * <p>
 * 用途:
 */


public class LoadingDialog extends AlertDialog {


    public LoadingDialog(Context context) {
        this(context,0);
    }

    public LoadingDialog(Context context, int themeId) {
        super(context, R.style.CustomDialog);

    }

//    public static class Bulider {
//
//        private static Bulider create(Context context) {
//
//            LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(context);
//            LoadingDialog dialog = new LoadingDialog(context, R.style.CustomDialog);
//
//            View dialogLayoutView = inflater.inflate(R.layout.layout_loading, null);
//            dialog.setContentView(dialogLayoutView);
//
//            dialog.show();
//
//
//            return this;
//
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loading);
    }


}
