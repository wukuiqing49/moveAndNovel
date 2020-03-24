package com.wkq.order.modlue.move.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.wkq.order.BR;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemPrevieImgBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class PreviewImgAdapter extends DataBindingAdapter<String> {
    public PreviewImgAdapter(Context context) {
        super(context, R.layout.item_previe_img, com.wkq.order.BR.data);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder bindingViewHolder = (DataBindingViewHolder) holder;
        ItemPrevieImgBinding binding = (ItemPrevieImgBinding) bindingViewHolder.getBinding();

        binding.largeView.setImageView(getItem(position), "");

    }
}
