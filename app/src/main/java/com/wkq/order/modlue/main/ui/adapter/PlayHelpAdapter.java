package com.wkq.order.modlue.main.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wkq.order.BR;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemPlayHelpBinding;
import com.wkq.order.databinding.ItemVideoWebBinding;
import com.wkq.order.modlue.main.modle.PlayHelpInfo;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-23
 * <p>
 * 用途:
 */


public class PlayHelpAdapter extends DataBindingAdapter<PlayHelpInfo> {

    Context mContext;

    public PlayHelpAdapter(Context context) {

        super(context, R.layout.item_play_help, BR.data);
        mContext = context;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder dataBindingViewHolder = (DataBindingViewHolder) holder;

        ItemPlayHelpBinding binding = (ItemPlayHelpBinding) dataBindingViewHolder.getBinding();
        binding.setData(getItem(position));

        Glide.with(mContext).load(getItem(position).getSrcInteger()).into(binding.ivStep);


    }
}
