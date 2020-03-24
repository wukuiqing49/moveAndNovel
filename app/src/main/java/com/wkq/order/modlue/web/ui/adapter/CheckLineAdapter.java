package com.wkq.order.modlue.web.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.wkq.order.BR;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemCheckLineBinding;
import com.wkq.order.modlue.web.model.CheckLineInfo;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/20
 * <p>
 * 简介:
 */
public class CheckLineAdapter extends DataBindingAdapter<CheckLineInfo> {
    public CheckLineAdapter(Context context) {
        super(context, R.layout.item_check_line, BR.data);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        DataBindingViewHolder dataBindingViewHolder = (DataBindingViewHolder) holder;

        ItemCheckLineBinding binding = (ItemCheckLineBinding) dataBindingViewHolder.getBinding();
        binding.setData(getItem(position));
        binding.llRoot.setOnClickListener(v -> {
            viewClickListener.onViewClick(binding.llRoot, getItem(position));
        });


    }
}
