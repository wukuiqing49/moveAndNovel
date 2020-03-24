package com.wkq.order.modlue.htmlmove.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.wkq.order.R;
import com.wkq.order.databinding.ItemMoveHtmlImgsBinding;
import com.wkq.order.databinding.ItemMoveStringBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.wkq.order.utils.GlideUtlis;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-28
 * <p>
 * 用途:
 */


public class MoveStringAdapter extends DataBindingAdapter<String> {
    Context context;

    public MoveStringAdapter(Context context) {

        super(context, R.layout.item_move_string, com.wkq.order.BR.data);
        this.context = context;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder dataBindingViewHolder = (DataBindingViewHolder) holder;
        ItemMoveStringBinding binding = (ItemMoveStringBinding) dataBindingViewHolder.getBinding();


        binding.tvAward.setText(getItem(position));



    }
}
