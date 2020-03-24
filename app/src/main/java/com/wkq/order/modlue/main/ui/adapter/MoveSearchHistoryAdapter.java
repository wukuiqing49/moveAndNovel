package com.wkq.order.modlue.main.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.wkq.database.dao.MoveSearchHistory;
import com.wkq.order.BR;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemSearchHistoryBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-30
 * <p>
 * 用途:
 */


public class MoveSearchHistoryAdapter extends DataBindingAdapter<MoveSearchHistory> {
    public MoveSearchHistoryAdapter(Context context) {
        super(context, R.layout.item_search_history, com.wkq.order.BR.data);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder bindingViewHolder= (DataBindingViewHolder) holder;
        ItemSearchHistoryBinding binding= (ItemSearchHistoryBinding) bindingViewHolder.getBinding();
        binding.tvSearch.setText(getItem(position).getMoveName());
        binding.root.setOnClickListener(view -> viewClickListener.onViewClick(view,getItem(position)));
    }
}
