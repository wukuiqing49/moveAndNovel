package com.wkq.order.modlue.htmlmove.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemHomeHtmlMtimeBinding;
import com.wkq.order.databinding.ItemHtmlMtimeTopBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.wkq.order.utils.GlideRoundedCornersTransform;

import wkq.com.lib_move.model.MoveInfo;
import wkq.com.lib_move.model.MoveTopInfo;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MoveHtmlMTimeTopAdapter extends DataBindingAdapter<MoveTopInfo> {

    Context mContext;
    OnItemClickListener listener;

    public MoveHtmlMTimeTopAdapter(Context context) {
        super(context, R.layout.item_html_mtime_top, com.wkq.order.BR.data);
        mContext = context;
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder bindingHolder = (DataBindingViewHolder) holder;

        ItemHtmlMtimeTopBinding binding = (ItemHtmlMtimeTopBinding) bindingHolder.getBinding();
        binding.setData(getItem(position));

        if (viewClickListener != null)
            binding.root.setOnClickListener(view -> viewClickListener.onViewClick(view, getItem(position)));
        String url;
        RequestOptions options = RequestOptions.bitmapTransform(new GlideRoundedCornersTransform(5, GlideRoundedCornersTransform.CornerType.ALL)).placeholder(R.drawable.bg_image_loading).error(R.drawable.bg_image_loading).priority(Priority.HIGH);
        if (!TextUtils.isEmpty(getItem(position).getMoveCover()) && !getItem(position).getMoveCover().startsWith("http")) {
            url = "http:" + getItem(position).getMoveCover();
        } else {
            url = getItem(position).getMoveCover();
        }
        Glide.with(mContext).load(url).apply(options).into(binding.ivImage);


    }


    public interface OnItemClickListener {
        void onItemClick(MoveInfo info);
    }
}
