package com.wkq.order.modlue.developer.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wkq.database.bean.LocalBook;
import com.wkq.database.bean.NetBook;
import com.wkq.order.BR;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemDeveloperNovelDownLoadBinding;
import com.wkq.order.databinding.ItemDeveloperNovelSubscribeBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.wkq.order.utils.GlideRoundedCornersTransform;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-23
 * <p>
 * 用途:
 */


public class DeveloperNovelDownLoadAdapter extends DataBindingAdapter<LocalBook> {

    Context mContext;

    public DeveloperNovelDownLoadAdapter(Context context) {

        super(context, R.layout.item_developer_novel_down_load, BR.data);
        mContext = context;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder dataBindingViewHolder = (DataBindingViewHolder) holder;

        ItemDeveloperNovelDownLoadBinding binding = (ItemDeveloperNovelDownLoadBinding) dataBindingViewHolder.getBinding();
        binding.setData(getItem(position));

        binding.root.setOnClickListener(view -> {
            if (viewClickListener != null)
                viewClickListener.onViewClick(binding.root, getItem(position));
        });
        binding.btDelete.setOnClickListener(view -> {
            if (viewClickListener != null)
                viewClickListener.onViewClick(binding.btDelete, getItem(position));
        });


        RequestOptions requestOptions = RequestOptions.placeholderOf(R.drawable.bg_image_loading).error(R.drawable.bg_image_loading)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                .transform(new GlideRoundedCornersTransform(5, GlideRoundedCornersTransform.CornerType.ALL));
        ;


        Glide.with(mContext).load(getItem(position).getImageUrl()).apply(requestOptions).into(binding.ivNovelPoster);

    }

}

