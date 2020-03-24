package com.wkq.order.modlue.novel.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.wkq.order.R;
import com.wkq.order.databinding.ItemNovelTopBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.zia.easybookmodule.bean.rank.RankClassify;
import com.zia.easybookmodule.bean.rank.RankInfo;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-30
 * <p>
 * 用途:
 */


public class NovelTopAdapter extends DataBindingAdapter<RankInfo> {
    Context context;

    public NovelTopAdapter(Context context) {
        super(context, R.layout.item_novel_top, com.wkq.order.BR.data);
        this.context = context;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder dataBindingHolder = (DataBindingViewHolder) holder;
        ItemNovelTopBinding binding = (ItemNovelTopBinding) dataBindingHolder.getBinding();
        binding.setData(getItem(position));


    binding.root.setOnClickListener(view -> {
        if (viewClickListener!=null)viewClickListener.onViewClick(view,getItem(position));
    });

    }


}
