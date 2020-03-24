package com.wkq.order.modlue.novel.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.wkq.order.BR;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemNovelChatperBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.zia.easybookmodule.bean.Catalog;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-02-01
 * <p>
 * 用途:
 */


public class NovelChapterAdapter extends DataBindingAdapter<Catalog> {
    public NovelChapterAdapter(Context context) {
        super(context, R.layout.item_novel_chatper, BR.data);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder bindingViewHolder = (DataBindingViewHolder) holder;

        ItemNovelChatperBinding binding = (ItemNovelChatperBinding) bindingViewHolder.getBinding();
        binding.setData(getItem(position));
        binding.root.setOnClickListener(view -> {
                    if (viewClickListener != null)
                        viewClickListener.onViewClick(binding.root, getItem(position));
                }
        );
    }
}
