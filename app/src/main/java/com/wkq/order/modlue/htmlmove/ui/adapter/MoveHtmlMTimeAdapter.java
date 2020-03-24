package com.wkq.order.modlue.htmlmove.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.wkq.net.model.MoveDataInfo;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemHomeHtmlMtimeBinding;
import com.wkq.order.databinding.ItemMovieDbComingInfoBinding;
import com.wkq.order.databinding.ItemMovieHtmlMtimeBinding;
import com.wkq.order.utils.Constant;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.wkq.order.utils.GlideRoundedCornersTransform;
import com.wkq.order.utils.MoveDbDataSaveUtlis;

import wkq.com.lib_move.model.MoveInfo;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MoveHtmlMTimeAdapter extends DataBindingAdapter<MoveInfo> {
    public MoveHtmlMTimeAdapter(Context context) {
        super(context, R.layout.item_movie_html_mtime, com.wkq.order.BR.data);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder bindingHolder = (DataBindingViewHolder) holder;

        ItemMovieHtmlMtimeBinding binding = (ItemMovieHtmlMtimeBinding) bindingHolder.getBinding();
        binding.setData(getItem(position));

        if (viewClickListener != null)
            binding.root.setOnClickListener(view -> viewClickListener.onViewClick(view, getItem(position)));
        RequestOptions options = RequestOptions.bitmapTransform(new GlideRoundedCornersTransform(5, GlideRoundedCornersTransform.CornerType.ALL)).placeholder(R.drawable.bg_image_loading).error(R.drawable.bg_image_loading).priority(Priority.HIGH);
        Glide.with(mContext).load(getItem(position).getMoveCover()).apply(options).into(binding.ivImage);

    }
}
