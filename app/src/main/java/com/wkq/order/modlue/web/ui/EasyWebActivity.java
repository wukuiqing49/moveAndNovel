package com.wkq.order.modlue.web.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.wkq.order.R;
import com.wkq.order.databinding.ActivityEasyWebBinding;


/**
 *
 */
public class EasyWebActivity extends BaseAgentWebActivity {

    private String url;
    private ActivityEasyWebBinding binding;

    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, EasyWebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_easy_web);
        binding.cdPlay.setOnClickListener(view -> {
            CheckLineActivity.startActivity(this, mAgentWeb.getWebCreator().getWebView().getUrl());
        });

    }


    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return (ViewGroup) this.findViewById(R.id.container);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected int getIndicatorColor() {
        return Color.parseColor("#23d41e");
    }

    @Override
    protected void setTitle(WebView view, String title) {
        super.setTitle(view, title);
//        if (!TextUtils.isEmpty(title)) {
//            if (title.length() > 10) {
//                title = title.substring(0, 10).concat("...");
//            }
//        }
//        mTitleTextView.setText(title);
    }

    @Override
    protected int getIndicatorHeight() {
        return 3;
    }

    @Nullable
    @Override
    protected String getUrl() {
        return url;
    }
}
