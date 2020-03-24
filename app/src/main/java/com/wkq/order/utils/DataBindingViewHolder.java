package com.wkq.order.utils;

import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/18
 * <p>
 * 简介:
 */
public class DataBindingViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;

    public DataBindingViewHolder(View itemView) {
        super(itemView);
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }

    public ViewDataBinding getBinding() {
        return this.binding;
    }
}
