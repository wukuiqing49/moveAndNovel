package com.wkq.order.modlue.htmlmove.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.wkq.net.model.MTimeMoveDetailInfo;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemMoveHtmlImgsBinding;
import com.wkq.order.databinding.ItemMoveProcessVideoBinding;
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


public class ProcessVideoAdapter extends DataBindingAdapter<MTimeMoveDetailInfo.PlaylistBean> {
    Context context;

    public ProcessVideoAdapter(Context context) {

        super(context, R.layout.item_move_process_video, com.wkq.order.BR.data);
        this.context = context;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder dataBindingViewHolder = (DataBindingViewHolder) holder;
        ItemMoveProcessVideoBinding binding = (ItemMoveProcessVideoBinding) dataBindingViewHolder.getBinding();

        binding.setData(getItem(position));
        binding.root.setOnClickListener(view -> {
            viewClickListener.onViewClick(binding.getRoot(), getItem(position));
        });


    }
}
