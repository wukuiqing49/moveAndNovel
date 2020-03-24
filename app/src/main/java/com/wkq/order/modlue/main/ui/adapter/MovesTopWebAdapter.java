package com.wkq.order.modlue.main.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wkq.net.model.MovieInTheatersBean;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemMovieInfoBinding;
import com.wkq.order.databinding.ItemMovieTopInfoBinding;
import com.wkq.order.modlue.main.modle.MovesTobWebInfo;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.wkq.order.utils.GlideRoundedCornersTransform;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MovesTopWebAdapter extends DataBindingAdapter<MovesTobWebInfo> {
    Context mContext;

    public MovesTopWebAdapter(Context context) {
        super(context, R.layout.item_movie_top_info, com.wkq.order.BR.data);
        mContext = context;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder bindingHolder = (DataBindingViewHolder) holder;

        ItemMovieTopInfoBinding binding = (ItemMovieTopInfoBinding) bindingHolder.getBinding();
        binding.setData(getItem(position));
//
        RequestOptions options = RequestOptions.bitmapTransform(new CircleCrop()).placeholder(R.mipmap.bg_pro_comment).error(R.mipmap.bg_pro_comment).priority(Priority.HIGH);

        Glide.with(mContext).load(getItem(position).getBgRes()).apply(options).into(binding.ivWeb);

        binding.root.setOnClickListener(view -> {
            viewClickListener.onViewClick(view,getItem(position));
        });


    }
}
