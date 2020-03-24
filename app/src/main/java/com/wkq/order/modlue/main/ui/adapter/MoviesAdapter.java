package com.wkq.order.modlue.main.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.wkq.net.model.MovieInTheatersBean;
import com.wkq.order.R;
import com.wkq.net.model.MoviesInfo;
import com.wkq.order.databinding.ItemMovieInfoBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.wkq.order.utils.GlideRoundedCornersTransform;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MoviesAdapter extends DataBindingAdapter<MovieInTheatersBean.SubjectsBean> {
    public MoviesAdapter(Context context) {
        super(context, R.layout.item_movie_info, com.wkq.order.BR.data);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder bindingHolder = (DataBindingViewHolder) holder;

        ItemMovieInfoBinding binding = (ItemMovieInfoBinding) bindingHolder.getBinding();
        binding.setData(getItem(position));

        RequestOptions options = RequestOptions.bitmapTransform(new GlideRoundedCornersTransform(5, GlideRoundedCornersTransform.CornerType.ALL)).placeholder(R.drawable.bg_image_loading).error(R.drawable.bg_image_loading).priority(Priority.HIGH);
        Glide.with(mContext).load(getItem(position).getImages().getSmall()).apply(options).into(binding.ivImage);

    }
}
