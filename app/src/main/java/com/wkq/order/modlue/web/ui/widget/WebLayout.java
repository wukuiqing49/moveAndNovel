package com.wkq.order.modlue.web.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.just.agentweb.IWebLayout;
import com.wkq.order.R;
import com.wkq.order.databinding.LayoutWebBinding;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/18
 * <p>
 * 简介:
 */
public class WebLayout implements IWebLayout {

    private final LayoutWebBinding binding;

    public WebLayout(Context context) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_web, null, false);

    }

    @NonNull
    @Override
    public ViewGroup getLayout() {
        return binding.root;
    }

    @Nullable
    @Override
    public WebView getWebView() {
        return binding.webView;
    }

    public void hideImg(){
        binding.defaultImage.setVisibility(View.GONE);
    }
    public void showImg(){
        binding.defaultImage.setVisibility(View.GONE);
    }
}
